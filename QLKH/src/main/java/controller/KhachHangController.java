package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO1.KhachHangDAO;
import models.KhachHang;

@WebServlet("/")

public class KhachHangController extends HttpServlet {
	private static  final long serialVersionUID = 1L;
    private KhachHangDAO khDAO;

    public void init() {
        khDAO  = new KhachHangDAO();
    }
    
    protected void doPost(	HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
	 
    protected void doGet(	HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertKhachHang(request, response);
                    break;
                case "/delete":
                    deleteKhachHang(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateKhachHang(request, response);
                    break;
                default:
                    listKhachHang(request, response);
                    break;
            }
        }   catch (SQLException ex) {
                throw new ServletException(ex);
            }
    }

	private void listKhachHang (HttpServletRequest request, HttpServletResponse response) 
                                throws SQLException, IOException, ServletException 
    {
        List<KhachHang> listKH = khDAO.selectAllKhachHang();
        request.setAttribute("listKH", listKH);
        RequestDispatcher dispatcher = request.getRequestDispatcher("khachhang-list.jsp");
        dispatcher.forward(request, response);
	}

	private void updateKhachHang (HttpServletRequest request, HttpServletResponse response) 
                                  throws SQLException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String tenkhachhang = request.getParameter("tenkhachhang");
        String ngaysinh = request.getParameter("ngaysinh");
        String cmnd = request.getParameter("cmnd");
        String quequan = request.getParameter("quequan");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        KhachHang kh = new KhachHang(id, tenkhachhang, ngaysinh, cmnd, quequan, email, sdt);
        try {
			khDAO.updateKhachHang(kh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	private void showEditForm (HttpServletRequest request, HttpServletResponse response) 
                                throws SQLException, IOException, ServletException 
    {
        int id = Integer.parseInt(request.getParameter("id"));
        KhachHang existingKhachHang = khDAO.selectKhachHang(id);
        RequestDispatcher  dispatcher = request.getRequestDispatcher("khachhang-form.jsp");
        request.setAttribute("kh", existingKhachHang);
        dispatcher.forward(request, response);
	}

	private void deleteKhachHang(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            khDAO.deleteKhachHang(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	private void insertKhachHang(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        //int id = Integer.parseInt(request.getParameter("id"));
        String tenkhachhang = request.getParameter("tenkhachhang");
        String ngaysinh = request.getParameter("ngaysinh");
        String cmnd = request.getParameter("cmnd");
        String quequan = request.getParameter("quequan");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        KhachHang kh = new KhachHang(tenkhachhang, ngaysinh, cmnd, quequan, email, sdt);
        try {
            khDAO.insertKhachHang(kh);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.sendRedirect("list");
	}

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher  dispatcher = request.getRequestDispatcher("khachhang-form.jsp");
        dispatcher.forward(request, response);
    }
}

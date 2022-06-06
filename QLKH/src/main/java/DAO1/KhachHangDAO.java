package DAO1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.KhachHang;

public class KhachHangDAO{

	private static final String INSERT_KHACHHANG_SQL = "INSERT INTO khachhang" + " (tenkhachhang, ngaysinh, cmnd, quequan, email, sdt) VALUES " + " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_KHACHHANG_BY_ID = "select * from khachhang where id =?";
	private static final String SELECT_ALL_KHACHHANG = "select * from khachhang";
	private static final String DELETE_KHACHHANG_SQL = "delete from khachhang where id = ?;";
	private static final String UPDATE_KHACHHANG_SQL = "update khachhang set tenkhachhang = ?,ngaysinh= ?, cmnd =?, quequan =?, email =?, sdt =? where id = ?;";
	
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3307/anhquan?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

	public KhachHangDAO() {
	}
	
	protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

	public void insertKhachHang(KhachHang khachhang) throws SQLException  {
		System.out.println(INSERT_KHACHHANG_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KHACHHANG_SQL)) {
				preparedStatement.setString(1,khachhang.gettenkhachhang());
				preparedStatement.setString(2, khachhang.getNgaysinh());
				preparedStatement.setString(3, khachhang.getCmnd());
				preparedStatement.setString(4, khachhang.getQuequan());
				preparedStatement.setString(5, khachhang.getEmail());
				preparedStatement.setString(6, khachhang.getSdt());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
	}

	public KhachHang selectKhachHang(int id) {
		KhachHang khachhang = null;
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KHACHHANG_BY_ID);) 
        {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String tenkhachhang = rs.getString("tenkhachhang");
				String ngaysinh = rs.getString("ngaysinh");
				String cmnd = rs.getString("cmnd");
				String quequan = rs.getString("quequan");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				khachhang = new KhachHang(id, tenkhachhang, ngaysinh, cmnd, quequan, email, sdt);
			}
		} catch (SQLException e) {
		    printSQLException(e);
		}
	    return khachhang;
	}

	public List<KhachHang> selectAllKhachHang() {
		List<KhachHang> khachhangs = new ArrayList<>();
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_KHACHHANG);) 
        {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String tenkhachhang = rs.getString("tenkhachhang");
				String ngaysinh = rs.getString("ngaysinh");
				String cmnd = rs.getString("cmnd");
				String quequan = rs.getString("quequan");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				khachhangs.add(new KhachHang(id, tenkhachhang,ngaysinh, cmnd, quequan, email, sdt));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		    return khachhangs;
	}

	public boolean deleteKhachHang(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_KHACHHANG_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateKhachHang(KhachHang khachhang) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_KHACHHANG_SQL);) {
			statement.setString(1, khachhang.gettenkhachhang());
			statement.setString(2, khachhang.getNgaysinh());
			statement.setString(3, khachhang.getCmnd());
			statement.setString(4, khachhang.getQuequan());
			statement.setString(5, khachhang.getEmail());
			statement.setString(6, khachhang.getSdt());
			statement.setInt(7, khachhang.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());	
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
	}
	
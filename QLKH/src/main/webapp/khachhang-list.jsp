<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Quản Lý Khách Hàng</title>
	<link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand">Quản Lý </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Khách hàng</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<h3 class="text-center">Danh sách khách hàng</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Thêm Khách Hàng Mới</a>
			</div>
		<br>
		<table class="table table-bordered">
			<thead>
			
				<tr>
					<th>ID</th>
					<th>Tên Khách Hàng</th>
					<th>Ngày Sinh</th>
					<th>CMND</th>
					<th>Quê Quán</th>
					<th>Email</th>
					<th>SĐT</th>
					<th>Thao Tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listKH}" var="kh">
					<tr>
						<td><c:out value="${kh.id}" /></td>
						<td><c:out value="${kh.tenkhachhang}"/></td>
						<td><c:out value="${kh.ngaysinh}" /></td>
						<td><c:out value="${kh.cmnd}" /></td>
						<td><c:out value="${kh.quequan}" /></td>
						<td><c:out value="${kh.email}" /></td>
						<td><c:out value="${kh.sdt}" /></td>
						<td>
							<a href="edit?id=<c:out value='${kh.id}' />" >Sửa</a>
							&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="delete?id=<c:out value='${kh.id}' />">Xoá</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>
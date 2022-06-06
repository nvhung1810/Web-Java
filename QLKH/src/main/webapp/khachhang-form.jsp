<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Quản Lý Khách Hàng</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbarbrand">Quản Lý</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Khách hàng</a></li>

			</ul>
		</nav>
	</header>
<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${kh != null}">
					<form action="update" method="post" >
				</c:if>
				<c:if test="${kh == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${kh != null}">Sửa thông tin khách hàng</c:if>
						<c:if test="${kh == null}">Thêm khách hàng mới</c:if>
					</h2>
				</caption>
				<c:if test="${kh != null}">
					<input type="hidden" name="id" value="<c:out value='${kh.id}'/>" />
				</c:if>
				<fieldset class="form-group">
					<label>Tên khách hàng</label> 
					<input type="text" value="<c:out value='${kh.tenkhachhang}'/>" class="form-control" name="tenkhachhang" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Ngày sinh</label>
					<input type="date" value="<c:out value='${kh.ngaysinh}' />" class="form-control" name="ngaysinh">
				</fieldset>
				<fieldset class="form-group">
					<label>CMND</label> 
					<input type="text" value="<c:out value='${kh.cmnd}'/>" class="form-control" name="cmnd">
				</fieldset>
				<fieldset class="form-group">
					<label>Quê Quán</label> 
					<input type="text" value="<c:out value='${kh.quequan}'/>" class="form-control" name="quequan">
				</fieldset>
				<fieldset class="form-group">
					<label>Email</label> 
					<input type="text" value="<c:out value='${kh.email}'/>" class="form-control" name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>SĐT</label> <input type="text" value="<c:out value='${kh.sdt}'/>" class="form-control" name="sdt">
				</fieldset>
					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
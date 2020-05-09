<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Coffee Shop Management</title>
<link href="<c:url value='/assets/css/bootstrap.min.css' />" rel="stylesheet"></link> 
</head>
<body>
	<%--Header--%>	
	<jsp:include page="../PageLayout/page-header.jsp"></jsp:include>
	<br>
	<%--Body--%>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<form>
				<caption>
					<h2>
            			View Info User
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required" disabled>
				</fieldset>

				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" disabled>
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${user.country}' />" class="form-control"
						name="country" disabled>
				</fieldset>
				<a href="<%=request.getContextPath()%>/user/list" class="btn btn-light">Back</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
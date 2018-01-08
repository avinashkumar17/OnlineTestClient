<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/design.css" />" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/index.js'/>"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Login</title>
</head>
<body>
	<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="form-wrap">
					<h1>Log in with your email account</h1>
					<form role="form" method="post" id="login-form" autocomplete="off">
						<div class="form-group">
							<label for="email" class="sr-only">Email</label> <input
								type="text" name="email" id="email" class="form-control"
								placeholder="somebody@example.com">
						</div>
						<div class="form-group">
							<label for="key" class="sr-only">Password</label> <input
								type="password" name="key" id="key" class="form-control"
								placeholder="Password">
						</div>
						<span id="error" style="display: none; color: red;">Wrong
							email</span>
						<div class="checkbox">
							<span class="character-checkbox" onclick="showPassword()"></span>
							<span class="label">Show password</span>
						</div>
						<input type="button" onclick="hello()" id="btn-login"
							class="btn btn-custom btn-lg btn-block" value="Log in">
					</form>
					<a href="javascript:;" class="forget" data-toggle="modal"
						data-target=".forget-modal">Forgot your password?</a>
					<hr>
				</div>
			</div>
			<!-- /.col-xs-12 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container --> </section>

	<div class="modal fade forget-modal" tabindex="-1" role="dialog"
		aria-labelledby="myForgetModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Recovery password</h4>
				</div>
				<div class="modal-body">
					<p>Type your email account</p>
					<input type="email" name="recovery-email" id="recovery-email"
						class="form-control" autocomplete="off">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-custom">Recovery</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"
	type="text/javascript" language="javascript"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.3/js/bootstrap.js"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/dashboard.css" />" />
<script type="text/javascript" src="<c:url value='/js/dashboard.js'/>"></script>

 <link id="themecss" rel="stylesheet" type="text/css" href="//www.shieldui.com/shared/components/latest/css/light/all.min.css" />
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>

<title>Dashboard</title>
</head>
<body onload="hello()">
	<div class="tab">
		<button class="tablinks" onclick="openCity(event, 'London')">London</button>
		<button class="tablinks" onclick="openCity(event, 'Paris')">Paris</button>
		<button class="tablinks" onclick="openCity(event, 'Tokyo')">Tokyo</button>
	</div>
	<div id="London" class="tabcontent">
		Add Category : <input type="text" name="addcategory" id="addCategory">
		<input type="button" onclick="insertCategory()" value="Add"> <a
			class="editor_create">Create new record</a>
			<table id="one">
	<tbody>

    </tbody>
    
    <div id="grid"></div>
    
    </table>
	</div>
	<div id="Paris" class="tabcontent">
		<h3>Paris</h3>
		<p>Paris is the capital of France.</p>
	</div>
	<div id="Tokyo" class="tabcontent">
		<h3>Tokyo</h3>
		<p>Tokyo is the capital of Japan.</p>
	</div>
</body>
</html>
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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/dashboard.css" />" />
<link id="themecss" rel="stylesheet" type="text/css"
	href="//www.shieldui.com/shared/components/latest/css/light/all.min.css" />
<script type="text/javascript"
	src="//www.shieldui.com/shared/components/latest/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/dashboard.js'/>"></script>
<title>Dashboard</title>
</head>
<body onload="hello()">
	<div class="tab">
		<button class="tablinks" onclick="openCity(event, 'London')">Category</button>
		<button class="tablinks" onclick="openCity(event, 'Paris')">Levels</button>
		<button class="tablinks" onclick="openCity(event, 'Tokyo')">Questions</button>
	</div>
	<div id="London" class="tabcontent">
		<!-- Add Category : <input type="text" name="addcategory" id="addCategory">
		<input type="button" onclick="insertCategory()" value="Add"> <a
			class="editor_create">Create new record</a> -->
		<table id="one">
			<tbody>
			</tbody>
			<div id="grid"></div>
		</table>
	</div>
	<div id="Paris" class="tabcontent">
		<input type="hidden" name="count" value="1" />
		<form class="addlevel" action="addNewLevels" method="post">
			<label for="sel1">Select list (select one):</label> <select
				class="form-control" id="sel1" name="level">

			</select>
			<div id="field">
				<input autocomplete="off" class="input" id="field1" name="prof1"
					type="text" placeholder="Type something" data-items="8" />
				<button id="b1" class="btn add-more" type="button">+</button>
			</div>
			<input type="submit" value="submit" /> <br> <small>Press
				+ to add another form field :)</small><br>
		</form>
		<div id="grid_level"></div>
	</div>
	<div id="Tokyo" class="tabcontent">
		<div class="addlevel">
			<p>Choose Category Here:</p>
			<select id="categorydrop"></select> <br /> <br />
			<p>Choose Levels Here:</p>
			<select id="leveldrop"></select> <br /> <br />
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h3>Questions :</h3>
					<div class="tabbable-panel">
						<div class="tabbable-line">
							<ul class="nav nav-tabs ">
								<li class="active"><a href="#tab_default_1"
									data-toggle="tab"> Add Questions </a></li>
								<li><a href="#tab_default_2" data-toggle="tab"> Add
										Questions by excel file </a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab_default_1">
									<div id="QuestionsGroup">
										<div id='newQuestion1'>
											<p class='question' id='newQuestion1'>
												Q1. <input type='text' size='100' name='question'
													id='question1'></input><br />
											</p>
											<ul class='answers'>
												<br />
												<input type='radio' name='q1' value='a' id='q1a1' /> a)
												<input type='text' name='answer1' for='q1a1' id='choicea1'></input>
												<br />
												<br />
												<input type='radio' name='q1' value='b' id='q2b1' /> b)
												<input type='text' name='answer2' for='q2a1' id='choiceb1'></input>
												<br />
												<br />
												<input type='radio' name='q1' value='c' id='q3c1' /> c)
												<input type='text' name='answer3' for='q3a1' id='choicec1'></input>
												<br />
												<br />
												<input type='radio' name='q1' value='d' id='q4d1' /> d)
												<input type='text' name='answer4' for='q4a1' id='choiced1'></input>
												<br />
											</ul>
										</div>
									</div>
									<input type='button' value='Add Button' id='addButton'>
									<input type='button' value='Remove Button' id='removeButton'>
									<input type='button' value='submit' id='submitQuestions'>
								</div>
								<div class="tab-pane" id="tab_default_2">
									<div class="addlevel">
										<br /> <input type="file" name="files" id="files" /><br />
										<br /> <input type="button" value="Submit" id="btnSubmit" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>
	<!-- <input type='button' value='Log Out' id='logout'> -->
	<a href = "http://localhost:9000/onlineclient/logout">Log Out</a>
	
</body>
</html>
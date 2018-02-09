var editor, tmp;
var obj = [];

function openCity(evt, cityName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	edit();
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}
function clearQuestion() {
	$('input[name="question"]').val('');
	$('input[name="answer1"]').val('');
	$('input[name="answer2"]').val('');
	$('input[name="answer3"]').val('');
	$('input[name="answer4"]').val('');
	$("input:radio").removeAttr("checked");

}
function externalFunction() {
	var options = "<p class='question' id='paragraph1'>Q1. <input type='text'  size='100' name='question' ></input><br/></p>"
			+ "<ul class='answers'>"
			+ "<br/><input type='radio' name='q1' value='a' id='q1a'> a) <input type='text' name='answer1' for='q1a'></input><br/>"
			+ "<br/><input type='radio' name='q1' value='b' id='q2a'> b) <input type='text' name='answer2' for='q2a'></input><br/>"
			+ "<br/><input type='radio' name='q1' value='c' id='q3a'> c) <input type='text' name='answer3' for='q3a'></input><br/>"
			+ "<br/><input type='radio' name='q1' value='d' id='q4a'> d) <input type='text' name='answer4' for='q4a'></input><br/></ul>";
	$("#questions").append(options);

}
function hello() {
	var h = document.getElementById("London");
	h.style.display = "block";
}

function edit() {

	$('#sel1').empty();
	$.ajax({
		url : "getCategory",
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, data) {
				$('#sel1').append($('<option>', {
					value : data.id,
					text : data.category
				}));
			});
		},
		error : function(err) {
			console.log('error' + err);
		}
	});
}

/*
 * $(function() { $('a[title]').tooltip(); });
 */

function selectCategory(id) {
	$.ajax({
		url : 'http://localhost:9000/onlineclient/getLevel',
		type : 'GET',
		dataType : 'text',
		data : {
			id : id
		},
		success : function(data) {
			var obj = $.parseJSON(data);
			var options = "";
			for (var i = 0; i < obj.length; i++) {
				options += "<option id='" + obj[i]['id'] + "' name='"
						+ obj[i]['id'] + "'>" + obj[i]['levelCategory']
						+ "</option>";
			}
			$("#leveldrop").html(options);
		},
		error : function(error) {
			console.log(error);
			alert("Error " + error);
		}
	});
}

function getCategory() {
	$.ajax({
		url : 'http://localhost:9000/onlineclient/getCategory',
		type : 'GET',
		dataType : 'text',
		success : function(data) {
			var obj = $.parseJSON(data);
			var options = "<option selected disabled>Choose here</option>";
			for (var i = 0; i < obj.length; i++) {
				options += "<option " + "id='" + obj[i]['id'] + "' name='"
						+ obj[i]['id'] + "'>" + obj[i]['category']
						+ "</option>";
			}
			$("#categorydrop").html(options);
		},
		error : function(error) {
			console.log(error);
			alert("Error " + error);
		}
	});
}

function getLevelusingCategory() {
	$("#grid_level")
			.shieldGrid(
					{
						dataSource : {
							remote : {
								read : "http://localhost:9000/onlineclient/getAllLevels",
								modify : {
									/*
									 * create : { url :
									 * "http://localhost:9000/onlineclient/addCategory",
									 * type : "post", data : function(edited) {
									 * return { category :
									 * edited[0].data.category }; } },
									 */
									update : {
										url : "http://localhost:9000/onlineclient/editLevel",
										type : "post",
										data : function(edited) {
											return {
												levelCategory : edited[0].data.levelCategory,
												id : edited[0].data.id
											};
										}
									},
									remove : {
										url : "http://localhost:9000/onlineclient/removeLevel",
										type : "post",
										data : function(removed) {

											return {
												id : removed[0].data.id
											};
										}
									}
								}
							},
							schema : {
								fields : {
									levelCategory : {
										path : "levelCategory",
										type : String
									},
									id : {
										path : "id",
										type : String
									},
									category : {
										path : "category.category",
										type : String
									}

								}
							}
						},
						paging : {
							pageSize : 5
						},
						rowHover : false,
						columns : [ {
							field : "levelCategory",
							title : "Level"
						}, {
							field : "category",
							title : "Category"
						}, {
							width : 150,
							title : " ",
							buttons : [ {
								commandName : "edit",
								caption : "Edit"
							}, {
								commandName : "delete",
								caption : "Delete"
							} ]
						} ],
						editing : {
							enabled : true,
							mode : "popup",
							confirmation : {
								"delete" : {
									enabled : true,
									template : function(item) {
										return "Delete product with name '"
												+ item.category + "'?";
									}
								}
							}
						},
					/*
					 * toolbar : [ { buttons : [ { commandName : "insert",
					 * caption : "Add" } ], position : "top" } ]
					 */
					});
	$("input[type='text'][name='category']").replaceWith(function() {
		return '<span class=' + this.className + '>' + this.value + '</span>'
	});
}
$(document)
		.ready(
				function() {

					$("#grid")
							.shieldGrid(
									{
										dataSource : {
											remote : {
												read : "getCategory",
												modify : {
													create : {
														url : "http://localhost:9000/onlineclient/addCategory",
														type : "post",
														data : function(edited) {
															return {
																category : edited[0].data.category
															};
														}
													},
													update : {
														url : "http://localhost:9000/onlineclient/editCategory",
														type : "post",
														data : function(edited) {
															return {
																category : edited[0].data.category,
																id : edited[0].data.id
															};
														}
													},
													remove : {
														url : "http://localhost:9000/onlineclient/remove",
														type : "post",
														data : function(removed) {
															return {
																id : removed[0].data.id
															};
														}
													}
												}
											},
											schema : {
												fields : {
													category : {
														path : "category",
														type : String
													},
													id : {
														path : "id",
														type : String
													}
												}
											}
										},
										paging : {
											pageSize : 5
										},
										rowHover : false,
										columns : [ {
											field : "category",
											title : "Category"
										}, {
											width : 150,
											title : " ",
											buttons : [ {
												commandName : "edit",
												caption : "Edit"
											}, {
												commandName : "delete",
												caption : "Delete"
											} ]
										} ],
										editing : {
											enabled : true,
											mode : "popup",
											confirmation : {
												"delete" : {
													enabled : true,
													template : function(item) {
														return "Delete product with name '"
																+ item.category
																+ "'?";
													}
												}
											}
										},
										toolbar : [ {
											buttons : [ {
												commandName : "insert",
												caption : "Add"
											} ],
											position : "top"
										} ]
									});

					getCategory();
					// externalFunction();
					$("#categorydrop").change(function() {
						var id = $(this).children(":selected").attr("id");
						selectCategory(id);
					});
					getLevelusingCategory();

					var counter = 2;
					$("#addButton")
							.click(
									function() {

										var newQuestion = $(
												document.createElement('div'))
												.attr("id",
														'newQuestion' + counter);
										var options = "<p class='question' id='paragraph1'>Q"
												+ counter
												+ ". <input type='text'  size='100' name='question' id='question"
												+ counter
												+ "' ></input><br/></p>"
												+ "<ul class='answers'>"
												+ "<br/><input type='radio' name='q"
												+ counter
												+ "' value='a' id='q1a'"
												+ counter
												+ "> a) <input type='textbox' name='answer1' id='choicea"
												+ counter
												+ "' for='q1a"
												+ counter
												+ "'></input><br/>"
												+ "<br/><input type='radio' name='q"
												+ counter
												+ "' value='b' id='q2b'"
												+ counter
												+ "> b) <input type='textbox' name='answer2' id='choiceb"
												+ counter
												+ "' for='q2b"
												+ counter
												+ "'></input><br/>"
												+ "<br/><input type='radio' name='q"
												+ counter
												+ "' value='c' id='q3c'"
												+ counter
												+ "> c) <input type='textbox' name='answer3' id='choicec"
												+ counter
												+ "' for='q3c"
												+ counter
												+ "'></input><br/>"
												+ "<br/><input type='radio' name='q"
												+ counter
												+ "' value='d' id='q4d'"
												+ counter
												+ "> d) <input type='textbox' name='answer4' id='choiced"
												+ counter
												+ "' for='q4d"
												+ counter
												+ "'></input><br/></ul>";

										newQuestion.after().html(options);
										newQuestion.appendTo("#QuestionsGroup");
										counter++;
									});
					$("#removeButton").click(function() {
						if (counter == 2) {
							alert("No question/answer choices to remove");
							return false;
						}
						counter--;
						$("#newQuestion" + counter).remove();
					});

					$("#btnSubmit")
							.click(
									function(event) {

										if (document.getElementById("files").value == "") {
											alert("Please upload a file.");
											return;
										}

										var idVal = $(
												"#categorydrop option:selected")
												.attr("name");
										var levelVal = $(
												"#leveldrop option:selected")
												.attr("name");
										var g = {
											id : "3",
											levelId : levelVal,
											items : obj
										};
										console.log("IdVal: " + idVal
												+ " LevelID: " + levelVal);
										event.preventDefault();
										var form = $('#files').prop('files')[0];
										var data = new FormData();
										data.append("file", form);
										data.append("id", idVal);
										data.append("levelid", levelVal);
										$("#btnSubmit").prop("disabled", true);
										$
												.ajax({
													url : 'http://localhost:9000/onlineclient/submitfile',
													type : "POST",
													dataType : "text",
													data : data,
													processData : false,
													contentType : false,
													cache : false,
													success : function(data) {
														console.log("File");
														alert(data);
													},
													error : function(error) {
														alert(error);
														console.log("Error : "
																+ error);
													}
												});
									});

					var next = 1;
					$(".add-more")
							.click(
									function(e) {
										e.preventDefault();
										var addto = "#field" + next;
										var addRemove = "#field" + (next);
										next = next + 1;
										var newIn = '<input autocomplete="off" class="input" id="field'
												+ next
												+ '" name="prof1" type="text">';
										var newInput = $(newIn);
										var removeBtn = '<button id="remove'
												+ (next - 1)
												+ '" class="btn btn-danger remove-me" >-</button></div><div id="field">';
										var removeButton = $(removeBtn);
										$(addto).after(newInput);
										$(addRemove).after(removeButton);
										$("#field" + next).attr('data-source',
												$(addto).attr('data-source'));
										$("#count").val(next);

										$('.remove-me')
												.click(
														function(e) {
															e.preventDefault();
															var fieldNum = this.id
																	.charAt(this.id.length - 1);
															var fieldID = "#field"
																	+ fieldNum;
															$(this).remove();
															$(fieldID).remove();
														});
									});

					$("#submitQuestions")
							.click(
									function() {
										obj = [];
										for (i = 1; i < counter; i++) {
											console.log(counter);
											var radioanswer = 'none';
											var question = '';
											var choiceA, choiceB, choiceC, choiceD;
											var Answer = '';
											if ($(
													'input[name=q' + i
															+ ']:checked')
													.val() != null) {
												question = $('#question' + i)
														.val();
												radioanswer = $(
														'input[name=q' + i
																+ ']:checked')
														.val();
												Answer = $(
														'#choice' + radioanswer
																+ i).val();
												console.log("Ques: " + question
														+ " Ans: " + Answer);
												choiceA = $('#choicea' + i)
														.val();
												choiceB = $('#choiceb' + i)
														.val();
												choiceC = $('#choicec' + i)
														.val();
												choiceD = $('#choiced' + i)
														.val();
												// console.log("A: "+choiceA+"
												// B: "+choiceB+" C: "+choiceC+"
												// D: "+choiceD);

												tmp = {
													'question' : question,
													'answer' : Answer,
													'choice_a' : choiceA,
													'choice_b' : choiceB,
													'choice_c' : choiceC,
													'choice_d' : choiceD
												};

												obj.push(tmp);
											}

										}
										var idVal = $(
												"#categorydrop option:selected")
												.attr("name");
										var levelVal = $(
												"#leveldrop option:selected")

										.attr("name");
										if (obj.length == 0) {
											alert("Empty Question/answers/radio values");
											return;
										}
										var g = {
											id : idVal,
											levelId : levelVal,
											items : obj
										};
										console.log("val" + JSON.stringify(g));

										$
												.ajax({
													url : 'http://localhost:9000/onlineclient/addQuestion',
													type : 'POST',
													dataType : 'text',
													contentType : 'application/json;charset=utf-8',
													data : JSON.stringify(g),
													success : function(data) {
														console.log("AJAX "
																+ data);
														clearQuestion();
														alert("Added Successfully");

													},
													error : function(error) {
														console.log("Error "
																+ error);
														alert("Error adding..."
																+ error);

													}
												});
									});

					$(':input[type="submit"]').prop('disabled', true);
					$('input[name="prof1"]').keyup(function() {
						if ($(this).val() != '') {
							$(':input[type="submit"]').prop('disabled', false);
						}
					});

					$(':input[id="submitQuestions"]').prop('disabled', true);
					$(':input[id="btnSubmit"]').prop('disabled', true);

					$("#categorydrop").change(
							function() {
								$(':input[id="submitQuestions"]').prop(
										'disabled', false);
								$(':input[id="btnSubmit"]').prop('disabled',
										false);
							});

					/*$("#logout").click(function() {
						
					});*/
				});
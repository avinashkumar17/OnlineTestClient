var editor;

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
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}
function hello() {
	var h = document.getElementById("London");
	h.style.display = "block";
	$.ajax({
		url : "http://localhost:8080/online/getcv",
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, value) {
				console.log(value);
				$('#one tbody').append(
						"<tr><td>" + value.category + "</td><td>" + value.id
								+ "</td><td>"+' </td><td><input type="button" value="Remove" onclick="test(this.id)" id="'
								+ value.category + '"></td></tr>'+' </td><td><input type="button" value="Edit" onclick="edit(this.id)" id="'
								+ value.category + '"></td></tr>');
			});
		},
		error : function(err) {
			console.log('error' + err);
		}
	});
}
function test(id) {
	$('#' + id).closest('tr').remove();
}

function edit(id) {
	
}

function insertCategory() {
	var category = $('#addCategory').val();

	$.ajax({
		url : 'http://localhost:9000/onlineclient/addCategory',
		type : 'POST',
		dataType : 'text',
		data : {
			'category' : category
		},
		success : function(data) {
			alert("Added successfully: " + data);
		},
		error : function(error) {
			console.log(error);
			alert("Error " + error);

		}
	});
	/*$('#numbers').append('<tr><td> Category : </td></tr>');
	$('#numbers tr:last')
			.after(
					'<tr><td> '
							+ category
							+ ' </td><td><input type="button" value="Remove" onclick="test(this.id)" id="'
							+ category + '"></td></tr>');*/
}

$(document).ready(function() {
	$("#grid").shieldGrid({
        dataSource: {
            remote: {
                read: "http://localhost:9000/onlineclient/getCategory",
                modify: {
                    create: {
                        url: "/employees/EmployeeCreate",
                        type: "post",
                        data: function (edited) {
                            return {
                                Name: edited[0].data.category,
                                ID: edited[0].data.id
                            };
                        }
                    },
                    update: {
                        url: "http://localhost:9000/onlineclient/editCategory",
                        type: "post",
                        data: function (edited) {
                        	console.log(edited[0].data.id+" : "+edited[0].data.category);
                            return { 
                                category: edited[0].data.category,
                                id: edited[0].data.id
                            };
                        }
                    },
                    remove: {
                        url: "http://localhost:9000/onlineclient/remove",
                        type: "post",
                        data: function (removed) {
                        	
                            return {
                            	id: removed[0].data.id 
                            	};
                        }
                    }
                }
            },
            schema: {
                fields: {
                	category: { path: "category", type: String },
                    id: { path: "id", type: String }
                }
            }
        },
        paging: {
            pageSize: 5
        },
        rowHover: false,
        columns: [
            { field: "category", title: "Category"},
           
            {
                width: 150,
                title: " ",
                buttons: [
                    { commandName: "edit", caption: "Edit" },
                    { commandName: "delete", caption: "Delete" }
                ]
            }
        ],
        editing: {
            enabled: true,
            mode: "popup",
            confirmation: {
                "delete": {
                    enabled: true,
                    template: function (item) {
                        return "Delete product with name '" + item.category + "'?";
                    }
                }
            }
        },
        toolbar: [
            {
                buttons: [
                    { commandName: "insert", caption: "Add" }
                ],
                position: "top"
            }
        ]
    });
	

});
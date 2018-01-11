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
}
function test(id) {
    $('#'+id).closest('tr').remove(); 
}

function insertCategory() {
	var category = $('#addCategory').val();
	
	
	/*$('#numbers').append('<tr><td> Category : </td></tr>');
	$('#numbers tr:last')
			.after(
					'<tr><td> '
							+ category
							+ ' </td><td><input type="button" value="Remove" onclick="test(this.id)" id="'
							+ category + '"></td></tr>');*/
}
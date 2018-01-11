
	function showPassword() {
		var key_attr = $('#key').attr('type');
		if (key_attr != 'text') {
			$('.checkbox').addClass('show');
			$('#key').attr('type', 'text');
		} else {
			$('.checkbox').removeClass('show');
			$('#key').attr('type', 'password');
		}
	}
	function hello() {
		var email = $('#email').val();
		var password = $('#key').val();	
		$.ajax({
			  url: 'http://localhost:9000/onlineclient/signIn',
			  type: 'POST',
			  dataType: 'text',
			  data: { 
			        'username': email, 
			        'password': password
			    },
			  success: function(data) {
				  window.location.href = 'http://localhost:9000/onlineclient/dashboard';
			  },
			  error: function(error) {
				  console.log(error);
				  alert("Error "+error);
				  
			  }
			});
	}


$(document).ready(function(){

});
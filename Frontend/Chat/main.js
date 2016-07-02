$(document).ready(function(){
	$("#login").click(function(){

        var username = $("#username").val();
        var password = $("#password").val();

		 $.ajax({
            type: 'GET',
            url: 'http://localhost:4567/login?username=' + username + '&password=' + password,
            success: function(responseData, textStatus, jqXHR) {

                var obj = $.parseJSON(responseData);
                console.log(obj['uid']);
                if(obj['uid'] != undefined){
                    $(location).attr('href', 'http://localhost:8888/chat/loggedin.html?uid=' + obj['uid']);
                }
            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }
        });
	});
});
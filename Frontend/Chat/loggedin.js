$(document).ready(function(){
        
    $(document).keypress(function(e){
        if((e.which || e.keyCode) == 13){
            console.log("Enter pressed");
            $("#send").click();
            //return false;
        }
    });


    retrieveChatrooms();
    
    $('#joinroom').on('click', function(){
        var roomnumber = $('#roomnumber').val();
        var uid = getUrlParameter('uid');

        joinChatroom(roomnumber, uid);
    });

    $('.chatrooms').on('click', '.room', function(){

        var roomnumber = $(this).text();
        console.log("roomnumber: " + roomnumber);

        retrieveChat(roomnumber);

        window.globalRoomnumber = roomnumber;
    });


    $('#send').on('click', function(){
        var message = $('#message').val();
        
        if(message == ''){
            return false;
        }

        sendMessage(message);
    });


    setInterval(function(){
        console.log("setTimeout Called");
        if(window.globalRoomnumber != undefined){
            var roomnumber = globalRoomnumber;
            retrieveChat(roomnumber);
            console.log("Chat updated");
        }
    }, 4000);


    $('.chatwindow').bind('DOMNodeInserted', function(){
        $(this).find('.latest').removeClass('latest');
        $(this).find('.message:last-child').addClass('latest');
    });

});


function scrollToLatest(){
    var container = $('.chatwindow'),
    scrollTo = $('.latest');


    container.scrollTop(
        scrollTo.offset().top - container.offset().top + container.scrollTop()
    );

}

function joinChatroom(roomnumber, uid){
     $.ajax({
        type: 'GET',
        url: 'http://localhost:4567/joinroom?roomnumber=' + roomnumber + '&uid=' + uid,
        success: function(responseData, textStatus, jqXHR) {
            var obj = $.parseJSON(responseData);
            console.log(obj);
            
            $('#roomnumber').val('');

            retrieveChatrooms();

        },
        error: function(xhr, status, error) {
            alert(xhr.responseText);
        }
    });
}

function retrieveChatrooms(){
    var uid = getUrlParameter('uid');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:4567/retrievechatrooms?uid=' + uid,
        success: function(responseData, textStatus, jqXHR) {

            var obj = $.parseJSON(responseData);
            console.log(obj);

            $('.chatrooms').empty();

            $.each(obj, function(i, val){
                $('.chatrooms').append('<div class="room" data-roomnumber="' + val + '">' + val + '</div>');
            });
        },
        error: function(xhr, status, error) {
            alert(xhr.responseText);
        }
    });
}

function sendMessage(message){

    var roomnumber = globalRoomnumber;
    var uid = getUrlParameter('uid');


     $.ajax({
        type: 'GET',
        url: 'http://localhost:4567/sendmessage?message=' + message + '&roomnumber=' + roomnumber + '&uid=' + uid,
        success: function(responseData, textStatus, jqXHR) {
            var obj = $.parseJSON(responseData);
            console.log(obj);
            
            $('#message').val('');

            retrieveChat(window.globalRoomnumber);

            scrollToLatest();
        },
        error: function(xhr, status, error) {
            alert(xhr.responseText);
        }
    });

}

function retrieveChat(roomnumber){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:4567/retrievechat?roomnumber=' + roomnumber,
        success: function(responseData, textStatus, jqXHR) {

            var obj = $.parseJSON(responseData);
            console.log(obj);

            $('.chatwindow').empty();

            $.each(obj, function(key, val){
                $('.chatwindow').append('<div class="message"><p class="username">' + val.Username +'</p><p class="message-body">' + val.Message + '</p></div>');
            });
            

        },
        error: function(xhr, status, error) {
            alert(xhr.responseText);
        }
    });
}


function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};


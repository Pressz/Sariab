if ($.cookie("Id") == null )
    $( "#logout" ).hide();
else
    $( "#login" ).hide();

$('#logout').on('submit', function (event) {
    event.preventDefault();

    $.removeCookie("Id");
    $.removeCookie("Username");
    $.removeCookie("Password");
    $.removeCookie("IsActive");
    $.removeCookie("Type");
    
    $(".menu-bar").hide();
    $(".operator-menu").hide();
    $(".admin-menu").hide();
    Hi.load('login');
});

$('#login').on('submit', function (event) {
    event.preventDefault();

    $url = Hi.home() + "/userController.php"
    + "?LOGINHELLO=true"
    + "&Username=" + $("input[name=Username]").val()
    + "&Password=" + $("input[name=Password]").val();
    
    jQuery.get($url, function(data){
        $.cookie("Id", data.Id);
        $.cookie("Fullname", data.Firstname + " " + data.Lastname);
        $.cookie("Username", data.Username, { expires : 10 });
        $.cookie("Password", $("input[name=Password]").val(), { expires : 10 });
        $.cookie("Type", data.Type, { expires : 10 });
        Hi.message('خوش آمدید!');
        $(".menu-bar").show();
        if (data.Type == 'OPERATOR' || data.Type == 'ADMIN')
            $(".operator-menu").show();
        if (data.Type == 'ADMIN')
            $(".admin-menu").show();
        Hi.load('dashboard');
    }, "json").fail(function() {
        Hi.message('خطا در احراز هویت', true);
    });
});

document.getElementById("nav_register").addEventListener("click", function(event){
    event.preventDefault();
    Hi.load('register');
});
function register(){
    // $('input[name=Username]').mask('980000000000', {placeholder: "98__________"});
}

$('#register').on('submit', function (event) {
    event.preventDefault();
    $.post( Hi.home() + "/userController.php",  $( "#register" ).serialize(), function( data ) {
        Hi.message('ثبت نام با موفقیت انجام شد', false);
        Hi.load('login');
    }, "json");
});

document.getElementById("nav_login").addEventListener("click", function(event){
    event.preventDefault();
    Hi.load('login');
});
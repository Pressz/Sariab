Hi.auth('ADMIN');
function users_activate(id){
    var act = 1;

    $.get(Hi.home() + "/userController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol()
    , function(data){
        if (data.IsActive == 1)
            act = 0;
        
        $.put( Hi.home() + "/userController.php" + "?" + Hi.loginprotocol(), 
        {
            IsActive: act,
            previousId: id,
        },
        function( data ) {
            Hi.message('حساب کاربر ' + (act == 0 ? 'غیر' : '') + ' فعال شد', act == 0);
            Hi.load('users');
        }, "json");
    });
}
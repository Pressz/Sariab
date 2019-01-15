Hi.auth('ADMIN');
var mode = 'edit';

function users_edit(id){
    if (id == null)
    {
        mode = 'insert';
        $('#delete').hide();
        $('#edit').hide();
    }
    else{
        $('#insert').hide();
    }
    url = Hi.home() + "/userController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol();

    $.get(url, function(data){ 
        $('input[name="previousId"]').val(data.Id);
        $('input[name="Username"]').val(data.Username);
        $('input[name="Firstname"]').val(data.Firstname);
        $('input[name="Lastname"]').val(data.Lastname);
        $('input[name="NationalCode"]').val(data.NationalCode);
        $('input[name="Type"]').val(data.Type);
    });
}

var url = Hi.home() + "/userController.php" + "?" + Hi.loginprotocol();

$('#edit').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.put(url, res,
        function( data ) {
            Hi.message('کاربر ویرایش شد', false);
            Hi.load('users');
        }, "json")
    );
});
$('#delete').on('click', function (event) {
    event.preventDefault();
    $.delete(url, $("form").find('input[name=previousId]').serialize(),
    function( data ) {
        Hi.message('کاربر حذف شد', false);
        Hi.load('users');
    }, "json");
});
$('#insert').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('کاربر ایجاد شد', false);
            Hi.load('users');
        }, "json")
    );
});
$('#cancel').on('click', function (event) {
    event.preventDefault();
    Hi.load('users');
});
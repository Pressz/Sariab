Hi.auth('OPERATOR');
var mode = 'edit';

function posts_edit(id){
    if (id == null)
    {
        mode = 'insert';
        $('#delete').hide();
        $('#edit').hide();
        $('#duplicate').hide();
    }
    else{
        $('#insert').hide();
    }
    url = Hi.home() + "/postController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol();

    $.get(url, function(data){ 
        $('input[name="previousId"]').val(data.Id);
        $('input[name="Title"]').val(data.Name);
        $('input[name="Description"]').val(data.Material);
        // TODO: Type
        // TODO: Image
    });
}

var url = Hi.home() + "/postController.php" + "?" + Hi.loginprotocol();

$('#edit').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.put(url, res,
        function( data ) {
            Hi.message('پست ویرایش شد', false);
            Hi.load('posts');
        }, "json")
    );
});
$('#delete').on('click', function (event) {
    event.preventDefault();
    $.delete(url, $("form").find('input[name=previousId]').serialize(),
    function( data ) {
        Hi.message('پست حذف شد', false);
        Hi.load('posts');
    }, "json");
});
$('#insert').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('پست ارسال شد', false);
            Hi.load('posts');
        }, "json")
    );
});
$('#duplicate').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('پست کپی شد', false);
            Hi.load('posts');
        }, "json")
    );
});
$('#cancel').on('click', function (event) {
    event.preventDefault();
    Hi.load('posts');
});
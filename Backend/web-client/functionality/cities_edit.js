Hi.auth('OPERATOR');
var mode = 'edit';

function cities_edit(id){
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
    url = Hi.home() + "/cityController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol();

    $.get(url, function(data){ 
        $('input[name="previousId"]').val(data.Id);
        $('input[name="Name"]').val(data.Name);
        $('input[name="Country"]').val(data.Country);
        // TODO: Image
    });
}

var url = Hi.home() + "/cityController.php" + "?" + Hi.loginprotocol();

$('#edit').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.put(url, res,
        function( data ) {
            Hi.message('شهر ویرایش شد', false);
            Hi.load('cities');
        }, "json")
    );
});
$('#delete').on('click', function (event) {
    event.preventDefault();
    $.delete(url, $("form").find('input[name=previousId]').serialize(),
    function( data ) {
        Hi.message('شهر حذف شد', false);
        Hi.load('cities');
    }, "json");
});
$('#insert').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('شهر ارسال شد', false);
            Hi.load('cities');
        }, "json")
    );
});
$('#cancel').on('click', function (event) {
    event.preventDefault();
    Hi.load('cities');
});
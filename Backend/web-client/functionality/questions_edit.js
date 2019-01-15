Hi.auth('ADMIN');
var mode = 'edit';

function questions_edit(id){
    if (id == null)
    {
        mode = 'insert';

        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;
        var today = year + "-" + month + "-" + day;

        $('input[name="Submit"]').val(today);

        $('#delete').hide();
        $('#edit').hide();
    }
    else{
        $('#insert').hide();
    }
    url = Hi.home() + "/questionController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol();

    $.get(url, function(data){ 
        $('input[name="previousId"]').val(data.Id);
        $('input[name="Title"]').val(data.Title);
        $('input[name="Submit"]').val(data.Submit);
        $('input[name="Choice1"]').val(data.Choice1);
        $('input[name="Choice2"]').val(data.Choice2);
        $('input[name="Choice3"]').val(data.Choice3);
        $('input[name="Choice4"]').val(data.Choice4);
    });
}

var url = Hi.home() + "/questionController.php" + "?" + Hi.loginprotocol();

$('#edit').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.put(url, res,
        function( data ) {
            Hi.message('سوال ویرایش شد', false);
            Hi.load('questions');
        }, "json")
    );
});
$('#delete').on('click', function (event) {
    event.preventDefault();
    $.delete(url, $("form").find('input[name=previousId]').serialize(),
    function( data ) {
        Hi.message('سوال حذف شد', false);
        Hi.load('questions');
    }, "json");
});
$('#insert').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('سوال ارسال شد', false);
            Hi.load('questions');
        }, "json")
    );
});
$('#cancel').on('click', function (event) {
    event.preventDefault();
    Hi.load('questions');
});
Hi.auth('OPERATOR');
var mode = 'edit';

function branches_edit(id){
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
    url = Hi.home() + "/branchController.php"
    + "?Id=" + id
    + "&" + Hi.loginprotocol();

    $.get(url, function(data){ 
        $('input[name="previousId"]').val(data.Id);
        $('input[name="Name"]').val(data.Name);
        $('input[name="CityId"]').val(data.CityId);
        $('input[name="UserId"]').val(data.UserId);
        // TODO: Image
    });



    $('input[name="UserID"]').flexdatalist({
        searchContain: false,
        textProperty: '{Username}',
        valueProperty: 'Username',
        minLength: 1,
        focusFirstResult: true,
        selectionRequired: true,
        visibleProperties: ["Username", "Fullname"],
        searchIn: ["Username", "Fullname", "Id"],
        url: Hi.home() + '/BridgeController.php' + "?" + "Id=dw_users.sql" + "&Params=[param1:Username,param2:Firstname,param3:Lastname]&" + Hi.loginprotocol(),
        relatives: '#relative'
    });

}

var url = Hi.home() + "/branchController.php" + "?" + Hi.loginprotocol();

$('#edit').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.put(url, res,
        function( data ) {
            Hi.message('دانشکده ویرایش شد', false);
            Hi.load('branches');
        }, "json")
    );
});
$('#delete').on('click', function (event) {
    event.preventDefault();
    $.delete(url, $("form").find('input[name=previousId]').serialize(),
    function( data ) {
        Hi.message('دانشکده حذف شد', false);
        Hi.load('branches');
    }, "json");
});
$('#insert').on('click', function (event) {
    event.preventDefault();
    $( "form" ).serializeFiles().then(res =>
        $.post(url, res,
        function( data ) {
            Hi.message('دانشکده ارسال شد', false);
            Hi.load('branches');
        }, "json")
    );
});
$('#cancel').on('click', function (event) {
    event.preventDefault();
    Hi.load('branches');
});
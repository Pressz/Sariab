Hi.auth('ADMIN');

function users(){
    $('#search').flexdatalist({
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

$.get(Hi.home() + "/BridgeController.php?Id=dw_users1.sql"
+ "&" + Hi.loginprotocol() , function(data, status){ 
    if (JSON.stringify(data).charAt(0) == "{")
        data = JSON.parse("[" + JSON.stringify(data) + "]");

    data.forEach(obj => {
        var tr="<tr>";
        var td0 = "<td>" +
        // TODO: "<input type=\"button\" onclick=\"Hi.load('users_activate', " + obj["Id"] + ");\" value=\"" + ((obj["IsActive"] == '1')? "غیر فعال کردن" : "فعال کردن"  ) + "\">" + 
        "<input type=\"button\" onclick=\"Hi.load('users_edit', " + obj["Id"] + ");\" value=\"ویرایش\">" + 
        "</td>";
        var td ="<td>"+obj["Id"]+"</td>";
        td += "<td>"+obj["Firstname"]+"</td>";
        td += "<td>"+obj["Lastname"]+"</td>";
        td += "<td>"+obj["Username"]+"</td>";
        if (obj["BinImage"] != null)
        {
            td += '<td><img src="data:image/jpeg;base64,'+obj["BinImage"]+'" /></td>';
        }
        else{
            td += "<td>تصویر یافت نشد</td>";
        }
        td += "</tr>";
        $("#users").append(tr+td0+td);
    });
});
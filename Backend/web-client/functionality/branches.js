Hi.auth('ADMIN');

function branches(){

}

$.get(Hi.home() + "/branchController.php?" + Hi.loginprotocol() , function(data, status){ 
    if (JSON.stringify(data).charAt(0) == "{")
        data = JSON.parse("[" + JSON.stringify(data) + "]");

    data.forEach(obj => {
        var tr="<tr>";
        var td0 = "<td>" +
        "<input type=\"button\" onclick=\"Hi.load('branches_edit', " + obj["Id"] + ");\" value=\"ویرایش\">" + 
        "</td>";
        var td = "<td>"+obj["Name"]+"</td>";
        td += "<td>"+obj["CityId"]+"</td>";
        td += "<td>"+obj["UserId"]+"</td>";
        td += "</tr>";
        $("#branches").append(tr+td0+td);
    });
});
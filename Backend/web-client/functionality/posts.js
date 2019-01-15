Hi.auth('OPERATOR');

function posts(){

}

$.get(Hi.home() + "/postController.php?BinImage=✓&" + Hi.loginprotocol() , function(data, status){ 
    if (JSON.stringify(data).charAt(0) == "{")
        data = JSON.parse("[" + JSON.stringify(data) + "]");

    data.forEach(obj => {
        var tr="<tr>";
        var td0 = "<td>" +
        "<input type=\"button\" onclick=\"Hi.load('posts_edit', " + obj["Id"] + ");\" value=\"ویرایش\">" + 
        "</td>";
        var td ="<td>"+obj["Id"]+"</td>";
        td += "<td>"+obj["Title"]+"</td>";
        td += "<td>"+obj["Description"]+"</td>";
        if (obj["BinImage"] != null)
        {
            td += '<td><img src="data:image/jpeg;base64,'+obj["BinImage"]+'" /></td></tr>';
        }
        else{
            td += "<td>تصویر یافت نشد</td></tr>";
        }
        $("#posts").append(tr+td0+td);
    });
});
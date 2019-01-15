$.get(Hi.home() + "/postController.php?BinImage=✓&" + Hi.loginprotocol() , function(data, status){ 
    if (JSON.stringify(data).charAt(0) == "{")
        data = JSON.parse("[" + JSON.stringify(data) + "]");

    data.forEach(obj => {
        var img = "<span>تصویر یافت نشد</span>";
        if (obj["BinImage"] != null)
            img = '<img src="data:image/jpeg;base64,'+obj["BinImage"]+'" />';
        $(".content").append('<div>'
                + '<h1>' + obj["Name"] + '</h1>'
                + '<p>' + obj["Material"] + '</p>'
                + '<span>' + obj["Price"] + '</span>'
                + '<span>' + obj["Off"] + '</span>'
                + img
                + '</div>'
        );
    });
});
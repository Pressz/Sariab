client = {
    GET :function(url)
    {
        $.get(url, function(data, status){
            return data;
        });
    },
    POST :function(url)
    {
        // jQuery.post( url [, data ] [, success ] [, dataType ] )

        /* SAMPLE 1
        $.post( "test.php", { func: "getNameAndTime" }, function( data ) {
            console.log( data.name ); // John
            console.log( data.time ); // 2pm
          }, "json");
        */
        /* SAMPLE 2
          $.post( "test.php", $( "#testform" ).serialize() );
        */

    }
}
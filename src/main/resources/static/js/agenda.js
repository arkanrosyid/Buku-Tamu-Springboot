$(document).ready(function() {
   read();
});

function read(){
    $.ajax({
       type : "GET",
       url : '/getAllAgenda',
       dataType : "json",
       success: function (response) {
        //  console.log(response);
        $('#bodyA').empty();
        var i = 1;
          $.each(response, function (keys, item) {
            date2 = parseDate(item.date);
        //    console.log(item);
            $('#bodyA').append('<tr>\
                <td scope="row">'+ i++ +'</td>\
                <td>\
                    '+ item.name +'\
                <td>\
                '+ date2.toLocaleDateString('id-ID' ,  { weekday: 'long' }) + ", " + date2.toLocaleDateString('id-ID') +'\
                </td>\
                <td>\
                    <div class="btn-group" role="group" aria-label="Basic example">\
                        <a href="/agenda/play/'+item.id+'" type="button" class="btn btn-success"> <i class="fas fa-play-circle"></i> </a>\
                        <a href="/agenda/info/'+item.id+'" type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></a>\
                        <a type="button" class="btn btn-danger" onclick="Del('+item.id+')"><i class="fas fa-trash-alt"></i></a>\
                    </div>\
                </td>\
            </tr>');
           
        //    console.log(date2.toLocaleDateString('id-ID' ,  { weekday: 'long' }) + ", " + date2.toLocaleDateString('id-ID') );
          })
       }
    });
};

function parseDate(date){
      d = new Date(date);
      return d
}

function Del(id){
    url =  "/agenda/delete/"+id;
    $.ajax({
        type : "GET",
        url : url  ,
        dataType : "json",
        success : function (response){
            read();
        },

    });

}

function add() {
    nameA = $("#nameAgenda").val();
    dateA = Date($("dateAgenda").val());
    
    data = {
      
    };
    // console.log(data);
    document.getElementById("myForm").reset();
   
    $.ajax({
        type : "POST",
        url : "/agenda/tambah"  ,
        data :  JSON.stringify({
            "name" : nameA,
            "date" : dateA,
        }) , 
        dataType : "json",
        contentType: 'application/json',
        success : function (response){
            read();

            console.log(response);
            
        },
    });
}


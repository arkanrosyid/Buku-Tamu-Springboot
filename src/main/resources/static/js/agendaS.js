$(document).ready(function()  {
    
    // fix Tanggal
    var date = new Date($("#tglA").text());
    strDate = date.toLocaleDateString('id-ID' ,  { weekday: 'long' })+ ", " + date.toLocaleDateString('id-ID');
    $("#tglA").text(strDate);

    read();
})

function read(){
    id =  $("#idA").val();

    url =  "/getAllTamu/"+id;
    $.ajax({
        type : "GET",
        url : url  ,
        dataType : "json",
        success : function (response){

          $('#bodyT').empty();
          i = 1;
            $.each(response, function (keys, item) {
                dateT = new Date(item.date)
                    $('#bodyT').append(' <tr>\
                    <td>'+ i++ +'</td>\
                    <td>'+item.name+'</td>\
                    <td>'+item.agency+'</td>\
                    <td>'+item.email+'</td>\
                    <td style="width: 10%;">\
                    '+ dateT.toLocaleDateString('id-ID') + '\
                    ' + dateT.getHours() +':'+dateT.getMinutes()+':'+dateT.getSeconds()+ '</td>\
                    <td>\
                        <div class="btn-group" role="group" aria-label="Basic example">\
                            <a href="/tamu/info/'+item.id+'" type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></a>\
                            <a type="button" class="btn btn-danger" onclick="delTamu('+item.id+')"><i class="fas fa-trash-alt"></i></a>\
                        </div>\
                    </td>\
                </tr>');
            
                // console.log("a");
            })
        },  
    });

  
    
}

function delTamu(id) {
    url =  "/tamu/delete/"+id;
    $.ajax({
        type : "GET",
        url : url  ,
        dataType : "json",
        success : function (response){
            read();
        },

    });


   
}
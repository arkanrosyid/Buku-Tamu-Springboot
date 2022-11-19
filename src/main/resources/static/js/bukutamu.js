$(document).ready(function()  {
    // get data
   
    var dateB =  $("#date").val();
    var date = new Date(dateB);

    // fix text
    strDate = date.toLocaleDateString('id-ID' ,  { weekday: 'long' })+ ", " + date.toLocaleDateString('id-ID');
    info =  $("#information").empty().append(strDate);
    ket =  $("#ketInp").empty();
})

function addTamu(){
    namaInp = $("#namaInp").val();
    instansiInp = $("#instansiInp").val();
    emailInp = $("#emailInp").val();
    ketInp = $("#ketInp").val();
    id =  $("#id").val();
    dateTime = new Date();

    // console.log(dateTime);

    
    
   
    $.ajax({
        type : "POST",
        url : "/tamu/tambah"  ,
        data :  JSON.stringify({
            "name" : namaInp,
            "instansi" : instansiInp,
            "email" : emailInp,
            "desc" : ketInp,
            "date" : dateTime,
            "idAgenda" : id,
        }) , 
        dataType : "json",
        contentType: 'application/json',
        success : function (response){
            console.log(response);
            if (response == "success") {
                alert("Data berhasil direkap!!, Terima Kasih");
                
                document.getElementById("form").reset();
            } else {
                alert("Data belum lengkap!!");
            }
          
            // console.log(response);
            
        },
        error : function (params) {
            alert("Data gagal direkap!!, mohon ulang kembali");
        }
    });
}


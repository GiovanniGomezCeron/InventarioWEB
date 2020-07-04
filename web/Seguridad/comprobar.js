var boton = $("#iniciar");
boton.click(function (e) {
    e.preventDefault();
    boton.val("Espere...");
    var nombre = $("#usuario").val();
    var con = $("#con").val();
    
    //Enviando solicitud al servidor
    var direccion = "Usuarios?opcion=comprobar&&user=" + nombre + "&&password=" + con;
    window.location = direccion;


});
//$("#usuario").keyup(function () {
//    var valor = $("#usuario").val();
//    var t = $("#usuario").length;
//    for (i = 0; i < t; i++) {
//        if (isNaN(parseInt(valor.substring(i,i+1)))) {
//            $("#usuario").css({"background": "red"});
//        }else{
//            $("#usuario").css({"background": "green"});
//        }
//
//    }
//
//});

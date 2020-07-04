<%@include file="../Vistas-Categorias/ComprobarRegistro.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar contraseña</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <h1 class="text-center">Recuperar contraseña</h1>
        <article  class="text-center border rounded w-50 container">
            <label for="correo">
              Email  
            </label><br>
            <input type="text" name="correo" id="correo" class="input col-8 text-center" /> <br> 
            <button class="btn btn-success m-3" id="enviar">Aceptar</button>
        </article>
        <script>
            $("#enviar").click(function(e){
                e.preventDefault();
                var correo=$("#correo").val();
                var direccion="/Proyecto_final/Usuarios?opcion=recuperar&&correo="+correo;
                window.location=direccion;
            })
        </script>
    </body>
</html>

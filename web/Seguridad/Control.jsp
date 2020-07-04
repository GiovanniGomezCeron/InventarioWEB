<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de seguridad</title>
    </head>
    <body>
        <%String[] nameP = {"id", "nombre", "apellido", "usuario", "correo", "tipo", "estado"};
            int rifa = (int) Math.random() * 3;
        %>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <h3 class="text-center">Control de seguridad</h3>
        <p class="text-center">Responda a la siguiente interrogante</p>
        <article class="input rounded w-25 container text-center ">
            <label id="a"><%=request.getParameter("pregunta")%></label><br>
            <input type="text" class="input text-center" name="respuesta" id="respuesta"/>
            <input class="btn btn-info m-3" id="enviar" value="Enviar respuesta">
            <input type="hidden" name="id" value="<%=request.getParameter("id")%>" id="id"/>
        </article>
        <script>
            
            $("#enviar").click(function(e){
                e.preventDefault();
                $("#enviar").val("Espere...");
                var id=$("#id").val();
                var respuesta=$("#respuesta").val(); 
                var direccion="/Proyecto_final/Usuarios?opcion=pregunta&&id="+id+"&&respuesta="+respuesta;
                window.location=direccion;
            });
            
                 
        </script>
    </body>
</html>

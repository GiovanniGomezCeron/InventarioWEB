<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperación de contraseña</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <br>
        <article class="input rounded w-25 text-center container">
        <p>Su contraseña es:</p>
        <p id="contraseña"><%=request.getSession().getAttribute("Contraseña")%></p>
        <p id="cuenta">8</p>
        </article>
        <script>
            document.getElementById("cuenta");
            setInterval(contar,1000);
            var direccion="/Proyecto_final/index.jsp";
            function contar() {
                $("#cuenta").html($("#cuenta").html()-1)
                if($("#cuenta").html()==1){
                    window.location=direccion;
                }
            }
        </script>
    </body>
</html>

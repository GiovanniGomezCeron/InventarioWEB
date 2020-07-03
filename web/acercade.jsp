<%@include file="Vistas-Categorias/ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acerca de</title>
    </head>
    <body>
        <%@include file="WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <h1 class="text-center">Acerca de</h1>
        <article class="text-center">
        <%
            String[]nombres={"Erick Urrutia","Brayan Flores","Jonathan Recinos","Giovanni Gómez","Horacio Cuellar"};
            String[]descripcion={"Analista","Scrum Master","Developer Front-End","Developer Back-End","BD Developer "};
            for(int i=1; i<=5; i++){
                out.print("<article class='display'><img src=imagenes/foto"+i+".jpg class='m-3  fotos'>"
                        +" <p>"+nombres[i-1]+"</p><p><b>"+descripcion[i-1]+"</b></p></article>");
            }
    
        %>
        <P>ITCA-FEPADE Zacatecoluca</p>
        <p><b>Docente: Lic. Manuel de Jesús Gámez López</b></p>
        <P>Aplicación de metogologías ágiles y testeo de software</p>
        <p>Técnico en ingeniería de sistemas informáticos</p>
        </article>
    </body>
</html>

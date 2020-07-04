<%@include file="Vistas-Categorias/ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar PDF</title>
    </head>
    <body>
        <%@include file="WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <section class="text-center">
             <a href="PDF?pdf=1" class="w-25 display input rounded text-center p-2 m-3 hover nav-link">
                <h4>General</h4>
                <img src="imagenes/hospital.svg" class="icons-home"/>
            </a>
            <a href="PDF?pdf=2" class="w-25 display input rounded text-center p-2 m-3 hover nav-link">
                <h4>Categorias</h4>
                <img src="imagenes/iconfinder-check-listnotepadseo-and-webcheck-marklistinglistedoffice-materialschool-material-4394750_119517(1).svg" class="icons-home"/>
            </a>
             <a href="PDF?pdf=3" class="w-25 display rounded input text-center p-2 m-3 hover nav-link">
                <h4>Usuarios</h4>
                <img src="imagenes/community_users_12977.png" class="icons-home"/>
            </a>
            <br>
            
            <a href="PDF?pdf=4" class='w-25 input display rounded  text-center p-2 m-3 hover nav-link'>
                <h4>Productos</h4>
                <img src="imagenes/box_full_products_14639.png" class="icons-home"/>
            </a>
            
        </section>
    </body>
</html>

<%@include file="Vistas-Categorias/ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Control de Inventario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="WEB-INF/Vistas-Parciales/css-js.jspf" %>
    </head>
    <body>
        <header class="bg-nav h">

        </header>
        <h3 class="text-center">Bienvenid@s al Sistema de Control de Inventario</h3>
        <section class="text-center">
            <a href="categorias?opcion=listar" class="w-25 display input rounded text-center p-2 m-3 hover nav-link">
                <h4>Categorias</h4>
                <img src="imagenes/iconfinder-check-listnotepadseo-and-webcheck-marklistinglistedoffice-materialschool-material-4394750_119517(1).svg" class="icons-home"/>
            </a>
            <a href="Usuarios?opcion=listar" class="w-25 display rounded input text-center p-2 m-3 hover nav-link">
                <h4>Usuarios</h4>
                <img src="imagenes/community_users_12977.png" class="icons-home"/>
            </a>
            <br>
            
            <a href="Productos?opcion=listar" class='w-25 input display rounded  text-center p-2 m-3 hover nav-link'>
                <h4>Productos</h4>
                <img src="imagenes/box_full_products_14639.png" class="icons-home"/>
            </a>
            
            <a href="GenerarPDF.jsp" class='w-25 input display rounded  text-center p-2 m-3 hover nav-link'>
                <h4>Generar PDF</h4>
                <img src="imagenes/pdf.svg" class="icons-home"/>
            </a>
            <br>
            <a href="acercade.jsp" class='w-25 input display rounded  text-center p-2 m-3 hover nav-link'>
                <h4>Acerca de</h4>
                <img src="imagenes/about_3697.png" class="icons-home"/>
            </a>
             <a href="<%=request.getContextPath()%>/Usuarios?opcion=salir" class='w-25 input display rounded  text-center p-2 m-3 hover nav-link'>
                <h4>Cerar Sesión</h4>
                <img src="imagenes/exit.png" class="icons-home"/>
            </a>
             
                          

        </section>
                <br>
                <br>
        <%@include file="WEB-INF/Vistas-Parciales/pie.jspf" %>
    </body>
</html>

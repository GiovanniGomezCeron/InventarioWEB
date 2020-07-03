<%@include file="ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <%
            String id = "", p2 = "", p3 = "", p4 = "", p5 = "";
            String direccion =request.getContextPath();
            String direccion2 = "";
            String parametro = request.getParameter("archivo");
            id = request.getParameter("id");
            p2 = request.getParameter("nombre");

            if (parametro.equals("usuario")) {
                p3 = request.getParameter("apellido");
                p4 = request.getParameter("usuario");
                p5 = request.getParameter("tipo");
                direccion += "/Usuarios?opcion=eliminar&&id="+id;
                direccion2 = "/Usuarios?opcion=listar";

            } else if (parametro.equals("categoria")) {
                direccion += "/categorias?opcion=eliminar&&id=" + id;
                direccion2 = "/categorias?opcion=listar";

            } else if (parametro.equals("producto")) {
                p4=request.getParameter("precio");
                p5=request.getParameter("categoria");
                direccion += "/Productos?opcion=eliminar&&id=" + id;
                direccion2 = "/Productos?opcion=listar";
            }

        %>
        <h3 class="text-center">Eliminar Usuario</h3>
        <article class="container border text-center w-50 p-2">
            <h4>¿Está seguro de eliminar usuario?</h4><br>
            <label><img src="../imagenes/usuario.svg" class="w-25"/></label><br>
            <label><b><%=p2 + " " + p3%></b></label><br>
            <label><b><%=p4%></b></label><br>
            <label><b><%=p5%></b></label><br>
            <section>

                <a href="<%=direccion%>" class="btn btn-danger display">Si, estoy seguro</a>
                <a href="<%=request.getContextPath() + direccion2%>" class="btn btn-success display">No, mantener usuario</a>
            </section>
        </article>
    </body>
</html>

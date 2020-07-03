<%@include file="ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear categoria</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <%@include file="CrearCategorias.jspf"%>
        <h3 class="text-center">Crear Categoria</h3>
        <form action="<%=direccion%>" method="POST" class="bg-danger text-center p-2 w-25 container-fluid t-white rounded">
            <label for="no">Nombre</label><br>
            <input type="text" name="nombre" id="no" class="text-center input" value="<%=values[1]%>" /><br>
            <label for="es"></label><br>
            <select name="estado" id="es" class="text-center input"/>
                <option value="1">Activo</option>
                <option value="0">Inactivo</option>
            </select><br><br>
            <input type="submit" value="Registrar" class="btn btn-dark p-2" />
            <input type="hidden" value="<%=values[0]%>" name="id"/>
        </form>
    </body>
</html>

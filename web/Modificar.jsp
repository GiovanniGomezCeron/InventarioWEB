<%@include file="Vistas-Categorias/ComprobarSession.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Usuario</title>
    </head>
    <body>
        <%@include file="WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <h1 class="text-center">Modificar registro</h1>
        <form action="" method="POST" enctype="multipart/form-data" class="container-fluid w-100 p-2">
            <section class="display w-25 text-center">
            <label for="foto"><img src="imagenes/usuario.svg" class="foto-m" title="Cambiar foto"></label>
            <input type="file" name="foto" id="foto"/>
            </section>
            <section class="border w-50 display ">
                <label class="container-fluid text-center border-light2 p-2 font-weight-bolder t-white bg-life bor-r">Información personal</label>
                <section class="p-4 text-center">
                <label for="nombre">Usuario</label><br>
                <input type="text" name="nombre" id="nombre" class="text-center m-1 input"/> <br>
                <label for="fecha">Fecha de nacimiento</label></br>
                <label for="fecha" class="btn-primary btn" title="Cambiar fecha">24/12/2001</label>
                <input type="date" name="nombre" id="fecha" class="m-1"/> <br>
                <label for="con">Contraseña</label><br>
                <input type="button" name="con" id="con" value="Cambiar contraseña" class="btn-danger btn m-1" title="Cambiar contraseña"/> <br>
                <label>Rol</label><br>
                <input type="text" name="rol" value="Analista" disabled class="text-center input"/><br><br>
                <input type="submit" value="Actualizar información" class="btn-success btn t-white"/>
                </section>
            </section>
        </form>
    </body>
</html>

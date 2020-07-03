<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Modificar Usuario</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>

        <%@include file="CrearUsuarios.jspf"%>
        <h3 class="text-center"><%=titulo%></h3>
        <form action="<%=request.getContextPath() + direccion%>" method="POST" class="p-2 bg-danger rounded container w-50 text-center t-white">

            <label for="nombre">Nombre:</label> </br>
            <input type="text" name="nombre" id="nombre" class="col-5 text-center input" value="<%=valuesP[1]%>"/></br>
            <label for="ap">Apellido:</label> </br>
            <input type="text" name="apellido" id="ap" class="col-5 text-center input" value="<%=valuesP[2]%>"/></br>
            <label for="no">Nombre de usuario</label><br>
            <input type="text" name="nUsuario" id="no" class="input text-center" value="<%=valuesP[3]%>"/><br>
            <label for='con'>Contraseña</label><br>
            <input type='text' name='con' id='con' class='input text-center' value="<%=con%>"><br>
            <label for="email">Email</label></br>
            <input type="text" name="email" id="email" class="col-5 text-center input" value="<%=valuesP[4]%>"/></br>
            <label for="tipo">Tipo</label><br>
            <select name="tipo" class="text-center" id="tipo">
                <option value="Administrador">Administrador</option>
                <option value="Cajero">Gerente</option>
                <option value="Administrador">Usuario</option>
            </select><br>
            <%out.print(bancoPreguntas[num]);%><br>
                <input type="hidden" value="" name="pregunta" id="pregunta"/>
            <input type="hidden" name="id" value="<%=valuesP[0]%>" id="ident"/>
            <input type="submit" value="Registrar" class="btn btn-dark p-2">


            </br>
            <script>
                document.getElementById("pregunta").value = document.getElementById("p").innerHTML;
            </script>
        </form>

    </body>
</html>

<%@include file="Vistas-Categorias/ComprobarRegistro.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
    </head>
    <body>
        <header class='bg-nav'>
            <article class="row container-fluid p-1">
            <a href='<%=request.getContextPath()%>/Vistas-Categorias/crearUsuarios.jsp?id=0&&nombre=&&apellido=&&correo=&&tipo=&&estado=&&usuario=&&con=' class="p-2  col-sm-1 col-xs-4 t-white nav-link btn btn-danger m-3">Registrarse</a>
            </article>
        </header>
        
        <h1 class="text-center">Sistema de Control de Inventario</h1>
        <form action="Verificar" method="POST" class=" bg-danger w-50  text-center container rounded pad">
            <label class="text-center bg-dark container redondo-arriba t-white "><h4>ITCA-FEPADE Zacatecoluca</h4></label>
            <label class=" text-center" for="usuario"><img src="imagenes/usuario.svg" class="container-fluid w-25"/></label></br>
            <input type="text" name="usuario" class="m-1 input text-center" id="usuario" required/></br> 
            <label class="text-center" for="contraseña"><img src="imagenes/categoria2.png" class="container-fluid w-50"></label></br>
            <input type="password" name="contraseña" class="m-1 input text-center" id="con" required/></br>
            <p class="t-white"><a href="Seguridad/Recuperar.jsp" class="te-d t-white">No recuerdo la contraseña</a></p>

            <input type="submit" value="Iniciar sesión" class="m-1 btn-dark btn" id="iniciar"/> 
        </form>
       

            <img src="imagenes/inventario.svg" class="img-size img-bottom bo"/>

        <script src="Seguridad/comprobar.js">
        </script>                                                                                                      
</body>
</html>

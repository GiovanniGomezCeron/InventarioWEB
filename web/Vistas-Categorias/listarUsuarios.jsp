<%@include file="ComprobarSession.jspf"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="lista" scope="session" class="java.util.List"/>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <div class='container-fluid'>
            <section class='m-2'>
                <h1 class='text-center w-100 display'>Lista de usuarios</h1>
            </section>

            <table class="table table-striped text-center input table-responsive-xl">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Usuario</th>
                    <th>Email</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th colspan="2">Acciones</th>
                </tr>
                <%
                    int id = Integer.parseInt(String.valueOf(session.getAttribute("Usuario")));
                    for (int i = 0; i < lista.size(); i++) {
                        Usuario user = new Usuario();
                        user = (Usuario) lista.get(i);

                %>
                <tr>
                    <td class="input"><%=user.getId()%></td>
                    <td class='input'><%=user.getNombre()%></td>
                    <td class='input'><%=user.getApellido()%></td>
                    <td class="input"><%=user.getUsuario()%></td>
                    <td class='input'><%=user.getCorreo()%></td>
                    <td class='input'><%=user.getTipo()%></td>
                    <td class='input'><%=user.getEstado()%></td>
                    <%
                        if (id == user.getId()) {
                        out.print("<td class='input'>");
                        out.print("<a href='"+request.getContextPath()+"/Vistas-Categorias/crearUsuarios.jsp?id="+user.getId()+"&&usuario="+user.getUsuario()+"&&nombre="+user.getNombre()+"&&apellido="+user.getApellido()+"&&correo="+user.getCorreo()+"&&tipo="+user.getTipo()+"&&estado="+user.getEstado()+"'>");
                        out.print("<img src='imagenes/pencil-striped-symbol-for-interface-edit-buttons_icon-icons.com_56782.svg' class='icons'/>");
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td class='input'>");
                        out.print("<a href='"+request.getContextPath()+"/Vistas-Categorias/Eliminar.jsp?archivo=usuario&&id="+user.getId()+"usuario="+user.getUsuario()+"&&nombre="+user.getNombre()+"&&apellido=>"+user.getApellido()+"&&tipo="+user.getTipo()+"'><img src='imagenes/trash.svg' class='icons'/></a>");
                        out.print("</td>"); 
                        }else{
                        out.print("<td class='input'></td><td class='input'></td>");
                        }
                    %>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>

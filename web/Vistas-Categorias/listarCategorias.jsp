<%@include file="ComprobarSession.jspf"%>
<%@page import="Model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- El id debe ser el mismo que se le colocó de nombre a la sesión en el controlador -->
<jsp:useBean id="lista" scope="session" class="java.util.List" />
<html>
    <head>
        <title>Control de Inventario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf" %>

    </head>
    <body>

        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf" %>

        <!-- Todo el contenido de la aplicación -->

        <div class='container-fluid'>
            <section class="m-2">
                <a href="<%= request.getContextPath()%>/Vistas-Categorias/crearCategoria.jsp?opcion=1&&id=0&&nombre=&&estado=&&" class="w-20 display"><img src="imagenes/nuevo.png" class="icons3"/></a>
                <h3 class="text-center display w-75">Listado de Categorias Registradas</h3>
            </section>
            <!--<a href="#" class="btn btn-success btn-sm glyphicon glyphicon-pencil" role="button">Nueva categoria</a>-->
            
            <table class="table input text-center ">
                <tr>
                    <th>Id</th><th>Nombre</th><th>Estado</th><th colspan="2">Acción</th>
                </tr>
                <%
                    for (int i = 0; i < lista.size(); i++) {
                        Categoria categoria = new Categoria();
                        categoria = (Categoria) lista.get(i);
                    //}
%>

                <tr>
                    <td class="input"><%= categoria.getId_categoria()%></td>
                    <td class="input"><%= categoria.getNom_categoria()%></td>
                    <td class='input'><%= categoria.getEstado_categoria()%></td>

                    <td class="input">
                        <!-- /Vistas-Categorias/crearCategoria.jsp -->
                        <a href="<%= request.getContextPath()%>/Vistas-Categorias/crearCategoria.jsp?opcion=editar&&id=<%= categoria.getId_categoria()%>&&nombre=<%= categoria.getNom_categoria()%>&&estado=<%= categoria.getEstado_categoria()%>"><img src="imagenes/pencil-striped-symbol-for-interface-edit-buttons_icon-icons.com_56782.svg" class="icons"/></a>
                    </td>
                    <td class="input">
                        <a href="<%=request.getContextPath()%>/Vistas-Categorias/Eliminar.jsp?archivo=categoria&&id=<%= categoria.getId_categoria()%>&&nombre=<%= categoria.getNom_categoria()%>"><img src="imagenes/trash.svg" class="icons"/></a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <!-- End content -->

        <%@include file="../WEB-INF/Vistas-Parciales/pie.jspf" %>
    </body>
</html>



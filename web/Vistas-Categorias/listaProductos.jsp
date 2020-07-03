<%@include file="ComprobarSession.jspf"%>
<%@page import="Model.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Lista" scope="session" class="java.util.ArrayList"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de productos</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <div class="container-fluid">
            <section class="m-2">
                <a href="<%=request.getContextPath()%>/Vistas-Categorias/crearProductos.jsp?id=0&&nombre=&&descripcion=&&stock=&&precio=&&medida=&&estado=&&categoria=" class="display w-20"><img src="imagenes/nuevo.png" class="icons3"/></a>
                <h1 class="text-center display w-75">Lista de productos</h1>
            </section>
            <table class="table table-striped table-responsive-xl input text-center">
                <th>Id</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Stock</th>
                <th>Precio</th>
                <th>Medida</th>
                <th>Estado</th>
                <th>Categoria</th>
                <th>Entrada</th>

                <th colspan="2">Acciones</th>
                <%

                    for (int i = 0; i < Lista.size(); i++) {
                        Producto p = new Producto();
                        p = (Producto) Lista.get(i);

                        
                %>
                <tr>
                    <td class="input"><%=p.getId_producto()%></td>
                    <td class="input"><%=p.getNom_producto()%></td>
                    <td class="input"><%=p.getDescripcion()%></td>
                    <td class="input"><%=p.getStock()%></td>
                    <td class="input"><%=p.getPrecio()%></td>
                    <td class="input"><%=p.getUnidadMedida()%></td>
                    <td class="input"><%=p.getEstado()%></td>
                    <td class="input"><%=p.getCategoria()%></td>
                    <td class="input"><%=p.getEntrada()%></td>
                    <td class="input">
                        <a href="<%=request.getContextPath()%>/Vistas-Categorias/crearProductos.jsp?id=<%=p.getId_producto()%>&&nombre=<%=p.getNom_producto()%>&&descripcion=<%=p.getDescripcion()%>&&stock=<%=p.getStock()%>&&precio=<%=p.getPrecio()%>&&unidad=<%=p.getUnidadMedida()%>&&estado=<%=p.getEstado()%>&&entrada=<%=p.getEntrada()%>"><img src="imagenes/pencil-striped-symbol-for-interface-edit-buttons_icon-icons.com_56782.svg" class="icons"/></a>
                    </td>
                    <td class="input">
                        <a href="<%=request.getContextPath()%>/Vistas-Categorias/Eliminar.jsp?archivo=producto&&id=<%=p.getId_producto()%>&&nombre=<%=p.getNom_producto()%>&&precio=<%=p.getPrecio()%>&&categoria=<%=p.getCategoria()%>"><img src="imagenes/trash.svg" class="icons"/></a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>

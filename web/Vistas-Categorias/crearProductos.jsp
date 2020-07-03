<%@include file="ComprobarSession.jspf"%>
<%@page import="Model.Categoria"%>
<jsp:useBean id="Categorias" scope="session" class="java.util.ArrayList"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/Vistas-Parciales/css-js.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear productos</title>
    </head>
    <body>
        <%@include file="../WEB-INF/Vistas-Parciales/encabezado.jspf"%>
        <%@include file="CrearProductos.jspf"%>
        <h1 class="text-center">Crear productos</h1>
        
        <form action="<%=request.getContextPath() + direccion%>" method="POST" class="p-2 bg-danger rounded container w-50 text-center t-white">

            <label for="nombre">Nombre</label> </br>
            <input type="text" name="nombre" id="nombre" class="col-5 text-center input text-center" value="<%=v[1]%>"/></br>
            <label for="des">Descripción</label></br>
            <input class="input text-center" name="descripcion" id="des" value="<%=v[2]%>"/><br>
            <label for="st">Stock</label><br>
            <input type="text" name="stock" class="input text-center" id="st" value="<%=v[3]%>"/><br>
            <label class="text-center" for='precio'>Precio</label><br>
            <input type='text' name='precio' class="text-center input" value="<%=v[4]%>"/><br>
            <label for="unidad">Unidad de medida</label><br>
            <select name="medida" id="unidad" class="text-center" >
                <% for (int i = 0; i < unidadM.length; i++) {
                        out.print("<option value=" + unidadM[i] + ">" + unidadM[i] + "</option>");
                    }
                %>
            </select><br>
            <label id="es" for="estado">Estado</label><br>
            <select id="estado" name="estado"  class="text-center">
                <option value="1">Activo</option>
                <option value="0">Inactivo</option>
            </select><br>
            <label for="cat">Categorias</label><br>
            
            <select name="categoria" id="cat" class="text-center">
                <%
                for(int i=0; i<Categorias.size();i++){
                    Categoria c=new Categoria();
                    c=(Categoria) Categorias.get(i);
                    out.print("<option value='"+c.getId_categoria()+"'>"+c.getNom_categoria()+"</option>");
                }
                
                %>    
            </select><br><br>
            <input type="hidden" name="id" value="<%=v[0]%>"/>
            <input type="submit" value="Registrar" class="btn btn-dark t-white"/>
            </br>
            
            
            <script>
                document.getElementById("pregunta").value = document.getElementById("p").innerHTML;
            </script>
        </form>
    </body>
</html>

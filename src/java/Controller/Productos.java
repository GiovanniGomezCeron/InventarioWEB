package Controller;

import DAO.ProductoDAO;
import DAO.ProductoDAOImp;
import Model.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Productos extends HttpServlet {

    Producto ClaseProducto = new Producto();
    ProductoDAO InterfaceProducto = new ProductoDAOImp();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out=response.getWriter();
            if(request.getParameter("opcion").equals("listar")){
            this.listar(request, response);
            }else if(request.getParameter("opcion").equals("eliminar")){
            this.Eliminar(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            ClaseProducto.setId_producto(id);
            ClaseProducto.setNom_producto(request.getParameter("nombre"));
            ClaseProducto.setDescripcion(request.getParameter("descripcion"));
            ClaseProducto.setPrecio(Double.parseDouble((request.getParameter("precio"))));
            ClaseProducto.setStock(Double.parseDouble(request.getParameter("stock")));
            ClaseProducto.setUnidadMedida(request.getParameter("medida"));
            ClaseProducto.setEstado(request.getParameter("estado"));
            ClaseProducto.setCategoria(request.getParameter("categoria"));

            if (id == 0) {
                InterfaceProducto.Guardar(ClaseProducto);
            } else {
                PrintWriter out=response.getWriter();
                out.print("id "+ClaseProducto.getId_producto());
                out.print("nombre "+ClaseProducto.getNom_producto());
                out.print("descripcion "+ClaseProducto.getDescripcion());
                out.print("stock "+ClaseProducto.getStock());
                out.print("precio "+ClaseProducto.getPrecio());
                out.print("medida "+ClaseProducto.getUnidadMedida());
                out.print("estado "+ClaseProducto.getEstado());
                out.print("categoria "+ClaseProducto.getCategoria());
                
               InterfaceProducto.Editar(ClaseProducto);
            }

            this.listar(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ProductoDAO produ = new ProductoDAOImp();
        Categorias c=new Categorias();
        HttpSession cat=request.getSession(true);
        cat.setAttribute("Categorias",c.Listar2());
        HttpSession session = request.getSession(true);
        session.setAttribute("Lista", produ.Listar());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listaProductos.jsp");
        dispatcher.forward(request, response);  
    }

    public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        int id=Integer.parseInt(request.getParameter("id"));
        InterfaceProducto.Eliminar(id);
        this.listar(request, response);
    }

}

package Controller;

import DAO.CategoriaDAO;
import DAO.CategoriaDAOImplementar;
import Model.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Categorias extends HttpServlet {

    Categoria cat = new Categoria();
    CategoriaDAO c = new CategoriaDAOImplementar();

    protected void listaCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        CategoriaDAO categoria = new CategoriaDAOImplementar();
        //Crear instancia de sesión; se le da true para crear la sesión.
        HttpSession session = request.getSession(true);
        session.setAttribute("lista", categoria.Listar()); //lista es el nombre de la variable de sesión.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listarCategorias.jsp");
        dispatcher.forward(request, response);

    }

    protected void borrarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        c.Eliminar(id);
        this.listaCategorias(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("opcion")) {
            case "listar":
                this.listaCategorias(request, response);
                break;
            case "eliminar":
                this.borrarCategoria(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO c = new CategoriaDAOImplementar();
        int id = Integer.parseInt(request.getParameter("id"));
        cat.setId_categoria(id);
        cat.setNom_categoria(request.getParameter("nombre"));
        cat.setEstado_categoria(request.getParameter("estado"));
        if (id == 0) {
            c.guardarCat(cat);
        } else {
            c.Editar(cat);
        }

        this.listaCategorias(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    public List<Categoria> Listar2(){
    List<Categoria> lista=new ArrayList<Categoria>();
    lista=c.Listar();
    return lista;
    }

}

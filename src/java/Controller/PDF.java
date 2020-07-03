package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import Model.Categoria;
import Model.Producto;
import Model.Usuario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.OutputStream;
import java.util.List;

public class PDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("pdf")) {
            case "1":
                UsuarioDAO user = new UsuarioDAOImp();
                CategoriaDAO category = new CategoriaDAOImplementar();
                ProductoDAO product = new ProductoDAOImp();
                this.PDFProductCategory(response);
                break;
            case "2":
                CategoriaDAO c = new CategoriaDAOImplementar();
                c.GenerarPDF("*", response.getOutputStream(), request.getParameter("accion"));
                break;
            case "3":
                UsuarioDAO u = new UsuarioDAOImp();
                u.GenerarPDF("*", response.getOutputStream());
                break;
            case "4":
                ProductoDAO p = new ProductoDAOImp();
                p.GenerarPDF("*", response.getOutputStream());
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public OutputStream PDFProductCategory(HttpServletResponse response) {
        //Obtener OutputStream
        OutputStream out = null;
        try {
            out = response.getOutputStream();

            //Implementando interfaces
            UsuarioDAO user = new UsuarioDAOImp();
            CategoriaDAO cat = new CategoriaDAOImplementar();
            ProductoDAO producto = new ProductoDAOImp();

            //Instanciado clases
            Usuario u = new Usuario();
            Producto p = new Producto();
            Categoria c = new Categoria();

            //obteniendo datos
            List<Usuario> lista = user.Listar();
            List<Categoria> lista2 = cat.Listar();
            List<Producto> lista3 = producto.Listar();

            //Creando PDF 
            Document pdf = new Document();

            PdfWriter.getInstance(pdf, out);

            //Creando tabla para datos
            PdfPTable tb = new PdfPTable(8);

            tb.setWidthPercentage(100);

            //Abriendo documento
            pdf.open();

            Paragraph parrafo = new Paragraph();
            parrafo.add("Tabla Usuarios \n\n");
            parrafo.setAlignment(Chunk.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 16, BaseColor.RED));

            pdf.add(parrafo);

            tb.addCell("Id");
            tb.addCell("Nombre");
            tb.addCell("Apellido");
            tb.addCell("Usuario");
            tb.addCell("Email");
            tb.addCell("Estado");
            tb.addCell("Tipo");
            tb.addCell("Registro");
            tb.setWidthPercentage(100);
            tb.setWidths(new int[]{4,10,10,10,27,7,5,13});

            for (int i = 0; i < lista.size(); i++) {
                Usuario us = new Usuario();
                us = (Usuario) lista.get(i);
                tb.addCell(String.valueOf(us.getId()));
                tb.addCell(us.getNombre());
                tb.addCell(us.getApellido());
                tb.addCell(us.getUsuario());
                tb.addCell(us.getCorreo());
                tb.addCell(us.getEstado());
                tb.addCell(us.getTipo());
                tb.addCell(us.getEntrada());
            }
            pdf.add(tb);

            Paragraph parrafo2 = new Paragraph();
            parrafo2.add("\nTabla Categoria \n\n ");
            parrafo2.setAlignment(Chunk.ALIGN_CENTER);
            parrafo2.setFont(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 16, BaseColor.RED));
            pdf.add(parrafo2);
            //Segundo tabla Categoria  
            PdfPTable tb2 = new PdfPTable(3);
            tb2.addCell("Id");
            tb2.addCell("Nombre");
            tb2.addCell("Estado");
            tb2.setWidthPercentage(50);
            
            for (int i = 0; i < lista2.size(); i++) {
                Categoria ca = new Categoria();
                ca = (Categoria) lista2.get(i);
                tb2.addCell(String.valueOf(ca.getId_categoria()));
                tb2.addCell(ca.getNom_categoria());
                tb2.addCell(ca.getEstado_categoria());
            }
            pdf.add(tb2);

            PdfPTable table = new PdfPTable(9);

            table.addCell("Id");
            table.addCell("Nombre");
            table.addCell("Descripción");
            table.addCell("Stock");
            table.addCell("Precio");
            table.addCell("Unidad Medida");
            table.addCell("Categoria");
            table.addCell("Estado");
            table.addCell("Fecha entrada");
            table.setWidthPercentage(100);
            Paragraph pr = new Paragraph();
            pr.add("\nTabla producto\n\n");
            pr.setAlignment(Chunk.ALIGN_CENTER);
            table.setWidths(new int[]{4,15,20,9,9,10,12,9,13});
            pdf.add(pr);
            for (int i = 0; i < lista3.size(); i++) {
                Producto p2 = new Producto();
                p2 = (Producto) lista3.get(i);
                table.addCell(String.valueOf(p2.getId_producto()));
                table.addCell(p2.getNom_producto());
                table.addCell(p2.getDescripcion());
                table.addCell(String.valueOf(p2.getStock()));
                table.addCell(String.valueOf(p2.getPrecio()));
                table.addCell(p2.getUnidadMedida());
                table.addCell(p2.getCategoria());
                table.addCell(p2.getEstado());
                table.addCell(p2.getEntrada());
            }
            pdf.add(table);
            pdf.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}

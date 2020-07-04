package DAO;

import Controller.PDF;
import Factory.ConexionBD;
import Factory.FactoryConexionBD;
import Model.Categoria;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImplementar implements CategoriaDAO {

    ConexionBD conn;  //Crear el objeto tipo conexión.

    public CategoriaDAOImplementar() {
        //Definir a la base de datos que se conectará por defecto.
        //this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
    }

    @Override
    public List<Categoria> Listar() {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder miSQL = new StringBuilder();
        miSQL.append("SELECT * FROM tb_categoria;");
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            //Se crea el objeto ResultSet implementando el método (consultaSQL) creado para la consulta.
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while (resultadoSQL.next()) {
                Categoria categoria = new Categoria();
                //Asignar cada campo consultado al atributo en la clase.
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                String estado = "Activo";
                if (resultadoSQL.getInt("estado_categoria") != 1) {
                    estado = "Inactivo";
                }
                categoria.setEstado_categoria(estado);
                lista.add(categoria); //Agregar al array cada registro encontrado.
            }
        } catch (Exception ex) {

        } finally {
            this.conn.cerrarConexion();
        }

        return lista;
    }

    @Override
    public List<Categoria> Listar2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Editar(Categoria cate) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("update tb_categoria set nom_categoria=").append("'" + cate.getNom_categoria() + "'").append(",")
                .append("estado_categoria=").append(Integer.parseInt(cate.getEstado_categoria()))
                .append(" where id_categoria=").append(cate.getId_categoria());
        boolean consulta = this.conn.ejecutarSQL(sql.toString());
        this.conn.cerrarConexion();
        return consulta;
    }

    @Override
    public boolean guardarCat(Categoria categoria) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        boolean guardar = false;
        try {

            StringBuilder sql = new StringBuilder();
            sql.append("insert into tb_categoria values (").append(categoria.getId_categoria() + ",'")
                    .append(categoria.getNom_categoria() + "',").append(Integer.parseInt(categoria.getEstado_categoria()))
                    .append(");");

            System.out.println(this.conn.ejecutarSQL(sql.toString()));
        } catch (Exception e) {

        } finally {
            this.conn.cerrarConexion();
        }
        return guardar;
    }

    @Override
    public boolean Eliminar(int id) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("delete from tb_categoria where id_categoria=" + id);
        boolean consultar = this.conn.ejecutarSQL(sql.toString());
        this.conn.cerrarConexion();
        return consultar;
    }

    @Override
    public OutputStream GenerarPDF(String condicion, OutputStream salida,String accion) {
        List<Categoria> lista = this.Listar();
        Document pdf = new Document();
        try {

            PdfWriter.getInstance(pdf, salida);
            PdfPTable table = new PdfPTable(3);
            table.addCell("Id");
            table.addCell("Nombre");
            table.addCell("Estado");
            Paragraph p=new Paragraph();
            p.add("Tabla Categoria \n\n");
            p.setAlignment(Chunk.ALIGN_CENTER);
            pdf.open();
            pdf.add(p);
            for (int i = 0; i < lista.size(); i++) {
                Categoria cat = new Categoria();
                cat = (Categoria) lista.get(i);
                table.addCell(String.valueOf(cat.getId_categoria()));
                table.addCell(cat.getNom_categoria());
                table.addCell(cat.getEstado_categoria());
            }
            pdf.add(table);
            pdf.close();
           
            
            salida.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

}

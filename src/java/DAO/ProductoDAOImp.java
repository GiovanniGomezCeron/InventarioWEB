package DAO;

import Factory.ConexionBD;
import Factory.FactoryConexionBD;
import Model.Producto;
import java.util.List;
import Factory.MySQLConexionFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAOImp implements ProductoDAO {

    ConexionBD cn;

    @Override
    public List<Producto> Listar() {
        List<Producto> productos = new ArrayList<Producto>();
        Producto[] datos = new Producto[9];
        int i = 0;
        try {
            this.cn = Factory.FactoryConexionBD.open(Factory.FactoryConexionBD.MySQL);
            StringBuilder sql = new StringBuilder();
            sql.append("select * from tb_producto");
            ResultSet rs = this.cn.consultaSQL(sql.toString());
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNom_producto(rs.getString("nom_producto"));
                p.setDescripcion(rs.getString("des_producto"));
                p.setStock(rs.getDouble("stock"));
                p.setPrecio(rs.getDouble("precio"));
                p.setUnidadMedida(rs.getString("unidad_medida"));
                p.setCategoria(String.valueOf(rs.getInt("categoria")));
                p.setEstado(String.valueOf(rs.getInt("estado_producto")));
                p.setEntrada(rs.getTimestamp("fecha_entrada").toString());
                productos.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cn.cerrarConexion();
        return productos;
    }

    @Override
    public boolean Editar(Producto p) {
        this.cn = Factory.FactoryConexionBD.open(Factory.FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("update tb_producto set nom_producto=").append("'" + p.getNom_producto() + "'").append(",")
                .append("stock=").append(p.getStock()).append(",")
                .append("precio=").append(p.getPrecio()).append(",")
                .append("unidad_medida=").append("'" + p.getUnidadMedida() + "'").append(",")
                .append("estado_producto=").append(Integer.parseInt(p.getEstado())).append(",")
                .append("des_producto=").append("'" + p.getDescripcion() + "'").append(",")
                .append("categoria=").append(Integer.parseInt(p.getCategoria()))
                .append(" where id_producto=").append(p.getId_producto());

        boolean consulta = this.cn.ejecutarSQL(sql.toString());
        this.cn.cerrarConexion();
        return consulta;
    }

    @Override
    public boolean Guardar(Producto param) {
        this.cn = FactoryConexionBD.open(Factory.FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
    
        sql.append("insert into tb_producto values(").
                append(param.getId_producto()).append(",")
                .append("'" + param.getNom_producto() + "'").append(",")
                .append("'" + param.getDescripcion() + "'").append(",")
                .append(param.getStock()).append(",")
                .append(param.getPrecio()).append(",")
                .append("'" + param.getUnidadMedida() + "'").append(",")
                .append(Integer.parseInt(param.getEstado())).append(",")
                .append(Integer.parseInt(param.getCategoria())).append(",")
                .append("current_timestamp").append(")");
        boolean consulta = this.cn.ejecutarSQL(sql.toString());
        this.cn.cerrarConexion();
        return consulta;
    }

    @Override
    public boolean Eliminar(int Id) throws SQLException {
        this.cn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        boolean Consulta = true;
        StringBuilder sql = new StringBuilder();
        sql.append("delete from tb_producto where id_producto=").append(Id);
        Consulta = this.cn.ejecutarSQL(sql.toString());
        return Consulta;
    }

    @Override
    public OutputStream GenerarPDF(String condicion, OutputStream salida) {
        List<Producto> lista = this.Listar();
        try {
            Document pdf = new Document();

            PdfWriter.getInstance(pdf, salida);

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
            table.setWidths(new int[]{4,15,20,9,9,10,12,9,13});
            Paragraph p1=new Paragraph();
            p1.add("Tabla producto\n\n");
            pdf.open();
            pdf.add(p1);

            for (int i = 0; i < lista.size(); i++) {
                Producto p = new Producto();
                p = (Producto) lista.get(i);
                table.addCell(String.valueOf(p.getId_producto()));
                table.addCell(p.getNom_producto());
                table.addCell(p.getDescripcion());
                table.addCell(String.valueOf(p.getStock()));
                table.addCell(String.valueOf(p.getPrecio()));
                table.addCell(p.getUnidadMedida());
                table.addCell(p.getCategoria());
                table.addCell(p.getEstado());
                table.addCell(p.getEntrada());

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

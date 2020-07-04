package DAO;

import Model.Usuario;
import java.util.List;
import java.sql.*;
import Factory.ConexionBD;
import Factory.FactoryConexionBD;
import java.io.OutputStream;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAOImp implements UsuarioDAO {

    ConexionBD conn;

    @Override
    public List<Usuario> Listar() {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder miSQL = new StringBuilder();
        miSQL.append("select * from tb_usuario");
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while (resultadoSQL.next()) {
                Usuario user = new Usuario();
                user.setId(resultadoSQL.getInt("id"));
                user.setNombre(resultadoSQL.getString("nombre"));
                user.setApellido(resultadoSQL.getString("apellido"));
                user.setCorreo(resultadoSQL.getString("correo"));
                user.setUsuario(resultadoSQL.getString("usuario"));
                user.setClave(resultadoSQL.getString("clave"));
                user.setEntrada(resultadoSQL.getTimestamp("fecha_registro").toString());
                String estado = "Activo";
                if (resultadoSQL.getInt("estado") != 1) {
                    estado = "Inactivo";
                }
                user.setEstado(estado);
                user.setTipo(String.valueOf(resultadoSQL.getInt("tipo")));
                lista.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.conn.cerrarConexion();
        }
        return lista;
    }

    @Override
    public boolean Editar(Usuario user) {
        this.conn = Factory.FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tb_usuario set nombre='").append(user.getNombre() + "',apellido='").append(user.getApellido() + "',correo='")
                .append(user.getCorreo() + "',usuario='").append(user.getUsuario() + "',tipo=").append(user.getTipo() + ",estado=")
                .append(user.getEstado() + ", clave='"+user.getClave()+"' where id=").append(user.getId() + ";");
        boolean r = this.conn.ejecutarSQL(sql.toString());
        return r;
    }

    @Override
    public boolean Guardar(Usuario user) {

        this.conn = Factory.FactoryConexionBD.open(Factory.FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_usuario(id,nombre,apellido,correo,usuario,clave,tipo,estado,pregunta,respuesta,fecha_registro) values (")
                .append(user.getId() + ",")
                .append("'" + user.getNombre() + "',")
                .append("'" + user.getApellido() + "',")
                .append("'" + user.getCorreo() + "',")
                .append("'" + user.getUsuario() + "',")
                .append("'" + user.getClave() + "',")
                .append(user.getTipo() + ",")
                .append(Integer.parseInt(user.getEstado()) + ",")
                .append("'" + user.getPregunta() + "',")
                .append("'" + user.getRespuesta() + "',")
                .append("current_timestamp());");
        boolean r = this.conn.ejecutarSQL(sql.toString());
        this.conn.cerrarConexion();
        return r;

    }

    @Override
    public boolean Eliminar(int Id) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tb_usuario WHERE id=" + Id);
        boolean borrar = this.conn.ejecutarSQL(sql.toString());
        return borrar;
    }

    @Override
    public String[] validarUser(String user, String password) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        String[] datos = new String[2];
        sql.append("Select id,pregunta from tb_usuario where usuario=").append("'" + user + "'").append(" && ")
                .append(" clave=").append("'" + password + "'");
        try {
            ResultSet rs = this.conn.consultaSQL(sql.toString());
            rs.next();
            datos[0] = String.valueOf(rs.getInt("id"));
            datos[1] = rs.getString("pregunta");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public String ValidarRespuesta(int id, String respon) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder sql = new StringBuilder();
        String dato = "";
        sql.append("select usuario from tb_usuario where id=" + id + " && respuesta=" + respon);
        System.out.println(sql.toString());

        try {
            ResultSet rs = this.conn.consultaSQL(sql.toString());

            rs.next();
            dato = rs.getString("usuario");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dato;

    }

    @Override
    public OutputStream GenerarPDF(String condicion, OutputStream salidad) {
        List<Usuario> lista = this.Listar();
        Document pdf = new Document();
        try {

            PdfWriter.getInstance(pdf, salidad);
            PdfPTable table = new PdfPTable(8);
            table.addCell("Id");
            table.addCell("Nombre");
            table.addCell("Apellido");
            table.addCell("Usuario");
            table.addCell("Email");
            table.addCell("Estado");
            table.addCell("Tipo");
            table.addCell("Registro");
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4,10,10,10,27,7,5,13});
            pdf.open();
            for (int i = 0; i < lista.size(); i++) {
                Usuario user = new Usuario();
                user = (Usuario) lista.get(i);
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getNombre());
                table.addCell(user.getApellido());
                table.addCell(user.getUsuario());
                table.addCell(user.getCorreo());
                table.addCell(user.getEstado());
                table.addCell(user.getTipo());
                table.addCell(user.getEntrada());
            }
            pdf.add(table);
            pdf.close();
            salidad.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salidad;
    }
    public String Email(String email){
    this.conn=FactoryConexionBD.open(FactoryConexionBD.MySQL);
    StringBuilder sql=new StringBuilder();
    sql.append("SELECT  clave FROM tb_usuario where correo='"+email+"'");
    String correo="";
    ResultSet rs= this.conn.consultaSQL(sql.toString());
        try {
            rs.next();
            correo=rs.getString("clave");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conn.cerrarConexion();
    return correo;
    }

}

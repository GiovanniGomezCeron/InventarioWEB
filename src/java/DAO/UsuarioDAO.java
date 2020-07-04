package DAO;

import Model.Usuario;
import java.io.OutputStream;
import java.util.List;

public interface UsuarioDAO {

    public List<Usuario> Listar();

    public boolean Editar(Usuario user);

    public boolean Guardar(Usuario user);

    public boolean Eliminar(int Id);
    
    public String[] validarUser(String user,String password);
    
    public String ValidarRespuesta(int id,String respon);

    public OutputStream GenerarPDF(String condicion,OutputStream salida);
    
    public String Email(String email);
    
    
}

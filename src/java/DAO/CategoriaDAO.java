package DAO;

import Model.Categoria;
import java.io.OutputStream;
import java.util.List;

public interface CategoriaDAO {
    //Definir los métodos, como la clase en interface. Los métodos no se implementan 
    //aqui, los métodos son de tipo abstractos.
    public List<Categoria> Listar();
    public List<Categoria> Listar2();
    public boolean Editar(Categoria cate);
    public boolean guardarCat(Categoria categoria);
    public boolean Eliminar(int id);
    public OutputStream GenerarPDF(String condicion,OutputStream salida,String accion);
    
}

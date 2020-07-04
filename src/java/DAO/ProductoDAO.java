package DAO;

import Model.Producto;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {
public List<Producto> Listar();
public boolean Editar(Producto p);
public boolean Guardar(Producto product);
public boolean Eliminar(int Id) throws SQLException;
public OutputStream GenerarPDF(String condicion,OutputStream salida);
}

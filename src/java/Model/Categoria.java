package Model;


public class Categoria {
    private int id_categoria;
    private String nom_categoria;
    private String estado_categoria;

    public Categoria() {
        this.id_categoria = 0;
        this.nom_categoria = "";
        this.estado_categoria = ""; //1 será activo y 0 será inactivo
    }

    public Categoria(int id_categoria, String nom_categoria, String estado_categoria) {
        this.id_categoria = id_categoria;
        this.nom_categoria = nom_categoria;
        this.estado_categoria = estado_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public String getEstado_categoria() {
        return estado_categoria;
    }

    public void setEstado_categoria(String estado_categoria) {
        this.estado_categoria = estado_categoria;
    }
    
    
     //Autor: Prof. Manuel de Jesús Gámez López
}

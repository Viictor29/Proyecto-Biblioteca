public class Autor {
    private int id_autor;
    private String nombre;

    //Controctores
    public Autor() {
    }

    public Autor(int id_autor, String nombre) {
        this.id_autor = id_autor;
        this.nombre = nombre;
    }

    public Autor(String nombre) {
        this.nombre = nombre;
    }
    //Getters y Setters

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //ToString

    @Override
    public String toString() {
        return "Id: " + id_autor + ", Nombre: " + nombre;
    }
}

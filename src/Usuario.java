public class Usuario {
    private int id_usuario;
    private String nombre;
    //Constructor vacio y general

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
    }

    //Getters y setters

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //To String
    @Override
    public String toString() {
        return "Id usuario: " + id_usuario + ", Nombre: " + nombre;
    }
}

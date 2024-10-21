public class Libro_Autor {
    private int idLibro;
    private int idAutor;

    //Constroctor vacio y general

    public Libro_Autor() {
    }

    public Libro_Autor(int idLibro, int idAutor) {
        this.idLibro = idLibro;
        this.idAutor = idAutor;
    }

    //Getters y Setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    //To String

    @Override
    public String toString() {
        return "Id Libro: " + idLibro + ", Id Autor: " + idAutor;
    }
}

public class Libro {
    private int id_libro;
    private String titulo;
    private String isbn;

    //Constroctores

    public Libro() {
    }

    public Libro(int id_libro, String titulo, String isbn) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.isbn = isbn;
    }

    //Getters y Setters
    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //ToString

    @Override
    public String toString() {
        return "Id: " + id_libro + ", Titulo: " + titulo + ", ISBN: " + isbn;
    }
}

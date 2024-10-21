import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sincronizacion {
    private AutorDAO autorDAO;
    private Libro_AutorDAO libro_autorDAO;
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;

    public Sincronizacion() {
    }

    public Sincronizacion(Connection conexion){
        this.autorDAO = new AutorDAO(conexion);
        this.libro_autorDAO = new Libro_AutorDAO(conexion);
        this.libroDAO = new LibroDAO(conexion);
        this.prestamoDAO = new PrestamoDAO(conexion);
        this.usuarioDAO = new UsuarioDAO(conexion);
    }

    public void sincronizarAutores(){
        ArrayList<Autor> autores = this.autorDAO.leerAutor();
        System.out.println("El listado de autores es: ");
        for (Autor autor: autores){
            System.out.println(autor);
        }
    }

    public void sincronizarLibroAutor() throws SQLException {
        ArrayList<Libro_Autor> libroAutores = this.libro_autorDAO.leerLibro_Autor();
        System.out.println("El listado de libro y autores es: ");
        for (Libro_Autor libroAutor: libroAutores){
            System.out.println(libroAutor);
        }
    }

    public void sincronizarLibros(){
        ArrayList<Libro> libros = this.libroDAO.leerLibros();
        System.out.println("El listado de libros es: ");
        for (Libro libro:libros){
            System.out.println(libro);
        }
    }

    public void sincronizarPrestamos(){
        ArrayList<Prestamo> prestamos = this.prestamoDAO.listaPrestamos;
        System.out.println("El listado de prestamos es: ");
        for (Prestamo prestamo:prestamos){
            System.out.println(prestamo);
        }
    }

    public void sincronizarUsuarios(){
        ArrayList<Usuario> usuarios = this.usuarioDAO.leerUsuario();
        System.out.println("El listado de usuarios es: ");
        for (Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }
}

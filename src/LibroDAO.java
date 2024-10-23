import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibroDAO {

    // Lista para almacenar los libros
    ArrayList<Libro> listaLibros = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    private Connection conexion;

    // Constructor que recibe una conexión a la base de datos
    public LibroDAO(Connection conexion){
        this.conexion = conexion;
    }

    // Método para crear un nuevo libro en la base de datos
    public Libro crearLibro(){
        Libro libro = new Libro();
        System.out.println("Introduce el nombre del Libro: ");
        String titulo = teclado.nextLine();  // Captura el título del libro
        System.out.println("Introduce el ISBN del libro: ");
        String isbn = teclado.nextLine();  // Captura el ISBN del libro

        // Consulta SQL para insertar un libro
        String SQL = "INSERT INTO Libro (titulo, isbn) VALUES (?, ?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1, titulo);  // Asigna el título al query
            ps.setString(2, isbn);    // Asigna el ISBN al query
            ps.executeUpdate();       // Ejecuta la inserción
            libro = new Libro(titulo, isbn);  // Crea un nuevo objeto Libro
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones en caso de error
        }
        return libro;  // Retorna el objeto libro creado
    }

    // Método para leer todos los libros de la base de datos
    public ArrayList<Libro> leerLibros(){
        listaLibros.clear();  // Limpiar la lista antes de llenarla nuevamente
        String SQL = "SELECT * FROM Libro";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta
            // Itera sobre el ResultSet y crea objetos Libro con los datos obtenidos
            while (rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String isbn = rs.getString("isbn");
                Libro libro = new Libro(id, titulo, isbn);
                listaLibros.add(libro);  // Añade cada libro a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros;  // Retorna la lista de libros
    }

    // Método para eliminar un libro por su ID
    public void eliminaLibro(){
        System.out.println("Indica el id que quiere eliminar: ");
        int id = teclado.nextInt();  // Captura el ID del libro a eliminar
        teclado.nextLine();  // Consumir el salto de línea

        // Consulta SQL para eliminar el libro por su ID
        String SQL = "DELETE FROM LIBRO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, id);  // Asigna el ID al query
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Método para actualizar la información de un libro
    public Libro actualizarLibro() throws SQLException{
        Libro libro = new Libro();
        System.out.println("Introduce el id del libro que quieres actualizar: ");
        int id = teclado.nextInt();  // Captura el ID del libro a actualizar
        teclado.nextLine();  // Consumir el salto de línea

        // Verifica si el libro existe en la base de datos
        String SQL = "SELECT COUNT(*) FROM LIBRO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);  // Asigna el ID al query
        ResultSet rs = ps.executeQuery();

        // Si el libro no existe, muestra un mensaje
        if (rs.next() && rs.getInt(1) == 0){
            System.out.println("El libro con ID " + id + " no existe en la Base de Datos");
        } else {
            // Captura los nuevos datos del libro
            System.out.println("Introduce el nuevo titulo: ");
            String nombre = teclado.nextLine();  // Nuevo título
            System.out.println("Introduce el nuevo ISBN: ");
            String isbn = teclado.nextLine();  // Nuevo ISBN

            // Consulta SQL para actualizar el libro
            String SQLactualizar = "UPDATE LIBRO SET TITULO = ?, ISBN = ? WHERE ID = ?";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);
            ps1.setString(1, nombre);  // Asigna el nuevo título
            ps1.setString(2, isbn);    // Asigna el nuevo ISBN
            ps1.setInt(3, id);         // Asigna el ID del libro a actualizar
            ps1.executeUpdate();       // Ejecuta la actualización

            libro = new Libro(nombre, isbn);  // Actualiza el objeto libro
            listaLibros.add(libro);  // Añade el libro actualizado a la lista
        }
        return libro;  // Retorna el libro actualizado
    }
}


import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Libro_AutorDAO {
    // Lista para almacenar las relaciones Libro-Autor
    ArrayList<Libro_Autor> Listalibro_Autor = new ArrayList<>();
    // Objeto para capturar la entrada del usuario
    Scanner teclado = new Scanner(System.in);
    // Variable para manejar la entidad Libro_Autor
    Libro_Autor Libro_Autor;

    // Conexión a la base de datos
    private Connection conexion;

    // Constructor que recibe la conexión
    public Libro_AutorDAO(Connection conexion){
        this.conexion = conexion;
    }

    // Método para crear una relación entre un libro y un autor
    public Libro_Autor crearLibro_Autor() throws SQLException{
        // Solicitar el id del libro
        System.out.println("Introduce id Libro: ");
        int idLibro = teclado.nextInt();
        // Solicitar el id del autor
        System.out.println("Introduce id Autor: ");
        int idAutor = teclado.nextInt();

        // Verificar si el libro existe en la base de datos
        String SQL = "SELECT COUNT(*) FROM Libro WHERE id = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, idLibro);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && (rs.getInt(1) > 0)) { // Si el libro existe
            // Verificar si el autor existe
            String SQL1 = "SELECT COUNT(*) FROM Autor WHERE id = ?";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQL1);
            ps1.setInt(1, idAutor);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next() && (rs1.getInt(1) > 0)) { // Si el autor existe
                // Verificar si ya existe la relación libro-autor
                String SQL2 = "SELECT * FROM Libro_Autor WHERE idLibro = ? AND idAutor = ?";
                PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL2);
                ps2.setInt(1, idLibro);
                ps2.setInt(2, idAutor);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next() && (rs2.getInt(1) > 0)) { // Si la relación ya existe
                    JOptionPane.showMessageDialog(null, "Ya existe ese libro con ese autor");
                }
                else { // Si la relación no existe, se crea
                    String SQL3 = "INSERT INTO Libro_Autor VALUES(?,?)";
                    PreparedStatement ps3 = Conexion.crearConexion().prepareStatement(SQL3);
                    ps3.setInt(1, idLibro);
                    ps3.setInt(2, idAutor);
                    ps3.executeUpdate();
                    // Crear una nueva instancia de la clase Libro_Autor
                    Libro_Autor = new Libro_Autor(idLibro, idAutor);
                }
            }
        }
        return Libro_Autor; // Retornar la relación creada (si se creó)
    }

    // Método para leer todas las relaciones libro-autor desde la base de datos
    public ArrayList<Libro_Autor> leerLibro_Autor() throws SQLException{
        String SQL = "SELECT * FROM Libro_Autor"; // Consulta para obtener todas las relaciones
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        // Recorrer los resultados y añadir cada relación a la lista
        while (rs.next()) {
            int idLibro = rs.getInt("idLibro");
            int idAutor = rs.getInt("idAutor");
            Libro_Autor = new Libro_Autor(idLibro, idAutor);
            Listalibro_Autor.add(Libro_Autor);
        }
        return Listalibro_Autor; // Retornar la lista con todas las relaciones
    }

    // Método para eliminar una relación libro-autor
    public void eliminarLibro_Autor() throws SQLException{
        // Solicitar el id del libro y del autor
        System.out.println("Dime el id del Libro: ");
        int idLibro = teclado.nextInt();
        System.out.println("Dime el id del Autor: ");
        int idAutor = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        // Eliminar la relación especificada
        String SQL = "DELETE FROM Libro_Autor WHERE idLibro = ? AND idAutor = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, idLibro);
        ps.setInt(2, idAutor);
        ps.executeUpdate();
    }

    // Método para actualizar la relación libro-autor
    public void ActualizarLibro_Autor() throws SQLException {
        ArrayList<Integer> posiblesLibros = new ArrayList<>(); // Lista para posibles libros del autor

        // Solicitar el id del autor para actualizar
        System.out.println("Dime el id del Autor a actualizar: ");
        int idLibro = teclado.nextInt();

        // Obtener todos los libros asociados al autor
        String SQL = "SELECT idLibro from Libro_Autor WHERE idAutor = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, idLibro);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            posiblesLibros.add(id); // Añadir libro a la lista
        }

        // Si hay más de un libro asociado, solicitar al usuario cuál actualizar
        if (posiblesLibros.size() > 1) {
            System.out.println("Hay varios libros de este autor, cuál quieres actualizar: " + posiblesLibros.toString());
            int idAutor = teclado.nextInt(); // Seleccionar libro
            System.out.println("Dime el nuevo Autor: ");
            int idLibroNuevo = teclado.nextInt(); // Nuevo id de autor
            // Actualizar la relación
            String SQL1 = "UPDATE Libro_Autor SET idAutor = ? WHERE idLibro = ? AND idAutor = ?";
            PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL1);
            ps2.setInt(1, idLibroNuevo);
            ps2.setInt(2, idAutor);
            ps2.setInt(3, idLibro);
            ps2.executeUpdate();
        } else if (posiblesLibros.size() == 1) { // Si solo hay un libro asociado
            System.out.println("Dime el nuevo Autor: ");
            int idLibroNuevo = teclado.nextInt();
            // Actualizar la relación
            String SQL1 = "UPDATE Libro_Autor SET idAutor = ? WHERE idLibro = ? AND idAutor = ?";
            PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL1);
            ps2.setInt(1, idLibroNuevo);
            ps2.setInt(2, posiblesLibros.get(0));  // Actualizar el único libro
            ps2.setInt(3, idLibro); // Autor original
            ps2.executeUpdate();
            System.out.println("Actualización realizada con éxito.");
        } else {
            System.out.println("No se encontraron libros asociados a este autor.");
        }

    }
}



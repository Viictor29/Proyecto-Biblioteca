import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AutorDAO {
    // Lista para almacenar los autores
    ArrayList<Autor> listaAutores = new ArrayList<>();
    // Scanner para capturar entradas del usuario
    Scanner teclado = new Scanner(System.in);
    private Connection conexion;

    // Constructor que recibe una conexión a la base de datos
    public AutorDAO(Connection conexion){
        this.conexion = conexion;
    }

    // Método para crear un nuevo autor
    public Autor crearAutor(){
        Autor autor = new Autor();
        System.out.println("Introduce el nombre del autor: ");
        String nombre = teclado.nextLine(); // Captura el nombre del autor

        // Consulta SQL para insertar un nuevo autor
        String SQL = "INSERT INTO Autor (nombre) VALUES (?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1, nombre); // Asigna el nombre del autor al query
            ps.executeUpdate(); // Ejecuta la inserción
            autor = new Autor(nombre); // Crea un objeto Autor con el nombre dado
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    }

    // Método para leer todos los autores desde la base de datos
    public ArrayList<Autor> leerAutor(){
        listaAutores.clear(); // Limpiar la lista de autores antes de llenarla nuevamente
        String SQL = "SELECT * FROM AUTOR";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta
            // Itera sobre el ResultSet y añade los autores a la lista
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Autor autor = new Autor(id, nombre); // Crea un objeto Autor con los datos obtenidos
                listaAutores.add(autor); // Añade el autor a la lista
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaAutores; // Retorna la lista de autores
    }

    // Método para eliminar un autor por su id
    public void eliminaAutor(){
        System.out.println("Indica el id que quiere eliminar: ");
        int id = teclado.nextInt(); // Captura el id del autor a eliminar
        teclado.nextLine(); // Consumir el salto de línea

        // Consulta SQL para eliminar el autor con el id dado
        String SQL = "DELETE FROM AUTOR WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, id); // Asigna el id al query
            ps.executeUpdate(); // Ejecuta la eliminación
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Método para actualizar el nombre de un autor
    public Autor actualizarAutor() throws SQLException{
        Autor autor = new Autor();
        System.out.println("Introduce el id del autor que quieres actualizar: ");
        int id = teclado.nextInt(); // Captura el id del autor a actualizar
        teclado.nextLine(); // Consumir el salto de línea

        // Consulta para verificar si el autor existe
        String SQL = "SELECT COUNT(*) FROM AUTOR WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id); // Asigna el id al query
        ResultSet rs = ps.executeQuery(); // Ejecuta la consulta

        // Verifica si el autor existe en la base de datos
        if (rs.next() && rs.getInt(1) == 0){
            System.out.println("El autor " + autor + " no existe en la Base de Datos");
        } else {
            // Solicita el nuevo nombre del autor
            System.out.println("Introduce el nuevo nombre: ");
            String nombre = teclado.nextLine(); // Captura el nuevo nombre

            // Consulta SQL para actualizar el nombre del autor
            String SQLactualizar = "UPDATE AUTOR SET NOMBRE = ? WHERE ID = ? ";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);
            ps1.setString(1, nombre); // Asigna el nuevo nombre al query
            ps1.setInt(2, id); // Asigna el id del autor
            ps1.executeUpdate(); // Ejecuta la actualización

            autor = new Autor(nombre); // Actualiza el objeto Autor con el nuevo nombre
            listaAutores.add(autor); // Añade el autor actualizado a la lista
        }
        return autor; // Retorna el autor actualizado
    }
}



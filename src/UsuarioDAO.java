import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioDAO {
    // Lista para almacenar los usuarios y scanner para recibir datos de entrada
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    // Conexión a la base de datos
    private Connection conexion;

    // Constructor que recibe una conexión a la base de datos
    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para crear un nuevo usuario
    public Usuario crearUsuario() {
        Usuario usuario = new Usuario();  // Creamos un objeto Usuario vacío
        System.out.println("Introduce el nombre del usuario: ");
        String nombre = teclado.nextLine();  // Captura el nombre del usuario desde la consola

        // Consulta SQL para insertar un nuevo usuario en la base de datos
        String SQL = "INSERT INTO USUARIO (NOMBRE) VALUES (?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ps.setString(1, nombre);  // Asigna el nombre al query
            ps.executeUpdate();  // Ejecuta la inserción
            usuario = new Usuario(nombre);  // Crea un objeto Usuario con el nombre capturado
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
        return usuario;  // Retorna el usuario creado
    }

    // Método para leer todos los usuarios de la base de datos
    public ArrayList<Usuario> leerUsuario() {
        listaUsuarios.clear();  // Limpiamos la lista antes de llenarla con nuevos datos

        // Consulta SQL para obtener todos los usuarios
        String SQL = "SELECT * FROM USUARIO";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta

            // Iteramos sobre el resultado para crear objetos Usuario
            while (rs.next()) {
                int id = rs.getInt("id");  // Capturamos el ID del usuario
                String nombre = rs.getString("nombre");  // Capturamos el nombre del usuario

                Usuario usuario = new Usuario(id, nombre);  // Creamos el objeto Usuario
                listaUsuarios.add(usuario);  // Añadimos el usuario a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
        return listaUsuarios;  // Retorna la lista de usuarios
    }

    // Método para eliminar un usuario por su ID
    public void eliminarUsuario() {
        System.out.println("Indica el id del usuario que quieres eliminar: ");
        int id = teclado.nextInt();  // Captura el ID del usuario a eliminar
        teclado.nextLine();  // Consumimos el salto de línea

        // Consulta SQL para eliminar un usuario por su ID
        String SQL = "DELETE FROM USUARIO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ps.setInt(1, id);  // Asigna el ID al query
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
    }

    // Método para actualizar un usuario existente
    public Usuario actualizarUsuario() throws SQLException {
        Usuario usuario = new Usuario();  // Creamos un objeto Usuario vacío
        System.out.println("Introduce el id del usuario que quieres actualizar: ");
        int id = teclado.nextInt();  // Captura el ID del usuario a actualizar
        teclado.nextLine();  // Consumimos el salto de línea

        // Verifica si el usuario existe en la base de datos
        String SQL = "SELECT COUNT(*) FROM USUARIO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);  // Asigna el ID al query
        ResultSet rs = ps.executeQuery();  // Ejecuta la consulta

        if (rs.next() && rs.getInt(1) == 0) {  // Si no existe el usuario
            System.out.println("El usuario con ID " + id + " no existe en la Base de Datos");
        } else {
            // Si existe, capturamos el nuevo nombre del usuario
            System.out.println("Introduce el nuevo nombre: ");
            String nombre = teclado.nextLine();

            // Consulta SQL para actualizar el nombre del usuario
            String SQLactualizar = "UPDATE USUARIO SET NOMBRE = ? WHERE ID = ?";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);
            ps1.setString(1, nombre);  // Asigna el nuevo nombre
            ps1.setInt(2, id);  // Asigna el ID del usuario a actualizar
            ps1.executeUpdate();  // Ejecuta la actualización

            // Creamos un nuevo objeto Usuario con el nombre actualizado
            usuario = new Usuario(id, nombre);
            listaUsuarios.add(usuario);  // Añadimos el usuario actualizado a la lista
        }
        return usuario;  // Retorna el usuario actualizado
    }
}


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PrestamoDAO {
    // Inicializamos una lista para almacenar los préstamos
    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    // Fechas por defecto: fecha actual y un mes después
    Date fechaInicio = Date.valueOf(LocalDate.now());
    Date fechaFin = Date.valueOf(LocalDate.now().plusMonths(1));

    // Conexión a la base de datos
    private Connection conexion;

    // Constructor que recibe una conexión a la base de datos
    public PrestamoDAO(Connection conexion){
        this.conexion = conexion;
    }

    // Método para crear un nuevo préstamo en la base de datos
    public Prestamo crearPrestamo() {
        Prestamo p = new Prestamo(); // Creamos el objeto préstamo

        // Consulta SQL para insertar un nuevo préstamo
        String SQL = "INSERT INTO Prestamo (fechaInicio, fechaFin) VALUES (?, ?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ps.setDate(1, fechaInicio); // Asigna la fecha de inicio por defecto
            ps.setDate(2, fechaFin);     // Asigna la fecha de fin por defecto
            ps.executeUpdate();          // Ejecuta la inserción en la base de datos
            p = new Prestamo(fechaInicio, fechaFin); // Crea un objeto préstamo con las fechas
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
        return p;  // Retorna el préstamo creado
    }

    // Método para leer todos los préstamos de la base de datos
    public ArrayList<Prestamo> leerPrestamo() {
        listaPrestamos.clear();  // Limpiamos la lista antes de llenarla con nuevos datos
        String SQL = "SELECT * FROM PRESTAMO";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta
            // Iteramos sobre el ResultSet para construir los objetos préstamo
            while (rs.next()) {
                int id = rs.getInt("id");
                String fechaI = rs.getString("fechaInicio");
                String fechaF = rs.getString("fechaFin");
                Prestamo p = new Prestamo(id, fechaI, fechaF);  // Creamos el objeto préstamo
                listaPrestamos.add(p);  // Añadimos el préstamo a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
        return listaPrestamos;  // Retorna la lista de préstamos
    }

    // Método para eliminar un préstamo por su ID
    public void eliminaPrestamo() {
        System.out.println("Indica el id que quiere eliminar: ");
        int id = teclado.nextInt();  // Captura el ID del préstamo a eliminar
        teclado.nextLine();  // Consumimos el salto de línea

        // Consulta SQL para eliminar un préstamo por su ID
        String SQL = "DELETE FROM PRESTAMO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)) {
            ps.setInt(1, id);  // Asigna el ID al query
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
    }

    // Método para actualizar un préstamo existente
    public Prestamo actualizarPrestamo() throws SQLException {
        Prestamo prestamo = new Prestamo();  // Creamos un objeto vacío para el préstamo a actualizar
        System.out.println("Introduce el id del prestamo que quieres actualizar: ");
        int id = teclado.nextInt();  // Captura el ID del préstamo a actualizar
        teclado.nextLine();  // Consumimos el salto de línea

        // Verifica si el préstamo existe en la base de datos
        String SQL = "SELECT COUNT(*) FROM PRESTAMO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);  // Asigna el ID al query
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) == 0) {  // Si no existe el préstamo
            System.out.println("El prestamo con ID " + id + " no existe en la Base de Datos");
        } else {
            // Si existe, capturamos las nuevas fechas de inicio y fin
            System.out.println("Introduce la nueva fecha de inicio: ");
            String fechaInicio = teclado.nextLine();

            System.out.println("Introduce la nueva fecha de fin: ");
            String fechaFin = teclado.nextLine();

            // Consulta SQL para actualizar el préstamo
            String SQLactualizar = "UPDATE PRESTAMO SET fechaInicio = ?, fechaFin = ? WHERE ID = ?";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);
            ps1.setString(1, fechaInicio);  // Asigna la nueva fecha de inicio
            ps1.setString(2, fechaFin);     // Asigna la nueva fecha de fin
            ps1.setInt(3, id);              // Asigna el ID del préstamo a actualizar
            ps1.executeUpdate();            // Ejecuta la actualización

            // Actualizamos el objeto préstamo con las nuevas fechas
            prestamo = new Prestamo(fechaInicio, fechaFin);
            listaPrestamos.add(prestamo);  // Añadimos el préstamo actualizado a la lista
        }
        return prestamo;  // Retorna el préstamo actualizado
    }
}


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PrestamoDAO {
    //Inicializamos lo que usaremos en la clase
    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    Date fechaInicio = Date.valueOf(LocalDate.now());
    Date fechaFin = Date.valueOf(LocalDate.now().plusMonths(1));
    private Connection conexion;
    public PrestamoDAO(Connection conexion){
        this.conexion = conexion;
    }
    //Los 4 métodos del DAO
    public Prestamo crearPrestamo(){
        Prestamo p = new Prestamo();

        String SQL = "INSERT INTO Prestamo (fechaInicio, fechaFin) VALUES (?,?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){

            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            ps.executeUpdate();
            p = new Prestamo(fechaInicio, fechaFin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Prestamo> leerPrestamo(){
        listaPrestamos.clear();
        String SQL = "SELECT * FROM PRESTAMO";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String fechaI = rs.getDate("fechaInicio").toString();
                String fechaF = rs.getDate("fechaFin").toString();
                int idUsuario = rs.getInt("usuarioId");
                int idLibro = rs.getInt("libroId");
                Prestamo p = new Prestamo(id, fechaI, fechaF, idUsuario, idLibro);
                listaPrestamos.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaPrestamos;
    }

    public void eliminaPrestamo(){
        System.out.println("Indica el id que quiere eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        String SQL = "DELETE FROM PRESTAMO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Prestamo actualizarPrestamo() throws SQLException{
        Prestamo prestamo = new Prestamo();
        System.out.println("Introduce el id del prestamo que quieres actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();


        String SQL = "SELECT COUNT(*) FROM PRESTAMO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1)==0){
            System.out.println("El prestamo " + prestamo + " no existe en la Base de Datos");

        }else {
            System.out.println("Introduce la nueva fecha de inicio: ");
            String fechaInicio = teclado.nextLine();

            System.out.println("Introduce la nueva fecha de fin: ");
            String fechaFin = teclado.nextLine();

            String SQLactualizar = "UPDATE PRESTAMO SET fechaInicio = ?, fechaFin = ? WHERE ID = ? ";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);

            ps1.setString(1, fechaInicio);
            ps1.setString(2,fechaFin);
            ps1.setInt(3, id);
            ps1.executeUpdate();

//            prestamo = new Prestamo(fechaInicio, fechaFin);
            listaPrestamos.add(prestamo);
        }
        return prestamo;
    }
}

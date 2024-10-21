import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PrestamoDAO {
    //Inicializamos lo que usaremos en la clase
    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    Date fechaInicio = Date.valueOf(LocalDate.now());
    Date fechaFin = Date.valueOf(LocalDate.now().plusMonths(1));
    //Los 4 métodos del DAO
    public Prestamo crearPrestamo(){
        Prestamo p = new Prestamo();
        System.out.println("Introduce el id del Préstamo: ");
        int idPrestamo = teclado.nextInt();
        String SQL = "INSERT INTO Prestamo (fechaInicio, fechaFin) VALUES (?,?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1, String.valueOf(idPrestamo));
            ps.setDate(2, fechaInicio);
            ps.setDate(3, fechaFin);
            ps.executeUpdate();
            p = new Prestamo(idPrestamo, fechaInicio, fechaFin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Prestamo> leerPrestamo(){
        listaPrestamos.clear();
        String SQL = "SELECT * FROM AUTOR";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                Date fechaI = rs.getDate("fechaInicio");
                Date fechaF = rs.getDate("fechaFin");
                Prestamo p = new Prestamo(id, fechaI, fechaF);
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
        for (Prestamo p : listaPrestamos){
            if (id == p.getId_prestamo()){
                String SQL = "DELETE FROM PRESTAMO WHERE ID = ?";
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else
                System.out.println("No hay Prestamo con este id");
        }
    }

    public void actualizarPrestamo(){
        System.out.println("Introduce el id del Prestamo que quieres actualizar: ");
        int id = teclado.nextInt();
        for (Prestamo p : listaPrestamos){
            if (id == p.getId_prestamo()){
                String SQL = "UPDATE AUTOR SET fechaInicio, fechaFin VALUES = ? , ? WHERE ID = ?";
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    Date fechaInicio = Date.valueOf(LocalDate.now());
                    Date fechaFin = Date.valueOf(LocalDate.now().plusMonths(1));
                    ps.setDate(1,fechaInicio);
                    ps.setDate(2, fechaFin);
                    ps.setInt(3, id);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

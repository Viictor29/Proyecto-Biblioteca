import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Libro_AutorDAO {
    ArrayList<Libro_Autor> Listalibro_Autor = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    Libro_Autor Libro_Autor;

    private Connection conexion;
    public Libro_AutorDAO(Connection conexion){
        this.conexion = conexion;
    }

    public Libro_Autor crearLibro_Autor() throws SQLException{
        System.out.println("Introduce id Libro: ");
        int idLibro = teclado.nextInt();
        System.out.println("Introduce id Autor: ");
        int idAutor = teclado.nextInt();
        String SQL = "SELECT COUNT(*) FROM Libro WHERE id = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, idLibro);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && (rs.getInt(1) > 0)) {
            String SQL1 = "SELECT COUNT(*) FROM Autor WHERE id = ?";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQL1);
            ps1.setInt(1, idAutor);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next() && (rs1.getInt(1) > 0)) {
                String SQL2 = "SELECT * FROM Libro_Autor WHERE idLibro = ? AND idAutor = ?";
                PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL2);
                ps2.setInt(1, idLibro);
                ps2.setInt(2, idAutor);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next() && (rs2.getInt(1) > 0)) {
                    JOptionPane.showMessageDialog(null, "Ya existe ese libro con ese autor");
                }
                else {
                    String SQL3 = "INSERT INTO Libro_Autor VALUES(?,?)";
                    PreparedStatement ps3 = Conexion.crearConexion().prepareStatement(SQL3);
                    ps3.setInt(1, idLibro);
                    ps3.setInt(2, idAutor);
                    ps3.executeUpdate();
                    Libro_Autor = new Libro_Autor(idLibro, idAutor);
                }
            }
        }
        return Libro_Autor;
    }

    public ArrayList<Libro_Autor> leerLibro_Autor() throws SQLException{
        String SQL = "SELECT * FROM Libro_Autor";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idLibro = rs.getInt("idLibro");
            int idAutor = rs.getInt("idAutor");
            Libro_Autor = new Libro_Autor(idLibro, idAutor);
            Listalibro_Autor.add(Libro_Autor);
        }
        return Listalibro_Autor;
    }

    public void eliminarLibro_Autor() throws SQLException{
        System.out.println("Dime el id del Libro: ");
        int idLibro = teclado.nextInt();
        System.out.println("Dime el id del Autor: ");
        int idAutor = teclado.nextInt();
        String SQL = "DELETE FROM Libro_Autor WHERE idLibro = ? AND idAutor = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, idLibro);
        ps.setInt(2, idAutor);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Libro eliminado");
    }

    public void ActualizarLibro_Autor() throws SQLException{
        System.out.println("¿Qué quieres cambiar? 1- IdLibro 2-IdAutor");
        int opcion = teclado.nextInt();
        if (opcion == 1) {
            System.out.println("Dime el id del Libro a actualizar: "); //Para saber el IdAutor de lo que vamos a actualizar.
            int idLibro = teclado.nextInt();
            String SQL = "SELECT idAutor from Libro_Autor WHERE idLibro = ?";
            PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            int idAutor = rs.getInt("idAutor"); //Lo guardamos
            //SEGUNDA PARTE
            System.out.println("Dime el nuevo id: ");
            int idLibroNuevo = teclado.nextInt();
            String SQL1 = "UPDATE Libro_Autor SET idLibro, idAutor VALUES ?, ?"; //Actualizamos el campo donde esté es idLibro.
            PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL1);
            ps2.setInt(1, idLibroNuevo);
            ps2.setInt(2, idAutor);
            ps2.executeUpdate();
        } else if (opcion == 2){
            System.out.println("Dime el id del Autor a actualizar: "); //Para saber el IdLibro de lo que vamos a actualizar.
            int idAutor = teclado.nextInt();
            String SQL = "SELECT idLibro from Libro_Autor WHERE idAutor = ?";
            PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
            ps.setInt(1, idAutor);
            ResultSet rs = ps.executeQuery();
            int idLibro = rs.getInt("idLibro"); //Lo guardamos
            //SEGUNDA PARTE
            System.out.println("Dime el nuevo id: ");
            int idAutorNuevo = teclado.nextInt();
            String SQL1 = "UPDATE Libro_Autor SET idAutor, idLibro VALUES ?, ?"; //Actualizamos el campo donde esté es idLibro.
            PreparedStatement ps2 = Conexion.crearConexion().prepareStatement(SQL1);
            ps2.setInt(1, idAutorNuevo);
            ps2.setInt(2, idLibro);
            ps2.executeUpdate();
        }
    }
}

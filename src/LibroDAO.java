import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibroDAO {

    ArrayList<Libro> listaLibros = new ArrayList<>();;
    Scanner teclado = new Scanner(System.in);
    private Connection conexion;
    public LibroDAO(Connection conexion){
        this.conexion = conexion;
    }

    //Método Insertar
    public Libro crearLibro(){
        Libro Libro = new Libro();
        System.out.println("Introduce el nombre del Libro: ");
        String titulo = teclado.nextLine();
        System.out.println("Introduce el ISBN del libro: ");
        String isbn = teclado.nextLine();

        String SQL = "INSERT INTO Libro (titulo , isbn) VALUES (? , ?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1, titulo);
            ps.setString(2, isbn);
            ps.executeUpdate();
            Libro = new Libro(titulo, isbn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Libro;
    }
    //Método Leer
    public ArrayList<Libro> leerLibros(){
        listaLibros.clear();
        String SQL = "SELECT * FROM Libro";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String isbn = rs.getString("isbn");
                Libro libro = new Libro(id, titulo, isbn);
                listaLibros.add(libro);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaLibros;
    }
    //Método Eliminar
    public void eliminaLibro(){
        System.out.println("Indica el id que quiere eliminar: ");
        int isbn = teclado.nextInt();
        teclado.nextLine();

        String SQL = "DELETE FROM LIBRO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, isbn);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    //Metodo Actualizar
    public Libro actualizarLibro() throws SQLException{
        Libro libro = new Libro();
        System.out.println("Introduce el id del libro que quieres actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();


        String SQL = "SELECT COUNT(*) FROM LIBRO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1)==0){
            System.out.println("El libro " + libro + " no existe en la Base de Datos");

        }else {
            System.out.println("Introduce el nuevo titulo: ");
            String nombre = teclado.nextLine();

            System.out.println("Introduce el nuevo ISBN: ");
            String ISBN = teclado.nextLine();

            String SQLactualizar = "UPDATE LIBRO SET TITULO = ?, ISBN = ? WHERE ID = ? ";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);

            ps1.setString(1, nombre);
            ps1.setString(2,ISBN);
            ps1.setInt(3, id);
            ps1.executeUpdate();

            libro = new Libro(nombre, ISBN);
            listaLibros.add(libro);
        }
        return libro;
    }
}

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
        System.out.println("Introduce el nombre del autor: ");
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
        System.out.println("Indica el isbn que quiere eliminar: ");
        int isbn = teclado.nextInt();
        teclado.nextLine();
        for (Libro libro:listaLibros){
            if (libro.getIsbn().equals(isbn)){
                String SQL = "DELETE FROM AUTOR WHERE Isbn = ?";
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    ps.setInt(1, isbn);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else
                System.out.println("No hay autor con este isbn");
        }

    }

    //Metodo Actualizar
    public void actualizarLibro(){
        System.out.println("Introduce el id del libro que quieres actualizar: ");
        int id = teclado.nextInt();

        for (Libro libro:listaLibros){
            String SQL = "UPDATE LIBRO SET TITULO, VALUES = ? WHERE ID = ?";
            if (id == libro.getId_libro()){
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    System.out.println("Introduce el nuevo titulo");
                    String titulo = teclado.nextLine();
                    ps.setString(1, titulo);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class AutorDAO {
    ArrayList<Autor> listaAutores = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    public Autor crearAutor(){
        Autor autor = new Autor();
        System.out.println("Introduce el nombre del autor: ");
        String nombre = teclado.nextLine();

        String SQL = "INSERT INTO Autor (nombre) VALUES (?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1,nombre);
            ps.executeUpdate();
            autor = new Autor(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    }

    public ArrayList<Autor> leerAutor(){
        listaAutores.clear();
        String SQL = "SELECT * FROM AUTOR";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Autor autor = new Autor(id, nombre);
                listaAutores.add(autor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaAutores;
    }

    public void eliminaAutor(){
        System.out.println("Indica el id que quiere eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        for (Autor autor:listaAutores){
            if (id == autor.getId_autor()){
                String SQL = "DELETE FROM AUTOR WHERE ID = ?";
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else
                System.out.println("No hay autor con este id");
        }

    }

    public void actualizarAutor(){
        System.out.println("Introduce el id del autor que quieres actualizar: ");
        int id = teclado.nextInt();

        for (Autor autor: listaAutores){
            if (id == autor.getId_autor()){
                String SQL = "UPDATE  AUTOR SET NOMBRE, VALUES = ? WHERE ID = ?";
                try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
                    System.out.println("Introduce el nuevo nombre: ");
                    String nombre = teclado.nextLine();
                    ps.setString(1,nombre);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class AutorDAO {
    ArrayList<Autor> listaAutores = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    private Connection conexion;
    public AutorDAO(Connection conexion){
        this.conexion = conexion;
    }

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

        String SQL = "DELETE FROM AUTOR WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Autor actualizarAutor() throws SQLException{
        Autor autor = new Autor();
        System.out.println("Introduce el id del autor que quieres actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();


        String SQL = "SELECT COUNT(*) FROM AUTOR WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1)==0){
            System.out.println("El autor " + autor + " no existe en la Base de Datos");

        }else {
            System.out.println("Introduce el nuevo nombre: ");
            String nombre = teclado.nextLine();

            String SQLactualizar = "UPDATE AUTOR SET NOMBRE = ? WHERE ID = ? ";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);

            ps1.setString(1, nombre);
            ps1.setInt(2, id);
            ps1.executeUpdate();

            autor = new Autor(nombre);
            listaAutores.add(autor);
        }
        return autor;
    }
}



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioDAO {
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    private Connection conexion;
    public UsuarioDAO(Connection conexion){
        this.conexion = conexion;
    }

    //Metodo crear Usuario
    public Usuario crearUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Introduce el nombre del usuario: ");
        String nombre = teclado.nextLine();

        String SQL = "INSERT INTO USUARIO (NOMBRE) VALUES (?)";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setString(1, nombre);
            ps.executeUpdate();
            usuario = new Usuario(nombre);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    //Metodo leer Usuario
    public ArrayList<Usuario> leerUsuario(){
        listaUsuarios.clear();
        String SQL = "SELECT * FROM USUARIO";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Usuario usuario = new Usuario(id, nombre);
                listaUsuarios.add(usuario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    //Metodo eliminar usuario
    public void eliminarUsuario(){
        System.out.println("Indica el id del usuario que quieres eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        String SQL = "DELETE FROM USUARIO WHERE ID = ?";
        try (PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //Metodo actualizar usuario
    public Usuario actualizarUsuario() throws SQLException{
        Usuario usuario = new Usuario();
        System.out.println("Introduce el id del usuario que quieres actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();


        String SQL = "SELECT COUNT(*) FROM USUARIO WHERE ID = ?";
        PreparedStatement ps = Conexion.crearConexion().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1)==0){
            System.out.println("El usuario " + usuario + " no existe en la Base de Datos");

        }else {
            System.out.println("Introduce el nuevo nombre: ");
            String nombre = teclado.nextLine();


            String SQLactualizar = "UPDATE USUARIO SET NOMBRE = ? WHERE ID = ? ";
            PreparedStatement ps1 = Conexion.crearConexion().prepareStatement(SQLactualizar);

            ps1.setString(1, nombre);
            ps1.setInt(2, id);
            ps1.executeUpdate();

            usuario = new Usuario();
            listaUsuarios.add(usuario);
        }
        return usuario;
    }
}

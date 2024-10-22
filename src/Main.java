import InterfazGrafica.vista;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        AutorDAO autorDAO = new AutorDAO(Conexion.crearConexion());
        Libro_AutorDAO libro_autorDAO = new Libro_AutorDAO(Conexion.crearConexion());
        LibroDAO libroDAO = new LibroDAO(Conexion.crearConexion());
        PrestamoDAO prestamoDAO = new PrestamoDAO(Conexion.crearConexion());
        UsuarioDAO usuarioDAO = new UsuarioDAO(Conexion.crearConexion());
        Sincronizacion sincronizacion = new Sincronizacion(Conexion.crearConexion());

        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("-------------------");
            System.out.println("1. CREAR AUTOR ");
            System.out.println("2. CREAR LIBRO ");
            System.out.println("3. CREAR PRÉSTAMO ");
            System.out.println("4. CREAR USUARIO ");
            System.out.println("5. CREAR LIBROAUTOR");
            System.out.println("6. LISTAR AUTORES");
            System.out.println("7. LISTAR LIBROS");
            System.out.println("8. LISTAR PRÉSTAMOS");
            System.out.println("9. LISTAR USUARIOS");
            System.out.println("10. LISTAR LIBROAUTOR");
            System.out.println("11. ELIMINAR AUTOR ");
            System.out.println("12. ELIMINAR LIBRO ");
            System.out.println("13. ELIMINAR PRÉSTAMO");
            System.out.println("14. ELIMINAR USUARIO");
            System.out.println("15. ELIMINAR LIBROAUTOR");
            System.out.println("16. ACTUALIZAR AUTOR ");
            System.out.println("17. ACTUALIZAR LIBRO ");
            System.out.println("18. ACTUALIZAR PRÉSTAMO");
            System.out.println("19. ACTUALIZAR USUARIO");
            System.out.println("20. ACTUALIZAR LIBROAUTOR");
            System.out.println("0. SALIR ");
            System.out.println("Elige una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> {
                    autorDAO.crearAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 2 -> {
                    libroDAO.crearLibro();
                    sincronizacion.sincronizarLibros();
                }
                case 3 -> {
                    prestamoDAO.crearPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 4 -> {
                    usuarioDAO.crearUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 5 -> {
                    libro_autorDAO.crearLibro_Autor();
                    sincronizacion.sincronizarLibroAutor();
                }
                case 6 -> {
                    autorDAO.leerAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 7 -> {
                    libroDAO.leerLibros();
                    sincronizacion.sincronizarLibros();
                }
                case 8 -> {
                    prestamoDAO.leerPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 9 -> {
                    usuarioDAO.leerUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 10 -> {
                    libro_autorDAO.leerLibro_Autor();
                    sincronizacion.sincronizarLibroAutor();
                }
                case 11 -> {
                    autorDAO.eliminaAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 12 -> {
                    libroDAO.eliminaLibro();
                    sincronizacion.sincronizarLibros();
                }
                case 13 -> {
                    prestamoDAO.eliminaPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 14 -> {
                    usuarioDAO.eliminarUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 15 -> {
                    libro_autorDAO.eliminarLibro_Autor();
                    sincronizacion.sincronizarLibroAutor();
                }
                case 16 -> {
                    autorDAO.actualizarAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 17 -> {
                    libroDAO.actualizarLibro();
                    sincronizacion.sincronizarLibros();
                }
                case 18 -> {
                    prestamoDAO.actualizarPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 19 -> {
                    usuarioDAO.actualizarUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 20 -> {
                    libro_autorDAO.ActualizarLibro_Autor();
                    sincronizacion.sincronizarLibroAutor();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                }
            }

        } while (opcion !=0);
    }
}
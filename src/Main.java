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
            System.out.println("1. CREAR AUTOR ");
            System.out.println("2. CREAR LIBRO ");
            System.out.println("3. CREAR PRÉSTAMO ");
            System.out.println("4. CREAR USUARIO ");
            System.out.println("5. LISTAR AUTORES");
            System.out.println("6. LISTAR LIBROS");
            System.out.println("7. LISTAR PRÉSTAMOS");
            System.out.println("8. LISTAR USUARIOS");
            System.out.println("9. ELIMINAR AUTOR ");
            System.out.println("10. ELIMINAR LIBRO ");
            System.out.println("11. ELIMINAR PRESTAMO");
            System.out.println("12. ELIMINAR USUARIO");
            System.out.println("13. ACTUALIZAR AUTOR ");
            System.out.println("14. ACTUALIZAR LIBRO ");
            System.out.println("15. ACTUALIZAR PRESTAMO");
            System.out.println("16. ACTUALIZAR USUARIO");
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
                    autorDAO.leerAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 6 -> {
                    libroDAO.leerLibros();
                    sincronizacion.sincronizarLibros();
                }
                case 7 -> {
                    prestamoDAO.leerPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 8 -> {
                    usuarioDAO.leerUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 9 -> {
                    autorDAO.eliminaAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 10 -> {
                    libroDAO.eliminaLibro();
                    sincronizacion.sincronizarLibros();
                }
                case 11 -> {
                    prestamoDAO.eliminaPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 12 -> {
                    usuarioDAO.eliminarUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 13 -> {
                    autorDAO.actualizarAutor();
                    sincronizacion.sincronizarAutores();
                }
                case 14 -> {
                    libroDAO.actualizarLibro();
                    sincronizacion.sincronizarLibros();
                }
                case 15 -> {
                    prestamoDAO.actualizarPrestamo();
                    sincronizacion.sincronizarPrestamos();
                }
                case 16 -> {
                    usuarioDAO.actualizarUsuario();
                    sincronizacion.sincronizarUsuarios();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                }
            }
        }while (opcion != 0);
    }
}
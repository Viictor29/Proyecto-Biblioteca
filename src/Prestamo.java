import java.util.Date;

public class Prestamo {
    String stringFechaInicio;
    String stringFechaFin;
    private int id_prestamo;
    private Date fechaFin, fechaInicio;
    private int idUsuario, idLibro;

    //Constructor vacio y general
    public Prestamo() {
    }

    public Prestamo(Date fechaFin, Date fechaInicio) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
    }

    public Prestamo(int id_prestamo, Date fechaFin, Date fechaInicio) {
        this.id_prestamo = id_prestamo;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
    }
    public Prestamo(int id_prestamo, String fechaFin, String fechaInicio, int idUsuario, int idLibro) {
        this.id_prestamo = id_prestamo;
        this.stringFechaInicio = fechaInicio;
        this.stringFechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    //Getters y Setters

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Id del prestamo: " + id_prestamo + ", Fecha de inicio: " + stringFechaInicio + ", Fecha de fin: " + stringFechaFin + ", idUsuario: " + idUsuario + ", idLibro: " + idLibro;
    }
}
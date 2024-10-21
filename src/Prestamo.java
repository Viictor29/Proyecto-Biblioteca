import java.sql.Date;

public class Prestamo {
    private int id_prestamo;
    private Date fechaFin, fechaInicio;

    //Constructor vacio y general
    public Prestamo() {
    }

    public Prestamo(int id_prestamo, Date fechaFin, Date fechaInicio) {
        this.id_prestamo = id_prestamo;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
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

    //toString
    @Override
    public String toString() {
        return "Id del prestamo: " + id_prestamo + ", Fecha de inicio: " + fechaInicio + ", Fecha de fin: " + fechaFin;
    }
}

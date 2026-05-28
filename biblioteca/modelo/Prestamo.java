package biblioteca.modelo;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Representa a un Prestamo dentro del sistema de biblioteca.
 * Esta clase actúa como un Modelo de datos .
 * @author Noelia Alfonso
 */

public class Prestamo {

    private static long idPrestamoGlobal= 1000;
    private long idPrestamo=0;
    private Alumno alumno= null;
    private List<Libro> libros= null;
    private LocalDate fechaPrestamo= null;
    private LocalDate fechaLimite=null ;
    private LocalDate fechaDevolucion= null;


    public Prestamo(Alumno alumno, List<Libro> libros){
        idPrestamoGlobal++;
        this.idPrestamo= idPrestamoGlobal;
        this.alumno=alumno;
        this.libros= libros;
        this.fechaPrestamo = LocalDate.now();
        //La fecha limite sera 15 dias despues de realizar el prestamo.
        this.fechaLimite = this.fechaPrestamo.plusDays(15);
        this.fechaDevolucion=null;
    }

    //Metodos getters
    public Alumno getAlumno(){
        return alumno;
    }

    public  long getId() {
        return idPrestamo;
    }
    public static long getIdPrestamoGlobal(){
        return idPrestamoGlobal;
    }

    public List<Libro> getLibros(){
        return libros;
    }

    public LocalDate getFechaPrestamo(){
        return fechaPrestamo;
    }

    public LocalDate getFechaLimite(){
        return fechaLimite;
    }

    public LocalDate getFechaDevolucion(){
        return fechaDevolucion;
    }

    //Metodos setters
    public void setAlumno(Alumno alumno){
        this.alumno=alumno;
    }

    public void setLibros(List<Libro> libros){
        this.libros=libros;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo){
        this.fechaPrestamo=fechaPrestamo;

    }
    public void setFechaLimite(LocalDate fechaLimite){
        this.fechaLimite= fechaLimite;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }



    public void devolverPrestamo(){
        this.fechaDevolucion= LocalDate.now();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Prestamo)){
            return false;
        }
        Prestamo prestamo= (Prestamo) obj;
        return prestamo.getId()== this.getId();

    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, alumno, libros, fechaPrestamo, fechaLimite, fechaDevolucion);
    }

    /**
     * Devuelve una representación en texto de los datos del prestamo.
     * @return
     */
    @Override
    public String toString(){
        return  "Prestamo  ["+ this.idPrestamo +" | Alumno: " + this.alumno.getNombreCompleto() + " | Libros: " + this.libros +
                " | Fecha Prestamo: " + this.fechaPrestamo + " | Fecha Limite: " + this.fechaLimite
                +  " | Fecha Devolucion : " + this.fechaDevolucion  +"]";
    }


}



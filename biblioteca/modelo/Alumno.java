package biblioteca.modelo;
import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;

/**
 * Representa a un Alumno dentro del sistema de biblioteca.
 * Esta clase actúa como un Modelo de datos .
 * * @author Noelia Alfonso
 */

public class Alumno implements Serializable {

    private String nombreCompleto="";
    private int ci=0;
    private String email="";
    private String telefono="";
    private LocalDate fechaNacimiento=null;
    private String facultadPerteneciente= "";

    public Alumno(String nombreCompleto, int ci, String email, String telefono, LocalDate fechaNacimiento,String facultadPerteneciente){
        this.nombreCompleto= nombreCompleto;
        this.ci= ci;
        this.email= email;
        this.telefono= telefono;
        this.fechaNacimiento= fechaNacimiento;
        this.facultadPerteneciente= facultadPerteneciente;
    }

    // Metodos getters.
    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public int getCi(){
        return ci;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefono(){
        return telefono;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public String getFacultadPerteneciente(){
        return facultadPerteneciente;
    }

    //Metodos setters.
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto= nombreCompleto;
    }

    public void setCi(int ci){
        this.ci=ci;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setTelefono(String telefono){
        this.telefono=telefono;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento= fechaNacimiento;
    }

    public void setFacultadPerteneciente(String facultadPerteneciente){
        this.facultadPerteneciente=facultadPerteneciente;
    }

    /**
     * Compara si dos alumnos son iguales basándose exclusivamente en su C.I.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Alumno)){
            return false;
        }
        Alumno alumno= (Alumno) obj;
        return alumno.getCi()==this.getCi();

    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCompleto, ci, email, telefono, fechaNacimiento, facultadPerteneciente);
    }

    /**
     * Devuelve una representación en texto de los datos del alumno.
     * @return
     */
    @Override
    public String toString(){
        return "Alumno [" + this.nombreCompleto +
                " | C.I.: " + this.ci +
                " | Email: " + this.email + " | Telefono: " + this.telefono +
                " | Fecha de Nacimiento: " + this.fechaNacimiento +
                " | Facultad: " + this.facultadPerteneciente + "]";
    }



}

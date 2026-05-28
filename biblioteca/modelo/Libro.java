package biblioteca.modelo;
import java.util.Objects;
/**
 * Representa a un Libro dentro del sistema de biblioteca.
 * Esta clase actúa como un Modelo de datos .
 * @author Noelia Alfonso
 */
public class Libro  {

    private long id=0;
    private String titulo="";
    private String editorial="";
    private int anhoPublicacion=0;
    private String autor="";


    public Libro( long id,String titulo, String editorial, int anhoPublicacion, String autor) {
        this.id=id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.anhoPublicacion = anhoPublicacion;
        this.autor = autor;
    }

    //Metodos getters
    public long getId() {
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getEditorial(){
        return editorial;
    }

    public int getAnhoPublicacion(){
        return anhoPublicacion;
    }

    public String getAutor(){
        return autor;
    }

    //Metodos setters
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setEditorial(String editorial){
        this.editorial = editorial;
    }

    public void setAnhoPublicacion(int anhoPublicacion){
        this.anhoPublicacion = anhoPublicacion;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Libro)){
            return false;
        }
        Libro libro= (Libro) obj;
        return libro.getId()== this.getId();

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, editorial, anhoPublicacion, autor);
    }

    /**
     * Devuelve una representación en texto de los datos del libro.
     * @return
     */
    @Override
    public String toString() {
        return "Libro [" + this.titulo + " | ID: " + this.getId() +" | Autor: " + this.autor +
                " | Editorial: " + this.editorial + " | Año: " + this.anhoPublicacion + "]";
    }
}

package biblioteca.repositorio;
import java.util.List;
/**
 * Define las operaciones básicas de acceso a datos para crear un repositorio.
 * @param <M> El tipo de modelo que gestiona el repositorio.
 */
public interface Repositorio<M> {

    public boolean crear(M m );

    public boolean borrar(M m);

    public List<M> getList();

}

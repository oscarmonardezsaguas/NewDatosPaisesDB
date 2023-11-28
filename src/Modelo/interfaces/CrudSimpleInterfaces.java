
package Modelo.interfaces;

import java.util.List;

public interface CrudSimpleInterfaces<T> {
    public List<T> listar(String texto);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public boolean eliminar(String id); 
    public boolean existe(String texto);
    public List<T> buscar(String texto);
}


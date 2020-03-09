package basedatos;

/**
 * Interfaz que contiene las variables que necesita la base de datos.
 * @author Javier Rodríguez Bravo
 */
public interface BaseDatos {
    public int cargar();
    public void guardar(int nuevaMuerte);
}

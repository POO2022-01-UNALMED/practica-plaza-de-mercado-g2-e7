package gestorAplicacion.clientes;

public interface Informacion {
    /**
     * Este método retorna la información completa de la clase, que puede ser utilizada
     * para crear reportes y por ende listar las personas o simplemente ver la información
     * de una persona
     * @return La información completa de la clase
     */
    public abstract String retornarInformacion();
}

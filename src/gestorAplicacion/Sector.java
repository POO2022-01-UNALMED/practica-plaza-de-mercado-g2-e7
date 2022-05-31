package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Sector
 * Esta clase es la clase que permite administrar la información de los sectores
 * @author: Daniel Puentes
 */
public class Sector implements Serializable {

    // Atributos
    private int codigo;
    private String nombre;
    private int precioBaseM2;
    protected ArrayList<Local> locales;

    static List<Sector> sectores;
    static {
        sectores = new ArrayList<Sector>();
    }

    // Constantes
    private static final long serialVersionUID = 1L;

    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Sector() {
        codigo = 0;
        nombre = "";
        precioBaseM2 = 0;
        locales = new ArrayList<>();
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Sector(int codigo, String nombre, int precioBaseM2, ArrayList<Local> locales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBaseM2 = precioBaseM2;
        this.locales = locales;
        sectores.add(this);
    }

    /**
     * Este método retorna la información completa de la clase, incluyendo la de los locales,
     * puede ser utilizada para crear reportes y por ende listar los sectores o simplemente ver la información de un sector
     * @return La información completa de la clase
     */
    public String retornarInformacion() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio base por M2: $" + precioBaseM2 + "\n\nInformacion de los locales\n\n" + retornarInformacionLocales();
    }

    /**
     * Este método retorna la información completa de la clase, sin incluir la de los locales,
     * puede ser utilizada para crear reportes y por ende listar los sectores o simplemente ver 
     * la información de un sector de forma resumida
     * @return La información completa de la clase
     */
    public String retornarInformacionSinLocales() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio base por M2: $" + precioBaseM2;
    }

    /**
     * Este método se encarga de recibir el listado de dueños que están registrados
     * en la plaza y una cédula buscada, y retorna el resultado de la búsqueda
     * @param duenhos existentes de la plaza
     * @param cedula buscada
     * @return Indice del listado si lo encuentra al dueño, o -1 si no se encuentra
     */
    public boolean buscarDuenho(ArrayList<Duenho> duenhos, int cedula) {
        for (Duenho duenho : duenhos)
            if (duenho.getCedula() == cedula)
                return true;
        return false;
    }

    /**
     * Este método se encarga de recibir el listado de dueños que están registrados
     * en la plaza, y retornar la información de cada uno, para asi poder crear reportes
     * o simplemente mostrar la información
     * @param duenhos existentes de la plaza
     * @return La información de todos los dueños de la plaza
     */
    public String mostrarDuenhos(ArrayList<Duenho> duenhos) {
        String informacion = "";
        for (Duenho duenho : duenhos)
            informacion += duenho.retornarInformacionCorta() + "\n\n";
        return informacion;
    }

    /**
     * Este método se encarga de retornar la información de todos los
     * locales pertenecientes al sector
     * @return La información de todos los locales del sector
     */
    public String retornarInformacionLocales() {
        String informacion = "";
        for (Local local : locales)
            informacion += local.retornarInformacion() + "\n\n";
        return informacion;
    }

    /**
     * Este método retorna el código del sector
     * @return Código del sector
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Este método asigna el código al sector
     * @param codigo a asignar
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Este método retorna el nombre del sector
     * @return Nombre del sector
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Este método asigna el nombre al sector
     * @param nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este método retorna el precio base por m2 del sector
     * @return Precio base del sector
     */
    public int getPrecioBaseM2() {
        return this.precioBaseM2;
    }

    /**
     * Este método asigna el precio base por m2 al sector
     * @param precioBaseM2 a asignar
     */
    public void setPrecioBaseM2(int precioBaseM2) {
        this.precioBaseM2 = precioBaseM2;
    }

    /**
     * Este método retorna el listado de locales del sector
     * @return Listado de locales del sector
     */
    public ArrayList<Local> getLocales() {
        return this.locales;
    }

    /**
     * Este método asigna la lista de locales al sector
     * @param locales a asignar
     */
    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }

    public static List<Sector> getSectores() {
        return sectores;
    }

    public static void setSectores(List<Sector> sectores) {
        Sector.sectores = sectores;
    }

    public String toString() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio base por M2: $" + precioBaseM2;
    }
}

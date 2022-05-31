package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Local
 * Esta clase es la clase que permite administrar la información de los locales
 * @author: Daniel Puentes
 */

public class Local implements Serializable {
    private int codigo;
    private boolean techo;
    private boolean camaraRefrigerante;
    private int tamanho;
    private int precioBase;
    private int cedulaDuenho;
    private boolean ocupado;

    // Constantes
    private static final long serialVersionUID = 1L;

    static List<Local> locales;
    static {
        locales = new ArrayList<Local>();
    }

    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Local() {
        codigo = 0;
        techo = false;
        camaraRefrigerante = false;
        tamanho = 0;
        precioBase = 0;
        cedulaDuenho = 0;
        ocupado = false;
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Local(int codigo, boolean techo, boolean camaraRefrigerante, int tamanho, int precioBase, int cedulaDuenho, boolean ocupado) {
        this.codigo = codigo;
        this.techo = techo;
        this.camaraRefrigerante = camaraRefrigerante;
        this.tamanho = tamanho;
        this.precioBase = precioBase;
        this.cedulaDuenho = cedulaDuenho;
        this.ocupado = ocupado;
        locales.add(this);
    }
    
    /**
     * Este método retorna la información completa de la clase, que puede ser utilizada para crear reportes
     * y por ende listar los locales o simplemente ver la información de un local
     * @return La información completa de la clase
     */
    public String retornarInformacion() {
        return "Codigo: " + codigo + "\nTiene techo: " + (techo ? "Si" : "No") + "\nTiene camara refrigerante: "
                + (camaraRefrigerante ? "Si" : "No") + "\nTamaño: " + tamanho + "m2\nPrecio base dado por el dueño: $"
                + precioBase + "\nCedula del dueño: " + cedulaDuenho + "\nOcupado: " + (ocupado ? "Si" : "No");
    }

    /**
     * Este método retorna el código del local
     * @return Código del local
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Este método asigna el código al local
     * @param codigo a asignar
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Este método retorna si el local tiene techo o no
     * @return Local tiene techo
     */
    public boolean isTecho() {
        return this.techo;
    }

    /**
     * Este método retorna el valor del techo del local
     * @return Techo del local
     */
    public boolean getTecho() {
        return this.techo;
    }

    /**
     * Este método asigna el estado del techo al local
     * @param techo a asignar
     */
    public void setTecho(boolean techo) {
        this.techo = techo;
    }

    /**
     * Este método retorna si el local tiene camara refrigerante o no
     * @return Local tiene camara refrigerante
     */
    public boolean isCamaraRefrigerante() {
        return this.camaraRefrigerante;
    }

    /**
     * Este método retorna la camara refrigerante del local
     * @return Camara refrigerante del local
     */
    public boolean getCamaraRefrigerante() {
        return this.camaraRefrigerante;
    }

    /**
     * Este método asigna el estado de la camara refrigerante al local
     * @param camaraRefrigerante a asignar
     */
    public void setCamaraRefrigerante(boolean camaraRefrigerante) {
        this.camaraRefrigerante = camaraRefrigerante;
    }

    /**
     * Este método retorna el tamaño del local
     * @return Tamaño del local
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * Este método asigna el tamaño al local
     * @param tamanho a asignar
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Este método retorna el precio base del local
     * @return Precio base del local
     */
    public int getPrecioBase() {
        return this.precioBase;
    }

    /**
     * Este método asigna el precio base al local
     * @param precioBase a asignar
     */
    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Este método retorna la cédula del dueño del local
     * @return Cédula del dueño del local
     */
    public int getCedulaDuenho() {
        return this.cedulaDuenho;
    }

    /**
     * Este método asigna la cédula del dueño al local
     * @param cedulaDuenho a asignar
     */
    public void setCedulaDuenho(int cedulaDuenho) {
        this.cedulaDuenho = cedulaDuenho;
    }

    /**
     * Este método retorna si el local está ocupado
     * @return Local está ocupado
     */
    public boolean isOcupado() {
        return this.ocupado;
    }

    /**
     * Este método retorna si el local está ocupado
     * @return Local está ocupado
     */
    public boolean getOcupado() {
        return this.ocupado;
    }

    /**
     * Este método asigna el estado de ocupación al local
     * @param ocupado a asignar
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public static List<Local> getLocales() {
        return locales;
    }

    public static void setLocales(List<Local> locales) {
        Local.locales = locales;
    }
}

package gestorAplicacion.infraestructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Local
 * Esta clase es la clase que permite administrar la informacion de los locales
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
        locales.add(this);
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
     * Este metodo retorna la informacion completa de la clase, que puede ser utilizada para crear reportes
     * y por ende listar los locales o simplemente ver la informacion de un local
     * @return La informacion completa de la clase
     */
    public String retornarInformacion() {
        return "Codigo: " + codigo + "\nTiene techo: " + (techo ? "Si" : "No") + "\nTiene camara refrigerante: "
                + (camaraRefrigerante ? "Si" : "No") + "\ntamanho: " + tamanho + "m2\nPrecio base dado por el duenho: $"
                + precioBase + "\nCedula del duenho: " + cedulaDuenho + "\nOcupado: " + (ocupado ? "Si" : "No");
    }

    /**
     * Este metodo retorna el codigo del local
     * @return codigo del local
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Este metodo asigna el codigo al local
     * @param codigo a asignar
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Este metodo retorna si el local tiene techo o no
     * @return Local tiene techo
     */
    public boolean isTecho() {
        return this.techo;
    }

    /**
     * Este metodo retorna el valor del techo del local
     * @return Techo del local
     */
    public boolean getTecho() {
        return this.techo;
    }

    /**
     * Este metodo asigna el estado del techo al local
     * @param techo a asignar
     */
    public void setTecho(boolean techo) {
        this.techo = techo;
    }

    /**
     * Este metodo retorna si el local tiene camara refrigerante o no
     * @return Local tiene camara refrigerante
     */
    public boolean isCamaraRefrigerante() {
        return this.camaraRefrigerante;
    }

    /**
     * Este metodo retorna la camara refrigerante del local
     * @return Camara refrigerante del local
     */
    public boolean getCamaraRefrigerante() {
        return this.camaraRefrigerante;
    }

    /**
     * Este metodo asigna el estado de la camara refrigerante al local
     * @param camaraRefrigerante a asignar
     */
    public void setCamaraRefrigerante(boolean camaraRefrigerante) {
        this.camaraRefrigerante = camaraRefrigerante;
    }

    /**
     * Este metodo retorna el tamanho del local
     * @return tamanho del local
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * Este metodo asigna el tamanho al local
     * @param tamanho a asignar
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Este metodo retorna el precio base del local
     * @return Precio base del local
     */
    public int getPrecioBase() {
        return this.precioBase;
    }

    /**
     * Este metodo asigna el precio base al local
     * @param precioBase a asignar
     */
    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Este metodo retorna la cedula del duenho del local
     * @return cedula del duenho del local
     */
    public int getCedulaDuenho() {
        return this.cedulaDuenho;
    }

    /**
     * Este metodo asigna la cedula del duenho al local
     * @param cedulaDuenho a asignar
     */
    public void setCedulaDuenho(int cedulaDuenho) {
        this.cedulaDuenho = cedulaDuenho;
    }

    /**
     * Este metodo retorna si el local esta ocupado
     * @return Local esta ocupado
     */
    public boolean isOcupado() {
        return this.ocupado;
    }

    /**
     * Este metodo retorna si el local esta ocupado
     * @return Local esta ocupado
     */
    public boolean getOcupado() {
        return this.ocupado;
    }

    /**
     * Este metodo asigna el estado de ocupaci�n al local
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

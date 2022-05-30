package gestorAplicacion;

/**
 * Clase: Local
 * Esta clase es la clase que permite administrar la informacion de los locales
 * @author: Daniel Puentes
 */

public class Local {
    private int codigo;
    private boolean techo;
    private boolean camaraRefrigerante;
    private int tamanho;
    private int precioBase;
    private int cedulaDuenho;
    private boolean ocupado;
    
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
    }
    
    /**
     * Este metodo retorna la informacion completa de la clase, que puede ser utilizada para crear reportes 
     * y por ende listar los locales o simplemente ver la informacion de un local
     * @return La informacion completa de la clase
     */
    public String retornarInformacion() {
        return "Codigo: " + codigo + "\nTiene techo: " + (techo ? "Si" : "No") + "\nTiene camara refrigerante: "
                + (camaraRefrigerante ? "Si" : "No") + "\nTamaño: " + tamanho + "m2\nPrecio base dado por el dueño: $"
                + precioBase + "\nCedula del dueño: " + cedulaDuenho + "\nOcupado: " + (ocupado ? "Si" : "No");
    }

    /**
     * Este metodo retorna el codigo del local
     * @return Codigo del local
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Este metodo asigna el codigo al local
     * @param Codigo a asignar
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
     * @param Techo a asignar
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
     * @param CamaraRefrigerante a asignar
     */
    public void setCamaraRefrigerante(boolean camaraRefrigerante) {
        this.camaraRefrigerante = camaraRefrigerante;
    }

    /**
     * Este metodo retorna el tamaño del local
     * @return Tamaño del local
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * Este metodo asigna el tamaño al local
     * @param Tamanho a asignar
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
     * @param PrecioBase a asignar
     */
    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Este metodo retorna la cedula del dueño del local
     * @return Cedula del dueño del local
     */
    public int getCedulaDuenho() {
        return this.cedulaDuenho;
    }

    /**
     * Este metodo asigna la cedula del dueño al local
     * @param CedulaDuenho a asignar
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
     * Este metodo asigna el estado de ocupacion al local
     * @param Ocupado a asignar
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
	
}

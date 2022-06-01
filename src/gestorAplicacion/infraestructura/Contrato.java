package gestorAplicacion.infraestructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase: Contrato
 * Esta clase es la clase que permite almacenar los datos necesarios, para poder crear un contrato
 * @author: Daniel Puentes
 */

public class Contrato implements Serializable {

    private int numero;
    private Date fechaInicio;
    private Date fechaFin;
    private String fechaInicioSTR;
    private String fechaFinSTR;
    private int montoMensual;
    private String nombreInterventor;
    private int cedulaCliente;
    private int codigoLocal;

    // Constantes
    private static final long serialVersionUID = 1L;

    static List<Contrato> Contratos;
    static {
        Contratos = new ArrayList<Contrato>();
    }

    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Contrato() {
        numero = 0;
        fechaInicio = null;
        fechaFin = null;
        montoMensual = 0;
        nombreInterventor = "";
        cedulaCliente = 0;
        codigoLocal = 0;
        Contratos.add(this);
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Contrato(int numero, Date fechaInicio, Date fechaFin, int montoMensual, String nombreInterventor, int cedulaCliente, int codigoLocal) {
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoMensual = montoMensual;
        this.nombreInterventor = nombreInterventor;
        this.cedulaCliente = cedulaCliente;
        this.codigoLocal = codigoLocal;
        Contratos.add(this);
    }

    /**
     * Este metodo retorna la informacion que tiene almacenada
     */
    public String retornarInformacion() {

        return "Numero de contrato: " + numero + "\nFecha de inicio: " + fechaInicioSTR + "\nFecha de finalizacion: "
                + fechaFinSTR + "\nMonto mensual: " + montoMensual + "\nNombre del interventor: " + nombreInterventor
                + "\nCedula del Contrato: " + cedulaCliente + "\nCodigo del local: " + codigoLocal;

    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getMontoMensual() {
        return this.montoMensual;
    }

    public void setMontoMensual(int montoMensual) {
        this.montoMensual = montoMensual;
    }

    public String getNombreInterventor() {
        return this.nombreInterventor;
    }

    public void setNombreInterventor(String nombreInterventor) {
        this.nombreInterventor = nombreInterventor;
    }

    public int getCedulaCliente() {
        return this.cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public int getCodigoLocal() {
        return this.codigoLocal;
    }

    public void setCodigoLocal(int codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public static List<Contrato> getContratos() {
        return Contratos;
    }

    public static void setContratos(List<Contrato> Contratos) {
        Contrato.Contratos = Contratos;
    }
}

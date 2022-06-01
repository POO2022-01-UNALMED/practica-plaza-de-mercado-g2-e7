package gestorAplicacion.clientes;

/**
 * Clase: Persona
 * Esta clase es abstracta, ya que no es de interés crear instancias de ella.
 * Se utilizará para que las clases Duenho, Cliente y Codeudor hereden sus atributos
 * e implementen los métodos obtenerDatos() y retornarInformacion()
 * @author: Daniel Puentes
 */

public abstract class Persona {
    protected int cedula;
    protected String nombre;
    protected int telefono;
    protected String direccion;
    protected char genero;
    protected String estadoCivil;

    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Persona() {
        cedula = 0;
        nombre = "";
        telefono = 0;
        direccion = "";
        genero = ' ';
        estadoCivil = "";
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Persona(int cedula, String nombre, int telefono, String direccion, char genero, String estadoCivil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
    }

    /**
     * Este método retorna la información completa de la clase, que puede ser utilizada
     * para crear reportes y por ende listar las personas o simplemente ver la información
     * de una persona
     * @return La información completa de la clase
     */
    public abstract String retornarInformacion();

    /**
     * Este método retorna la cédula y el nombre de la persona, y se implementó para
     * que al momento de listar las personas se diera una información resumida
     * @return La cédula y el nombre de la persona
     */
    public String retornarInformacionCorta() {
        return "Cedula: " + cedula + "\nNombre: " + nombre;
    }

    /**
     * Este método retorna la cédula de la persona
     * @return Cédula de la persona
     */
    public int getCedula() {
        return this.cedula;
    }

    /**
     * Este método asigna la cédula a la persona
     * @param cedula a asignar
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * Este método retorna el nombre de la persona
     * @return Nombre de la persona
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Este método asigna el nombre a la persona
     * @param nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este método retorna el teléfono de la persona
     * @return Teléfono de la persona
     */
    public int getTelefono() {
        return this.telefono;
    }

    /**
     * Este método asigna el télefono a la persona
     * @param telefono a asignar
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Este método retorna la dirección de la persona
     * @return Dirección de la persona
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Este método asigna la dirección a la persona
     * @param direccion a asignar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Este método retorna el género de la persona
     * @return Género de la persona
     */
    public char getGenero() {
        return this.genero;
    }

    /**
     * Este método asigna el género a la persona
     * @param genero a asignar
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }

    /**
     * Este método retorna el estado civil de la persona
     * @return Estado civil de la persona
     */
    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    /**
     * Este método asigna el estado civil a la persona
     * @param estadoCivil a asignar
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

}

package gestorAplicacion;

/**
 * Clase: Persona
 * Esta clase es abstracta, ya que no es de interes crear instancias de ella
 * Se utilizara para que las clases Duenho, Cliente y Codeudor hereden sus atributos
 * E implementen los métodos obtenerDatos() y retornarInformacion()
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
     * Este metodo retorna la informacion completa de la clase, que puede ser utilizada
     * para crear reportes y por ende listar las personas o simplemente ver la informacion
     * de una persona
     * @return La informacion completa de la clase
     */
    public abstract String retornarInformacion();

    /**
     * Este metodo retorna la cedula y el nombre de la persona, y se implemento para
     * que al momento de listar las personas se diera una informacion resumida
     * @return La cedula y el nombre de la persona
     */
    protected String retornarInformacionCorta() {
        return "Cedula: " + cedula + "\nNombre: " + nombre;
    }

    /**
     * Este metodo retorna la cedula de la persona
     * @return Cedula de la persona
     */
    public int getCedula() {
        return this.cedula;
    }

    /**
     * Este metodo asigna la cedula a la persona
     * @param cedula a asignar
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * Este metodo retorna el nombre de la persona
     * @return Nombre de la persona
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Este metodo asigna el nombre a la persona
     * @param nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este metodo retorna el telefono de la persona
     * @return Telefono de la persona
     */
    public int getTelefono() {
        return this.telefono;
    }

    /**
     * Este metodo asigna el telefono a la persona
     * @param telefono a asignar
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Este metodo retorna la direccion de la persona
     * @return Direccion de la persona
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Este metodo asigna la direccion a la persona
     * @param direccion a asignar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Este metodo retorna el genero de la persona
     * @return Genero de la persona
     */
    public char getGenero() {
        return this.genero;
    }

    /**
     * Este metodo asigna el genero a la persona
     * @param genero a asignar
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }

    /**
     * Este metodo retorna el estado civil de la persona
     * @return Estado civil de la persona
     */
    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    /**
     * Este metodo asigna el estado civil a la persona
     * @param estadoCivil a asignar
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

}

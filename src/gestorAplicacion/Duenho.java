package gestorAplicacion;

/**
 * Clase: Duenho
 * Esta clase es la clase que permite administrar la informacion de los dueños
 * @author: Daniel Puentes
 */

public class Duenho extends Persona{
	
	/**
     * Este constructor es usado para llamar al constructor de la clase Persona
     * y asi estar seguros de que se van a generar unos datos iniciales
     */
    public Duenho() {
    	super();
    }

    /**
     * Este metodo se encarga de leer los atributos de la clase, y es implementado
     * desde su clase heredera Persona
     */
    public String obtenerDatos() { 
    	return "Ingrese el nombre del dueño" + this.getNombre() +
    		"Ingrese el telefono del dueño" + this.getTelefono() +
    		"Ingrese la direccion del dueño" + this.getDireccion() +
    		"Ingrese el genero del dueño (M o F)" + this.getGenero() +
    		"Ingrese el estado civil del dueño" + this.getEstadoCivil();
    }

    /**
     * Este metodo es implementado de la clase Persona, retorna la cedula y el nombre de la persona, 
     * y se implemento para que al momento de listar las personas se diera una informacion resumida
     * @return La cedula y el nombre de la persona
     */
    public String retornarInformacion() {
        return "Cedula: " + cedula + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nDireccion: " + direccion
                + "\nGenero: " + genero + "\nEstado civil: " + estadoCivil;
    }
}

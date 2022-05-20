package gestorAplicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Codeudor
 * Esta clase es la clase que permite administrar la informacion de los codeudores
 * @author: Daniel Puentes
 */

public class Codeudor extends Persona{
	
	static List<Codeudor> codeudores;
	static {
		 codeudores = new ArrayList<Codeudor>();
	 }
	/**
     * Este constructor es usado para llamar al constructor de la clase Persona
     * y asi estar seguros de que se van a generar unos datos iniciales
     */
    public Codeudor() {
    	super();
    	codeudores.add(this);
    }

    /**
     * Este metodo se encarga de leer los atributos de la clase, y es implementado
     * desde su clase heredera Persona
     * @return 
     */
    public String obtenerDatos() {
    	return "Ingrese el nombre del codeudor" + this.getNombre() +
		"Ingrese el telefono del codeudor" + this.getTelefono() +
		"Ingrese la direccion del codeudor" + this.getDireccion() +
		"Ingrese el genero del codeudor (M o F)" + this.getGenero() +
		"Ingrese el estado civil del codeudor" + this.getEstadoCivil();
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
    
    public static List<Codeudor> getCodeudores() {
		return codeudores;
	}

	public static void setCodeudores(List<Codeudor> codeudores) {
		Codeudor.codeudores = codeudores;
	}
}

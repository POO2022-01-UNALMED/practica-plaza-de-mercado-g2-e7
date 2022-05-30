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

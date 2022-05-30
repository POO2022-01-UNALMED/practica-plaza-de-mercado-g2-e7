package gestorAplicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Duenho
 * Esta clase es la clase que permite administrar la informacion de los due�os
 * @author: Daniel Puentes
 */

public class Duenho extends Persona{
	
	static List<Duenho> duenhos;
	static {
		duenhos = new ArrayList<Duenho>();
	}
	
	/**
     * Este constructor es usado para llamar al constructor de la clase Persona
     * y asi estar seguros de que se van a generar unos datos iniciales
     */
    public Duenho() {
    	super();
    	duenhos.add(this);
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
    
    public static List<Duenho> getduenhos() {
		return duenhos;
	}

	public static void setduenhos(List<Duenho> duenhos) {
		Duenho.duenhos = duenhos;
	}
}

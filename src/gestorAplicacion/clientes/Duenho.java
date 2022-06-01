package gestorAplicacion.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Duenho
 * Esta clase es la clase que permite administrar la informaci�n de los due�os
 * @author: Daniel Puentes
 */

public class Duenho extends Persona implements Serializable {

	// Constantes
	private static final long serialVersionUID = 1L;

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

	public Duenho(int cedula, String nombre, int telefono, String direccion, char genero, String estadoCivil) {
		super(cedula, nombre, telefono, direccion, genero, estadoCivil);
		duenhos.add(this);
	}

	/**
	 * Este m�todo es implementado de la clase Persona, retorna la c�dula y el nombre de la persona,
	 * y se implement� para que al momento de listar las personas se diera una informaci�n resumida
	 * @return La c�dula y el nombre de la persona
	 */
	public String retornarInformacion() {
		return "Cedula: " + cedula + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nDireccion: " + direccion
				+ "\nGenero: " + genero + "\nEstado civil: " + estadoCivil;
	}

	public static List<Duenho> getDuenhos() {
		return duenhos;
	}

	public static void setDuenhos(List<Duenho> duenhos) {
		Duenho.duenhos = duenhos;
	}

}


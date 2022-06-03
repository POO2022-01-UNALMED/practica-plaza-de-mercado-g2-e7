package gestorAplicacion.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: Cliente
 * Esta clase es la clase que permite administrar la información de los clientes
 * @author: Daniel Puentes
 */

public class Cliente extends Persona {

	private int cedulaCodeudor1;
	private int cedulaCodeudor2;
	static List<Cliente> clientes;
	static {
		clientes = new ArrayList<Cliente>();
	}

	/**
	 * Este constructor es usado para llamar al constructor de la clase Persona
	 * y asi estar seguros de que se van a generar unos datos iniciales,
	 * además inicializa la cédula de ambos codeudores
	 */
	public Cliente() {
		super();
		cedulaCodeudor1 = 0;
		cedulaCodeudor2 = 0;
		clientes.add(this);
	}

	/**
	 * Este constructor sirve para inicializar los datos con valores
	 * dados al momento de instanciar la clase, además de también llamar
	 * al constructor de Persona para inicializar los atributos heredados
	 */
	public Cliente(int cedulaCodeudor1, int cedulaCodeudor2) {
		super();
		this.cedulaCodeudor1 = cedulaCodeudor1;
		this.cedulaCodeudor2 = cedulaCodeudor2;
		clientes.add(this);
	}


	/**
	 * Este método es implementado de la clase Persona, retorna la información completa de la clase,
	 *  que puede ser utilizada para crear reportes y por ende listar las personas o simplemente ver
	 *  la información de una persona
	 * @return La información completa de la clase
	 */
	public String retornarInformacion() {
		return "Cedula: " + cedula + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nDireccion: " + direccion
				+ "\nGenero: " + genero + "\nEstado civil: " + estadoCivil + "\nCedula del codeudor #1: "
				+ cedulaCodeudor1 + "\nCedula del codeudor #2: " + cedulaCodeudor2;
	}

	/**
	 * Este método retorna la cédula del codeudor #1 del cliente
	 * @return Cédula del codeudor #1
	 */
	public int getCedulaCodeudor1() {
		return this.cedulaCodeudor1;
	}

	/**
	 * Este método asigna la cédula del codeudor #1 al cliente
	 * @param cedulaCodeudor1 a asignar
	 */
	public void setCedulaCodeudor1(int cedulaCodeudor1) {
		this.cedulaCodeudor1 = cedulaCodeudor1;
	}

	/**
	 * Este método retorna la cédula del codeudor #2 del cliente
	 * @return Cédula del codeudor #2
	 */
	public int getCedulaCodeudor2() {
		return this.cedulaCodeudor2;
	}

	/**
	 * Este método asigna la cédula del codeudor #2 al cliente
	 * @param cedulaCodeudor2 a asignar
	 */
	public void setCedulaCodeudor2(int cedulaCodeudor2) {
		this.cedulaCodeudor2 = cedulaCodeudor2;
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(List<Cliente> clientes) {
		Cliente.clientes = clientes;
	}
}

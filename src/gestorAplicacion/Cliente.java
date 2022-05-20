package gestorAplicacion;

/**
 * Clase: Cliente
 * Esta clase es la clase que permite administrar la informacion de los clientes
 * @author: Daniel Puentes
 */

public class Cliente extends Persona{

	 private int cedulaCodeudor1;
	 private int cedulaCodeudor2;

	 /**
	  * Este constructor es usado para llamar al constructor de la clase Persona
	  * y asi estar seguros de que se van a generar unos datos iniciales,
	  * ademas inicializa la cedula de ambos codeudores
	  */
	 public Cliente() {
		 super();
	     cedulaCodeudor1 = 0;
	     cedulaCodeudor2 = 0;
	 }
	 
	 /**
	  * Este constructor sirve para inicializar los datos con valores
	  * dados al momento de instanciar la clase, ademas de tambien llamar
	  * a el constructor de Persona para inicializar los atributos heredados
	  */
	 public Cliente(int cedulaCodeudor1, int cedulaCodeudor2) {
		 super();
	     this.cedulaCodeudor1 = cedulaCodeudor1;
	     this.cedulaCodeudor2 = cedulaCodeudor2;
	 }
	 
	 /**
	  * Este metodo se encarga de leer los atributos de la clase, y es implementado
	  * desde su clase heredera Persona
	 * @return 
	  */
	 public String obtenerDatos() {
		return "Ingrese el nombre del cliente" + this.getNombre() +
 		"Ingrese el telefono del cliente" + this.getTelefono() +
 		"Ingrese la direccion del cliente" + this.getDireccion() +
 		"Ingrese el genero del cliente (M o F)" + this.getGenero() +
 		"Ingrese el estado civil del cliente" + this.getEstadoCivil();
	 }
	 
	 /**
	  * Este metodo es implementado de la clase Persona, retorna la informacion completa de la clase,
	  *  que puede ser utilizada para crear reportes y por ende listar las personas o simplemente ver
	  *  la informacion de una persona
	  * @return La informacion completa de la clase
	  */
	 public String retornarInformacion() {
		 return "Cedula: " + cedula + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nDireccion: " + direccion
				 + "\nGenero: " + genero + "\nEstado civil: " + estadoCivil + "\nCedula del codeudor #1: "
	             + cedulaCodeudor1 + "\nCedula del codeudor #2: " + cedulaCodeudor2;
	 }
	 
	 /**
	  * Este metodo retorna la cedula del codeudor #1 del cliente
	  * @return Cedula del codeudor #1
	  */
	 public int getCedulaCodeudor1() {
		 return this.cedulaCodeudor1;
	 }

	 /**
	  * Este metodo asigna la cedula del codeudor #1 al cliente
	  * @param Cedula del codeudor #1 a asignar
	  */
	 public void setCedulaCodeudor1(int cedulaCodeudor1) {
	     this.cedulaCodeudor1 = cedulaCodeudor1;
	 }

	 /**
	  * Este metodo retorna la cedula del codeudor #2 del cliente
	  * @return Cedula del codeudor #2
	  */
	 public int getCedulaCodeudor2() {
	     return this.cedulaCodeudor2;
	 }

	 /**
	  * Este metodo asigna la cedula del codeudor #2 al cliente
	  * @param Cedula del codeudor #2 a asignar
	  */
	 public void setCedulaCodeudor2(int cedulaCodeudor2) {
		 this.cedulaCodeudor2 = cedulaCodeudor2;
	 }
}
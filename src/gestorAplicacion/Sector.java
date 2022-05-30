package gestorAplicacion;
import uMain.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase: Sector
 * Esta clase es la clase que permite administrar la información de los sectores
 * @author: Daniel Puentes
 */
public class Sector {

    Scanner sc = new Scanner(System.in);

    Main main= new Main();
    // Atributos
    private int codigo;
    private String nombre;
    private int precioBaseM2;
    private ArrayList<Local> locales;

    static List<Sector> sectores;
    static {
        sectores = new ArrayList<Sector>();
    }

    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Sector() {
        codigo = 0;
        nombre = "";
        precioBaseM2 = 0;
        locales = new ArrayList<>();
        sectores.add(this);
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Sector(int codigo, String nombre, int precioBaseM2, ArrayList<Local> locales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBaseM2 = precioBaseM2;
        this.locales = locales;
        sectores.add(this);
    }

    /**
     * Este metodo retorna la informacion completa de la clase, incluyendo la de los locales,
     * puede ser utilizada para crear reportes y por ende listar los sectores o simplemente ver la informacion de un sector
     * @return La informacion completa de la clase
     */
    public String retornarInformacion() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio base por M2: $" + precioBaseM2 + "\n\nInformacion de los locales\n\n" + retornarInformacionLocales();
    }

    /**
     * Este metodo retorna la informacion completa de la clase, sin incluir la de los locales,
     * puede ser utilizada para crear reportes y por ende listar los sectores o simplemente ver 
     * la informacion de un sector de forma resumida
     * @return La informacion completa de la clase
     */
    public String retornarInformacionSinLocales() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio base por M2: $" + precioBaseM2;
    }

    /**
     * Este método se encarga de recibir el listado de dueños que están registrados
     * en la plaza y un código que será para el nuevo local
     * Se selecciona el dueño, se crea un local, se leen los datos de este
     * y finalmente se agrega el nuevo local a la lista de locales del sector
     * @param duenhos
     * @param codigoLocal
     */
    public void agregarLocal(ArrayList<Duenho> duenhos, int codigoLocal) {
        Local nuevoLocal = new Local();
        nuevoLocal.obtenerDatos();
        nuevoLocal.setCodigo(codigoLocal);

        int cedulaDuenho = -1;

        boolean cedulaExistente = false;

        while (!cedulaExistente) {

            main.mostrarMensajes("Ingrese la cédula del dueño\n\n" + mostrarDuenhos(duenhos));

            cedulaDuenho = sc.nextInt();

            cedulaExistente = buscarDuenho(duenhos, cedulaDuenho);

            if (!cedulaExistente) 
                main.mostrarMensajes("Ingrese una cédula valida");

        }

        nuevoLocal.setCedulaDuenho(cedulaDuenho);

        locales.add(nuevoLocal);

        main.mostrarMensajes("Local agregado correctamente al sector");

    }

    /**
     * Este metodo se encarga de recibir el listado de dueños que estan registrados
     * en la plaza y una cedula buscada, y retorna el resultado de la busqueda
     * @param duenhos existentes de la plaza
     * @param cedula buscada
     * @return Indice del listado si lo encuentra al due�o, o -1 si no se encuentra
     */
    public boolean buscarDuenho(ArrayList<Duenho> duenhos, int cedula) {
        for (Duenho duenho : duenhos)
            if (duenho.getCedula() == cedula)
                return true;
        return false;
    }

    /**
     * Este metodo se encarga de recibir el listado de dueños que estan registrados
     * en la plaza, y retornar la informacion de cada uno, para asi poder crear reportes
     * o simplemente mostrar la informacion
     * @param duenhos existentes de la plaza
     * @return La informacion de todos los dueños de la plaza
     */
    public String mostrarDuenhos(ArrayList<Duenho> duenhos) {
        String informacion = "";
        for (Duenho duenho : duenhos)
            informacion += duenho.retornarInformacionCorta() + "\n\n";
        return informacion;
    }

    /**
     * Este metodo se encarga de retornar la informacion de todos los
     * locales pertenecientes al sector
     * @return La informacion de todos los locales del sector
     */
    public String retornarInformacionLocales() {
        String informacion = "";
        for (Local local : locales)
            informacion += local.retornarInformacion() + "\n\n";
        return informacion;
    }

    /**
     * Este metodo retorna el codigo del sector
     * @return Codigo del sector
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Este metodo asigna el codigo al sector
     * @param codigo a asignar
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Este metodo retorna el nombre del sector
     * @return Nombre del sector
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Este metodo asigna el nombre al sector
     * @param nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este metodo retorna el precio base por m2 del sector
     * @return Precio base del sector
     */
    public int getPrecioBaseM2() {
        return this.precioBaseM2;
    }

    /**
     * Este metodo asigna el precio base por m2 al sector
     * @param precioBaseM2 a asignar
     */
    public void setPrecioBaseM2(int precioBaseM2) {
        this.precioBaseM2 = precioBaseM2;
    }

    /**
     * Este metodo retorna el listado de locales del sector
     * @return Listado de locales del sector
     */
    public ArrayList<Local> getLocales() {
        return this.locales;
    }

    /**
     * Este metodo asigna la lista de locales al sector
     * @param locales a asignar
     */
    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }

    public static List<Sector> getSectores() {
        return sectores;
    }

    public static void setSectores(List<Sector> sectores) {
        Sector.sectores = sectores;
    }
}

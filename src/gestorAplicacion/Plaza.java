package gestorAplicacion;

import java.util.ArrayList;

/**
 * Clase: Plaza
 * Esta clase es la clase que permite administrar la información de la plaza
 * @author: Daniel Puentes
 */
public class Plaza {

    // Constantes
    public final static String nombre = "Frutas y verduras";
    public final static double porcentajeAumentoConTecho = 1.15; // 15%
    public final static double porcentajeAumentoConCamaraRefrigerante = 1.15; // 15%

    // Atributos
    private ArrayList<Sector> sectores;
    private ArrayList<Duenho> duenhos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Codeudor> codeudores;
    private ArrayList<Contrato> contratos;



    /**
     * Este constructor sirve para inicializar los datos y asi evitar errores
     */
    public Plaza() {
        sectores = new ArrayList<>();
        duenhos = new ArrayList<>();
        clientes = new ArrayList<>();
        codeudores = new ArrayList<>();
        contratos = new ArrayList<>();
    }

    /**
     * Este constructor sirve para inicializar los datos con valores
     * dados al momento de instanciar la clase
     */
    public Plaza(ArrayList<Sector> sectores, ArrayList<Duenho> duenhos, ArrayList<Cliente> clientes, ArrayList<Codeudor> codeudores, ArrayList<Contrato> contratos) {
        this.sectores = sectores;
        this.duenhos = duenhos;
        this.clientes = clientes;
        this.codeudores = codeudores;
        this.contratos = contratos;
    }
    

    /**
     * Este método se encarga de contar y retornar la cantidad de locales existentes en la plaza
     * @return La cantidad de locales existentes en la plaza
     */
    public int obtenerCantidadLocales() {
        int nLocales = 0;

        for (Sector sector : sectores)
            nLocales += sector.getLocales().size();

        return nLocales;
    }


    /**
     * Este método se encarga de buscar el índice de un local con un código dado
     * @param codigoLocal buscado
     * @return El índice del sector y el índice del local respectivamente si se encuentra o {-1, -1} si no se encuentra
     */
    public int[] buscarLocal(int codigoLocal) {

        for (int i = 0; i < sectores.size(); i++) // Recorriendo sectores
            for (int j = 0; j < sectores.get(i).getLocales().size(); j++) // Recorriendo locales de cada sector
                if (sectores.get(i).getLocales().get(j).getCodigo() == codigoLocal)
                    return new int[] {i, j};

        return new int[] {-1, -1};

    }



    /**
     * Este método se encarga de mostrar la información de los sectores de la plaza,
     * permitiendo crear y mostrar listados y reportes
     * @return La información de los sectores de la plaza
     */
    public String mostrarSectores() {
        String informacion = "";

        for (Sector sector : sectores)
            informacion += sector.retornarInformacionSinLocales() + "\n\n";

        return informacion;
    }

    /**
     * Este método se encarga de mostrar la información de los locales de la plaza,
     * permitiendo crear y mostrar listados y reportes
     * @return La información de los locales de la plaza
     */
    public String mostrarLocales() {
        String informacion = "";

        for (Sector sector : sectores)
            informacion += sector.retornarInformacionLocales() + "\n\n";

        return informacion;
    }

    /**
     * Este método se encarga de mostrar la información de los codeudores de la plaza,
     * permitiendo crear y mostrar listados y reportes
     * @return La información de los codeudores de la plaza
     */
    public String mostrarCodeudores() {
        String informacion = "";

        for (Codeudor codeudor : codeudores)
            informacion += codeudor.retornarInformacionCorta() + "\n\n";

        return informacion;
    }

    /**
     * Este método se encarga de mostrar la información de los clientes de la plaza,
     * permitiendo crear y mostrar listados y reportes
     * @return La información de los clientes de la plaza
     */
    public String mostrarClientes() {
        String informacion = "";

        for (Cliente cliente : clientes)
            informacion += cliente.retornarInformacionCorta() + "\n\n";

        return informacion;
    }

    /**
     * Este método se encarga de buscar el índice del un sector con una codigo dado
     * @param codigo buscada
     * @return El índice del sector buscado si se encuentra, si no -1
     */
    public int buscarSector(int codigo) {
        for (int i = 0; i < sectores.size(); i++)
            if (sectores.get(i).getCodigo() == codigo)
                return i;

        return -1;
    }

    /**
     * Este método se encarga de buscar el índice del un dueño con una cédula dada
     * @param cedula buscada
     * @return El índice del dueño buscado si se encuentra, si no -1
     */
    public int buscarDuenho(int cedula) {
        for (int i = 0; i < duenhos.size(); i++)
            if (duenhos.get(i).getCedula() == cedula)
                return i;

        return -1;
    }

    /**
     * Este método se encarga de buscar el índice del un cliente con una cédula dada
     * @param cedula buscada
     * @return El índice del cliente buscado si se encuentra, si no -1
     */
    public int buscarCliente(int cedula) {
        for (int i = 0; i < clientes.size(); i++)
            if (clientes.get(i).getCedula() == cedula)
                return i;

        return -1;
    }

    /**
     * Este método se encarga de buscar el indice del un codeudor con una cédula dada
     * @param cedula buscada
     * @return El índice del codeudor buscado si se encuentra, si no -1
     */
    public int buscarCodeudor(int cedula) {
        for (int i = 0; i < codeudores.size(); i++)
            if (codeudores.get(i).getCedula() == cedula)
                return i;

        return -1;
    }

    /**
     * Este método retorna el listado de sectores de la plaza
     * @return Listado de sectores de la plaza
     */
    public ArrayList<Sector> getSectores() {
        return this.sectores;
    }

    /**
     * Este método asigna la lista de sectores de la plaza
     * @param sectores a asignar
     */
    public void setSectores(ArrayList<Sector> sectores) {
        this.sectores = sectores;
    }

    /**
     * Este método retorna el listado de dueños de la plaza
     * @return Listado de dueños de la plaza
     */
    public ArrayList<Duenho> getDuenhos() {
        return this.duenhos;
    }

    /**
     * Este método asigna la lista de dueños de la plaza
     * @param duenhos a asignar
     */
    public void setDuenhos(ArrayList<Duenho> duenhos) {
        this.duenhos = duenhos;
    }

    /**
     * Este método retorna el listado de clientes de la plaza
     * @return Listado de clientes de la plaza
     */
    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    /**
     * Este método asigna la lista de clientes de la plaza
     * @param clientes a asignar
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Este método retorna el listado de codeudores de la plaza
     * @return Listado de codeudores de la plaza
     */
    public ArrayList<Codeudor> getCodeudores() {
        return this.codeudores;
    }

    /**
     * Este método asigna la lista de codeudores de la plaza
     * @param codeudores a asignar
     */
    public void setCodeudores(ArrayList<Codeudor> codeudores) {
        this.codeudores = codeudores;
    }

    /**
     * Este método retorna el listado de contratos de la plaza
     * @return Listado de contratos de la plaza
     */
    public ArrayList<Contrato> getContratos() {
        return this.contratos;
    }

    /**
     * Este método asigna la lista de contratos de la plaza
     * @param contratos a asignar
     */
    public void setContratos(ArrayList<Contrato> contratos) {
        this.contratos = contratos;
    }

}

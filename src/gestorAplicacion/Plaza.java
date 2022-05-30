package gestorAplicacion;

import uMain.Main;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase: Plaza
 * Esta clase es la clase que permite administrar la información de la plaza
 * @author: Daniel Puentes
 */
public class Plaza {

    Scanner sc = new Scanner(System.in);
    Main main= new Main();
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
     * Este metodo se encarga de crear un sector nuevo y agregarlo a la lista
     */
    public void agregarSector() {
        Sector nuevoSector = new Sector();
        main.obtenerDatos(nuevoSector);
        nuevoSector.setCodigo(sectores.size() + 1);

        sectores.add(nuevoSector);
    }

    /**
     * Este metodo se encarga de crear un local nuevo dentro de un sector dado y agregarlo
     * a la lista de locales dentro de dicho sector
     */
    public void agregarLocal() {

        if (sectores.size() == 0) {
            main.mostrarMensajes("Antes de agregar locales debe agregar sectores a la plaza");
        }
        else {
            if (duenhos.size() == 0) {
                main.mostrarMensajes("Antes de agregar locales debe agregar dueños");
            } else {

                int codigoSector = -1;

                int indiceSector = -1;

                while (indiceSector == -1) {

                    main.mostrarMensajes("Ingrese el codigo del sector donde sera agregado el local\n\n" + mostrarSectores());

                    codigoSector = sc.nextInt();

                    indiceSector = buscarSector(codigoSector);

                    if (indiceSector == -1)
                        main.mostrarMensajes("Ingrese un codigo de sector valido");

                }

                sectores.get(indiceSector).agregarLocal(duenhos, obtenerCantidadLocales() + 1);

            }

        }
    }

    /**
     * Este método se encarga de crear un dueño nuevo y agregarlo a la lista de dueños
     */
    public void registrarDuenho() {

        int cedulaDuenho = -1;

        boolean cedulaExistente = true;

        while (cedulaExistente) {

            main.mostrarMensajes("Ingrese la cedula del dueño");

            cedulaDuenho = sc.nextInt();

            if (buscarDuenho(cedulaDuenho) != -1){
                main.mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
            }

            else {
                cedulaExistente = false;
            }
        }

        Duenho nuevoDuenho = new Duenho();
        nuevoDuenho.setCedula(cedulaDuenho);
        main.obtenerDatos(nuevoDuenho);

        duenhos.add(nuevoDuenho);

        main.mostrarMensajes("Dueño registrado correctamente");

    }

    /**
     * Este metodo se encarga de crear un cliente nuevo y agregarlo a la lista de clientes
     */
    public void registrarCliente() {

        if (codeudores.size() == 0 || codeudores.size() == 1)
            main.mostrarMensajes("Para registrar un cliente deben existir por lo menos 2 codeudores");

        else {

            int cedulaCliente = -1;

            boolean cedulaExistente = true;

            while (cedulaExistente) {

                main.mostrarMensajes("Ingrese la cedula del cliente");

                cedulaCliente = sc.nextInt();

                if (buscarCliente(cedulaCliente) != -1){
                    main.mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
                }

                else {
                        cedulaExistente = false;
                    }
            }

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setCedula(cedulaCliente);
            main.obtenerDatos(nuevoCliente);

            int cedulaCodeudor1 = codeudores.get(seleccionarCodeudor(1)).getCedula();
            int cedulaCodeudor2;

            do {

                cedulaCodeudor2 = codeudores.get(seleccionarCodeudor(2)).getCedula();

                if (cedulaCodeudor1 == cedulaCodeudor2)
                    main.mostrarMensajes("Los codeudores deben ser distintos");

            } while (cedulaCodeudor1 == cedulaCodeudor2);

            clientes.add(nuevoCliente);

            main.mostrarMensajes("Cliente registrado correctamente");

        }

    }

    /**
     * Este metodo se encarga de crear un codeudor nuevo y agregarlo a la lista de codeudores
     */
    public void registrarCodeudor() {

        int cedulaCodeudor = -1;

        boolean cedulaExistente = true;

        while (cedulaExistente) {

            main.mostrarMensajes("Ingrese la cedula del codeudor");

            cedulaCodeudor = sc.nextInt();

            if (buscarCodeudor(cedulaCodeudor) != -1){
                main.mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
            }

            else {
                cedulaExistente = false;
            }

        }

        Codeudor nuevoCodeudor = new Codeudor();
        nuevoCodeudor.setCedula(cedulaCodeudor);
        main.obtenerDatos(nuevoCodeudor);

        codeudores.add(nuevoCodeudor);

        main.mostrarMensajes("Codeudor registrado correctamente");

    }

    /**
     * Este metodo se encarga elegir un local y un cliente, para proceder a alquilar un local
     * y luego de esto se genera un contrato y es agregado a la lista de contratos
     */
    public void alquilarLocal() {

        if (clientes.size() == 0)
            main.mostrarMensajes("Para alquilar un local primero debe registrar clientes");

        else if (sectores.size() == 0)
            main.mostrarMensajes("Para alquilar un local primero debe agregar sectores");

        else if (obtenerCantidadLocales() == 0)
            main.mostrarMensajes("Para alquilar un local primero debe agregar locales");

        else {

            int cedulaCliente = clientes.get(seleccionarCliente()).getCedula();

            int[] indicesLocal = seleccionarLocal();
            sectores.get(indicesLocal[0]).getLocales().get(indicesLocal[1]).setOcupado(true);
            Local local = sectores.get(indicesLocal[0]).getLocales().get(indicesLocal[1]);

            int codigoLocal = local.getCodigo();
            int montoMensual = local.getPrecioBase() + (sectores.get(indicesLocal[0]).getPrecioBaseM2() * local.getTamanho());
            montoMensual *= (local.getTecho()) ? porcentajeAumentoConTecho : 1;
            montoMensual *= (local.getCamaraRefrigerante()) ? porcentajeAumentoConCamaraRefrigerante : 1;
            montoMensual *= 1.2;

            Contrato contrato = new Contrato();
            contrato.setNumero(contratos.size() + 1);
            contrato.setCedulaCliente(cedulaCliente);
            contrato.setCodigoLocal(codigoLocal);
            contrato.setMontoMensual(montoMensual);
            main.obtenerDatos(contrato);

            contratos.add(contrato);

            main.mostrarMensajes("Alquiler completado correctamente");

        }
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
     * Este metodo se encarga de leer el codigo de un local y retornar el inidice de este
     * @return El indice del sector y del local respectivamente si se encuentra o {-1, -1} si no se encuentra
     */
    public int[] seleccionarLocal() {

        int[] indices = {-1, -1}; // Retorna el indice del sector y del local respectivamente

        do {

            main.mostrarMensajes("Ingrese el codigo del local\n\n" + mostrarLocales());

            indices = buscarLocal(sc.nextInt());

            if (indices[0] == - 1 || indices[1] == -1)
                main.mostrarMensajes("No se encontro ningun local con el codigo seleccionado");

        } while (indices[0] == - 1 || indices[1] == -1);

        return indices;

    }

    /**
     * Este metodo se encarga de buscar el indice de un local con un codigo dado
     * @param codigoLocal buscado
     * @return El indice del sector y el indice del local respectivamente si se encuentra o {-1, -1} si no se encuentra
     */
    public int[] buscarLocal(int codigoLocal) {

        for (int i = 0; i < sectores.size(); i++) // Recorriendo sectores
            for (int j = 0; j < sectores.get(i).getLocales().size(); j++) // Recorriendo locales de cada sector
                if (sectores.get(i).getLocales().get(j).getCodigo() == codigoLocal)
                    return new int[] {i, j};

        return new int[] {-1, -1};

    }

    /**
     * Este metodo lee una cedula y la busca, retornando el indice del cliente
     * @return El indice del cliente si lo encontro o -1 sino
     */
    public int seleccionarCliente() {

        int indice = -1;

        do {

            main.mostrarMensajes("Ingrese la cedula del cliente\n\n" + mostrarClientes());

            indice = buscarCliente(sc.nextInt());

            if (indice == -1)
                main.mostrarMensajes("No existe ningun cliente registrado con esta cedula");

        } while (indice == -1);

        return indice;

    }

    /**
     * Este metodo lee una cedula y la busca, retornando el indice del codeudor
     * @param n, que es el # del codeudor y puede ser 1 o 2
     * @return El indice del codeudor si lo encontro o -1 sino
     */
    public int seleccionarCodeudor(int n) {

        int indice = -1;

        do {

            main.mostrarMensajes("Ingrese la cedula del codeudor #" + n + "\n\n" + mostrarCodeudores());

            indice = buscarCodeudor(sc.nextInt());

            if (indice == -1)
                main.mostrarMensajes("No existe ningun codeudor registrado con esta cedula");

        } while (indice == -1);

        return indice;

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
     * @return La informacion de los clientes de la plaza
     */
    public String mostrarClientes() {

        String informacion = "";

        for (Cliente cliente : clientes)
            informacion += cliente.retornarInformacionCorta() + "\n\n";

        return informacion;

    }

    /**
     * Este método se encarga de buscar el indice del un sector con una codigo dado
     * @param codigo buscada
     * @return El indice del sector buscado si se encuentra, sino -1
     */
    public int buscarSector(int codigo) {

        for (int i = 0; i < sectores.size(); i++)
            if (sectores.get(i).getCodigo() == codigo)
                return i;

        return -1;

    }

    /**
     * Este método se encarga de buscar el indice del un dueño con una cédula dada
     * @param cedula buscada
     * @return El indice del dueño buscado si se encuentra, sino -1
     */
    public int buscarDuenho(int cedula) {

        for (int i = 0; i < duenhos.size(); i++)
            if (duenhos.get(i).getCedula() == cedula)
                return i;

        return -1;

    }

    /**
     * Este método se encarga de buscar el indice del un cliente con una cédula dada
     * @param cedula buscada
     * @return El indice del cliente buscado si se encuentra, sino -1
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
     * @return El indice del codeudor buscado si se encuentra, sino -1
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

package uMain;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.clientes.*;
import gestorAplicacion.infraestructura.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import static uMain.Automatizacion.*;


/**
 * Esta clase es la clase que permite administrar el funcionamiento y flujo del sistema
 * @author: Daniel Puentes
 */
public class Main {
    static Scanner sc = new Scanner(System.in);

    static Sector sector = new Sector();

    static Plaza plaza = new Plaza();

    public static void mostrarMensajes(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Este método se encarga de recibir el listado de dueños que están registrados
     * en la plaza y un código que será para el nuevo local
     * Se selecciona el dueño, se crea un local, se leen los datos de este
     * y finalmente se agrega el nuevo local a la lista de locales del sector
     * @param duenhos
     * @param codigoLocal
     */
    public static void agregarLocales(ArrayList<Duenho> duenhos, int codigoLocal) {
        Local nuevoLocal = new Local();
        obtenerDatos(nuevoLocal);
        nuevoLocal.setCodigo(codigoLocal);

        int cedulaDuenho = -1;

        boolean cedulaExistente = false;

        while (!cedulaExistente) {

            mostrarMensajes("Ingrese la cédula del dueño\n\n" + sector.mostrarDuenhos(duenhos) + "\n");

            cedulaDuenho = sc.nextInt();

            cedulaExistente = sector.buscarDuenho(duenhos, cedulaDuenho);

            if (!cedulaExistente)
                mostrarMensajes("Ingrese una cédula valida" + "\n");

        }

        nuevoLocal.setCedulaDuenho(cedulaDuenho);

        sector.getLocales().add(nuevoLocal);

        mostrarMensajes("Local agregado correctamente al sector" + "\n");

    }

    /**
     * Este metodo se encarga de crear un sector nuevo y agregarlo a la lista
     */
    public static void agregarSector() {
        Sector nuevoSector = new Sector();
        obtenerDatos(nuevoSector);
        nuevoSector.setCodigo(plaza.getSectores().size() + 1);

        plaza.getSectores().add(nuevoSector);

        mostrarMensajes("Sector agregado correctamente a la plaza" + "\n");
    }

    /**
     * Este metodo se encarga de crear un local nuevo dentro de un sector dado y agregarlo
     * a la lista de locales dentro de dicho sector
     */
    public static void agregarLocal() {
        if (plaza.getSectores().size() == 0 && Sector.getSectores().size() == 0) {
            mostrarMensajes("Antes de agregar locales debe agregar sectores a la plaza" + "\n");
        }
        else if (plaza.getDuenhos().size() == 0 && Duenho.getDuenhos().size() == 0) {
            mostrarMensajes("Antes de agregar locales debe agregar dueños" + "\n");
        }
        else {
            int codigoSector = -1;

            int indiceSector = -1;

            while (indiceSector == -1) {

                mostrarMensajes("Ingrese el codigo del sector donde sera agregado el local\n\n" + plaza.mostrarSectores() + "\n");

                codigoSector = sc.nextInt();

                indiceSector = plaza.buscarSector(codigoSector);

                if (indiceSector == -1)

                    mostrarMensajes("Ingrese un codigo de sector valido" + "\n");

            }

            /*plaza.getSectores().get(indiceSector).*/agregarLocales(plaza.getDuenhos(), plaza.obtenerCantidadLocales() + 1);

        }
    }


    /**
     * Este método se encarga de crear un dueño nuevo y agregarlo a la lista de dueños
     */
    public static void registrarDuenho() {
        int cedulaDuenho = -1;

        boolean cedulaExistente = true;

        while (cedulaExistente) {

            mostrarMensajes("Ingrese la cedula del dueño");

            cedulaDuenho = sc.nextInt();
            sc.nextLine();

            if (plaza.buscarDuenho(cedulaDuenho) != -1){
                mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta" + "\n");
            }

            else {
                cedulaExistente = false;
            }
        }

        Duenho nuevoDuenho = new Duenho();
        nuevoDuenho.setCedula(cedulaDuenho);
        obtenerDatos(nuevoDuenho);

        plaza.getDuenhos().add(nuevoDuenho);

        mostrarMensajes("Dueño registrado correctamente" + "\n");
    }

    /**
     * Este método se encarga de crear un cliente nuevo y agregarlo a la lista de clientes
     */
    public static void registrarCliente() {
        if ((plaza.getCodeudores().size() == 0 || plaza.getCodeudores().size() == 1)
                && (Codeudor.getCodeudores().size() == 0 || Codeudor.getCodeudores().size() == 1))
            mostrarMensajes("Para registrar un cliente deben existir por lo menos 2 codeudores" + "\n");

        else {
            int cedulaCliente = -1;

            boolean cedulaExistente = true;

            while (cedulaExistente) {

                mostrarMensajes("Ingrese la cedula del cliente");

                cedulaCliente = sc.nextInt();
                sc.nextLine();

                if (plaza.buscarCliente(cedulaCliente) != -1){
                    mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta" + "\n");
                }

                else {
                    cedulaExistente = false;
                }
            }

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setCedula(cedulaCliente);
            obtenerDatos(nuevoCliente);

            int cedulaCodeudor1 = Codeudor.getCodeudores().get(seleccionarCodeudor(1)).getCedula();
            int cedulaCodeudor2;

            do {

                cedulaCodeudor2 = Codeudor.getCodeudores().get(seleccionarCodeudor(2)).getCedula();

                if (cedulaCodeudor1 == cedulaCodeudor2)
                    mostrarMensajes("Los codeudores deben ser distintos" + "\n");

            } while (cedulaCodeudor1 == cedulaCodeudor2);

            Cliente.getClientes().add(nuevoCliente);

            mostrarMensajes("Cliente registrado correctamente" + "\n");

        }
    }

    /**
     * Este método se encarga de crear un codeudor nuevo y agregarlo a la lista de codeudores
     */
    public static void registrarCodeudor() {
        int cedulaCodeudor = -1;

        boolean cedulaExistente = true;

        while (cedulaExistente) {

            mostrarMensajes("Ingrese la cedula del codeudor");

            cedulaCodeudor = sc.nextInt();
            sc.nextLine();

            if (plaza.buscarCodeudor(cedulaCodeudor) != -1){
                mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta" + "\n");
            }

            else {
                cedulaExistente = false;
            }

        }

        Codeudor nuevoCodeudor = new Codeudor();
        nuevoCodeudor.setCedula(cedulaCodeudor);
        obtenerDatos(nuevoCodeudor);

        plaza.getCodeudores().add(nuevoCodeudor);

        mostrarMensajes("Codeudor registrado correctamente" + "\n");
    }

    /**
     * Este método se encarga elegir un local y un cliente, para proceder a alquilar un local
     * y luego de esto se genera un contrato y es agregado a la lista de contratos
     */
    public static void alquilarLocal() {
        if (plaza.getClientes().size() == 0 && Cliente.getClientes().isEmpty())
            mostrarMensajes("Para alquilar un local primero debe registrar clientes" + "\n");

        else if (plaza.getSectores().size() == 0 && Sector.getSectores().isEmpty())
            mostrarMensajes("Para alquilar un local primero debe agregar sectores" + "\n");

        else if (plaza.obtenerCantidadLocales() == 0 && Local.getLocales().isEmpty())
            mostrarMensajes("Para alquilar un local primero debe agregar locales" + "\n");

        else {

            int cedulaCliente = Cliente.getClientes().get(seleccionarCliente()).getCedula();

            int[] indicesLocal = seleccionarLocal();
            Sector.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]).setOcupado(true);
            Local local = Sector.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]);

            int codigoLocal = local.getCodigo();
            int montoMensual = local.getPrecioBase() + (Sector.getSectores().get(indicesLocal[0]).getPrecioBaseM2() * local.getTamanho());
            montoMensual *= (local.getTecho()) ? Plaza.PORCENTAJE_AUMENTO_CON_TECHO : 1;
            montoMensual *= (local.getCamaraRefrigerante()) ? Plaza.PORCENTAJE_AUMENTO_CON_CAMARA_REFRIGERANTE : 1;
            montoMensual *= 1.2;

            Contrato contrato = new Contrato();
            contrato.setNumero(Contrato.getContratos().size() + 1);
            contrato.setCedulaCliente(cedulaCliente);
            contrato.setCodigoLocal(codigoLocal);
            contrato.setMontoMensual(montoMensual);
            obtenerDatos(contrato);

            Contrato.getContratos().add(contrato);

            mostrarMensajes("Alquiler completado correctamente" + "\n");

        }
    }

    /**
     * Este método lee una cédula y la busca, retornando el índice del cliente
     * @return El índice del cliente si lo encontró o -1 si no
     */
    public static int seleccionarCliente() {
        int indice = -1;

        do {
            mostrarMensajes("Ingrese la cedula del cliente\n\n" + plaza.mostrarClientes() + "\n");

            indice = plaza.buscarCliente(sc.nextInt());

            if (indice == -1)
                mostrarMensajes("No existe ningun cliente registrado con esta cedula" + "\n");

        } while (indice == -1);

        return indice;
    }

    /**
     * Este método lee una cédula y la busca, retornando el índice del codeudor
     * @param n, que es el # del codeudor y puede ser 1 o 2
     * @return El índice del codeudor si lo encontró o -1 si no
     */
    public static int seleccionarCodeudor(int n) {

        int indice = -1;

        do {

            mostrarMensajes("Ingrese la cédula del codeudor #" + n + "\n\n" + plaza.mostrarCodeudores() + "\n");

            indice = plaza.buscarCodeudor(sc.nextInt());

            if (indice == -1)
                mostrarMensajes("No existe ningún codeudor registrado con esta cédula" + "\n");

        } while (indice == -1);

        return indice;

    }

    /**
     * Este método muestra un listado de los locales desocupados en el momento
     */
    public static void mostrarLocalesDesocupados() {
        Local local;
        String listado = "Listado de los locales desocupados\n\n";

        for (int i = 0; i < Local.getLocales().size(); i++){
            local = Local.getLocales().get(i);
            if (!local.isOcupado())
                listado += "\n" + local.retornarInformacion() + "\n\n";
            else {
                mostrarMensajes("No hay locales desocupados" + "\n");
            }
        }


        mostrarMensajes(listado);
    }

    /**
     * Este método muestra un listado de los locales ocupados en el momento
     */
    public static void mostrarLocalesOcupados() {
        Local local;
        String listado = "Listado de los locales ocupados\n\n";

        for (int i = 0; i < Local.getLocales().size(); i++) {
            local = Local.getLocales().get(i);
            if (local.isOcupado())
                listado += "\n" + local.retornarInformacion() + "\n\n";
            else {
                mostrarMensajes("No hay locales ocupados" + "\n");
            }
        }
        mostrarMensajes(listado);
    }

    /**
     * Este método muestra el historial de alquiler de un local elegido
     */
    public static void mostrarHistorialAlquilerLocal() {
        if (plaza.obtenerCantidadLocales() == 0 && Local.getLocales().isEmpty())
            mostrarMensajes("No hay locales actualmente" + "\n");

        else {

            int[] indicesLocal = seleccionarLocal();
            Local local = Sector.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]);

            String informacion = "Historial de alquiler del local con codigo: " + local.getCodigo() + "\n\n";
            int totalDineroAdministracion = 0;

            for (Contrato contrato : Contrato.getContratos()) {

                informacion += contrato.retornarInformacion() + "\n";
                totalDineroAdministracion += contrato.getMontoMensual();

            }

            informacion += "\nTotal de dinero recaudado perteneciente a admnistracion: $" + totalDineroAdministracion + "\n";

            mostrarMensajes(informacion);

        }

    }

    /**
     * Este método muestra un listado de los contratos vigentes en una fecha dada
     */
    public static void mostrarListadoContratosVigentes() {
        try {

            mostrarMensajes("Ingrese la fecha a buscar (Año/Mes/Dia)");

            String fechaBusquedaSTR = sc.next();

            Date fechaBusqueda = new SimpleDateFormat("yyyy/MM/dd").parse(fechaBusquedaSTR);

            String informacion = "Listado de contratos vigentes en la fecha " + fechaBusquedaSTR + "\n\n";

            for (Contrato contrato : plaza.getContratos()) {
                if (fechaBusqueda.after(contrato.getFechaInicio()) && fechaBusqueda.before(contrato.getFechaFin()))
                    informacion += contrato.retornarInformacion() + "\n";
            }

            for (Contrato contrato : Contrato.getContratos()) {
                if (fechaBusqueda.after(contrato.getFechaInicio()) && fechaBusqueda.before(contrato.getFechaFin()))
                    informacion += contrato.retornarInformacion() + "\n";
            }

            mostrarMensajes(informacion);

        }
        catch (ParseException ex) {
            mostrarMensajes("La fecha ingresada no es valida" + "\n");
        }

    }

    /**
     * Este método se encarga de leer el código de un local y retornar el índice de este
     * @return El índice del sector y del local respectivamente si se encuentra o {-1, -1} si no se encuentra
     */
    public static int[] seleccionarLocal() {
        int[] indices = {-1, -1}; // Retorna el índice del sector y del local respectivamente

        do {

            mostrarMensajes("Ingrese el codigo del local\n\n" + plaza.mostrarLocales() + "\n");

            indices = plaza.buscarLocal(sc.nextInt());

            if (indices[0] == - 1 || indices[1] == -1)
                mostrarMensajes("No se encontro ningun local con el codigo seleccionado" + "\n");

        } while (indices[0] == - 1 || indices[1] == -1);

        return indices;
    }

    /**
     * Este método se encarga de leer los atributos de la clase Sector
     *
     * @Class Sector
     */
    public static void obtenerDatos(Sector obj) {
        mostrarMensajes("Ingrese el nombre del sector (sin dejar espacios)");
        String nombre = sc.next();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el precio base por m2 del sector");
        int precioBaseM2 = sc.nextInt();
        obj.setPrecioBaseM2(precioBaseM2);
    }

    /**
     * Este método se encarga de leer los atributos de la clase Duenho
     *
     * @Class Duenho
     */
    public static void obtenerDatos(Duenho obj) {
        mostrarMensajes("Ingrese el nombre del dueño");
        String nombre = sc.nextLine();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del dueño");
        int telefono = sc.nextInt();
        sc.nextLine();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del dueño");
        String direccion = sc.nextLine();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del dueño (M o F)");
        char genero = sc.next().charAt(0);
        sc.nextLine();
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del dueño (sin dejar espacios)");
        String estado = sc.next();
        obj.setDireccion(estado);
    }

    /**
     * Este método se encarga de leer los atributos de la clase
     *
     * @Class Cliente
     */
    public static void obtenerDatos(Cliente obj) {
        mostrarMensajes("Ingrese el nombre del cliente");
        String nombre = sc.nextLine();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del cliente");
        int telefono = sc.nextInt();
        sc.nextLine();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del cliente");
        String direccion = sc.nextLine();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del cliente (M o F)");
        char genero = sc.next().charAt(0);
        sc.nextLine();
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del cliente (sin dejar espacios)");
        String estado = sc.next();
        obj.setDireccion(estado);
    }

    /**
     * Este método se encarga de leer los atributos de la clase Codeudor
     *
     * @Class Codeudor
     */
    public static void obtenerDatos(Codeudor obj) {
        mostrarMensajes("Ingrese el nombre del codeudor");
        String nombre = sc.nextLine();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del codeudor");
        int telefono = sc.nextInt();
        sc.nextLine();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del codeudor");
        String direccion = sc.nextLine();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del codeudor (M o F)");
        char genero = sc.next().charAt(0);
        sc.nextLine();
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del codeudor (sin dejar espacios)");
        String estado = sc.next();
        obj.setDireccion(estado);
    }

    /**
     * Este método se encarga de solicitar los datos y hacer una validación los mismos
     *
     * @Class Contrato
     */
    public static void obtenerDatos(Contrato obj) {
        mostrarMensajes("Ingrese nombre de interventor (sin dejar espacios)");
        String nombreInterventor = sc.next();
        obj.setNombreInterventor(nombreInterventor);

        boolean fechaInicioCorrecta = false;

        while (!fechaInicioCorrecta) {
            try {
                mostrarMensajes("Ingrese la fecha de inicio del contrato (Año/Mes/Dia)");
                String fechaInicioSTR = sc.next();

                Date fechaInicio = new SimpleDateFormat("yyyy/MM/dd").parse(fechaInicioSTR);
                obj.setFechaInicio(fechaInicio);

                fechaInicioCorrecta = true;
            } catch (ParseException ex) {
                mostrarMensajes("La fecha ingresada no es valida" + "\n");
            }
        }

        boolean fechaFinCorrecta = false;

        while (!fechaFinCorrecta) {

            try {
                mostrarMensajes("Ingrese la fecha de finalización del contrato (Año/Mes/Dia)");
                String fechaFinSTR = sc.next();

                Date fechaFin = new SimpleDateFormat("yyyy/MM/dd").parse(fechaFinSTR);
                obj.setFechaInicio(fechaFin);

                fechaFinCorrecta = true;
            } catch (ParseException ex) {
                mostrarMensajes("La fecha ingresada no es valida" + "\n");
            }
        }
    }

    /**
     * Este método se encarga de leer los atributos de la clase Local
     *
     * @Class Local
     */
    public static void obtenerDatos(Local obj) {
        mostrarMensajes("¿El local tiene techo? (True o False)");
        boolean techo = sc.nextBoolean();
        obj.setTecho(techo);

        mostrarMensajes("¿Tiene camara refrigerante? (True o False)");
        boolean camaraRefrigerante = sc.nextBoolean();
        obj.setCamaraRefrigerante(camaraRefrigerante);

        mostrarMensajes("Ingrese el tamaño del local (En M2)");
        int tamanho = sc.nextInt();
        obj.setTamanho(tamanho);

        mostrarMensajes("Ingrese el precio base dado por el dueño");
        int precioBase = sc.nextInt();
        obj.setPrecioBase(precioBase);
    }

    public static int menu() {
        mostrarMensajes(
                "Menu - Sistema de la plaza '" + Plaza.nombre + "'"
                        + "\n\n1) Agregar sector a la plaza"
                        + "\n2) Agregar local a un sector"
                        + "\n3) Registrar dueño"
                        + "\n4) Registrar cliente"
                        + "\n5) Registrar codeudor"
                        + "\n6) Alquilar local"
                        + "\n7) Mostrar locales desocupados"
                        + "\n8) Mostrar locales ocupados"
                        + "\n9) Mostrar historial de alquiler de un local"
                        + "\n10) Mostrar listado de contratos vigentes"
                        + "\n11) Opciones avanzadas"
                        + "\n12) Salir"
                        + "\n\nSeleccione una opcion"
        );

        return sc.nextInt();

    }

    public static void main(String[] args) {
        cargar();

        int opc = -1;

        while (opc != 12) {
            opc = menu();

            switch (opc) {
                case 1:
                    agregarSector();
                    guardar();
                    break;

                case 2:
                    agregarLocal();
                    guardar();
                    break;

                case 3:
                    registrarDuenho();
                    guardar();
                    break;

                case 4:
                    registrarCliente();
                    guardar();
                    break;

                case 5:
                    registrarCodeudor();
                    guardar();
                    break;

                case 6:
                    alquilarLocal();
                    break;

                case 7:
                    mostrarLocalesDesocupados();
                    break;

                case 8:
                    mostrarLocalesOcupados();
                    break;

                case 9:
                    mostrarHistorialAlquilerLocal();
                    break;

                case 10:
                    mostrarListadoContratosVigentes();
                    break;

                case 11:
                    opcionesAvanzadas();
                    break;

                case 12:
                    guardar();
                    mostrarMensajes("Saliendo del sistema...");
                    break;

                default:
                    mostrarMensajes("Ingrese una opción valida" + "\n");
                    break;
            }
        }
    }

    public static void guardar() {
        Serializador.serializarTodo();
    }

    public static void cargar() {
        Deserializador.deserializarTodo();
    }

    public static int menuOpcionesAvanzadas(){
        mostrarMensajes(
                "Bienvenido a las opciones avanzadas de '" + Plaza.nombre + "'"
                        + "\n\n1) Acceder al menu de automatizacion"
                        + "\n2) Mostrar datos guardados"
                        + "\n3) Salir de opciones avanzadas"
                        + "\n\nSeleccione una opcion"
        );
        return sc.nextInt();
    }
    public static void opcionesAvanzadas() {
        int opc2 = -1;

        while (opc2 != 3) {

            opc2 = menuOpcionesAvanzadas();

            switch (opc2) {
                case 1:
                    automatizacion();
                    break;

                case 2:
                    mostrar();
                    break;

                case 3:
                    guardar();
                    mostrarMensajes("Saliendo de opciones avanzadas..." + "\n");
                    break;

                default:
                    mostrarMensajes("Ingrese una opción valida" + "\n");
                    break;
            }
        }
    }

    public static int menuAutomatizacion() {
        mostrarMensajes(
                "Que datos deseas automatizar de '" + Plaza.nombre + "'"
                        + "\n\n1) Generar sector a la plaza"
                        + "\n2) Generar duenho aleatorio"
                        + "\n3) Generar codeudor aleatorio"
                        + "\n4) Generar local aleatorio"
                        + "\n5) Salir de automatizacion"
                        + "\n\nSeleccione una opcion"
        );

        return sc.nextInt();
    }

    public static int menuMostrar() {
        mostrarMensajes(
                "Que deseas ver de '" + Plaza.nombre + "'"
                        + "\n\n1) Mostrar sectores almacenados"
                        + "\n2) Mostrar duenhos almacenados"
                        + "\n3) Mostrar codeudores almacenados"
                        + "\n4) Mostrar locales almacenados"
                        + "\n5) Mostrar clientes almacenados"
                        + "\n6) Salir de menu de mostrar datos"
                        + "\n\nSeleccione una opcion"
        );

        return sc.nextInt();
    }

    public static void automatizacion() {
        Sector sector;
        Duenho duenho;
        Codeudor codeudor;
        Local local;

        int opc3 = -1;

        while (opc3 != 5) {

            opc3 = menuAutomatizacion();

            switch (opc3) {
                case 1:
                    sector = generarSectorAleatorio();
                    mostrarMensajes("Se genero satisfactoriamente el sector: " + (Sector.getSectores().size() - 1)
                            + "\n" + sector.retornarInformacionSinLocales() + "\n");
                    break;

                case 2:
                    duenho = generarDuenhoAleatorio();
                    mostrarMensajes("Se genero satisfactoriamente el duenho: " + (Duenho.getDuenhos().size() - 1)
                            + "\n" + duenho.retornarInformacionCorta() + "\n");
                    break;

                case 3:
                    codeudor = generarCodeudorAleatorio();
                    mostrarMensajes("Se genero satisfactoriamente el codeudor: " + (Codeudor.getCodeudores().size() - 1)
                            + "\n" + codeudor.retornarInformacionCorta() + "\n");
                    break;

                case 4:
                    local = generarLocalAleatorio();
                    mostrarMensajes("Se genero satisfactoriamente el local: " + (Local.getLocales().size() - 1)
                            + "\n" + local.retornarInformacion() + "\n");
                    break;

                case 5:
                    guardar();
                    mostrarMensajes("Saliendo del menu de automatizacion..." + "\n");
                    break;

                default:
                    mostrarMensajes("Ingrese una opcion valida" + "\n");
                    break;
            }
        }
    }

    public static void mostrar() {
        Sector sector;
        Duenho duenho;
        Codeudor codeudor;
        Local local;
        Cliente cliente;

        int opc4 = -1;

        while (opc4 != 6) {

            opc4 = menuMostrar();

            switch (opc4) {
                case 1:
                    if (Sector.getSectores().isEmpty()) {
                        mostrarMensajes("En este momento no hay sectores almacenados" + "\n");
                    }
                    else {
                        for (int i = 0; i < Sector.getSectores().size(); i++) {
                            sector = Sector.getSectores().get(i);
                            mostrarMensajes("Sector: " + i + "\n" + sector.retornarInformacionSinLocales() + "\n");
                        }
                    }
                    break;

                case 2:
                    if (Duenho.getDuenhos().isEmpty()){
                        mostrarMensajes("En este momento no hay duenhos almacenados" + "\n");
                    }
                    else {
                        for (int i = 0; i < Duenho.getDuenhos().size(); i++) {
                            duenho = Duenho.getDuenhos().get(i);
                            mostrarMensajes("Duenho: " + i + "\n" + duenho.retornarInformacion() + "\n");
                        }
                    }
                    break;

                case 3:
                    if (Codeudor.getCodeudores().isEmpty()){
                        mostrarMensajes("En este momento no hay codeudores almacenados" + "\n");
                    }
                    else {
                        for (int i = 0; i < Codeudor.getCodeudores().size(); i++) {
                            codeudor = Codeudor.getCodeudores().get(i);
                            mostrarMensajes("Codeudor: " + i + "\n" + codeudor.retornarInformacion() + "\n");
                        }
                    }
                    break;

                case 4:
                    if (Local.getLocales().isEmpty()){
                        mostrarMensajes("En este momento no hay locales almacenados" + "\n");
                    }
                    else {
                        for (int i = 0; i < Local.getLocales().size(); i++) {
                            local = Local.getLocales().get(i);
                            mostrarMensajes("Local: " + i + "\n" + local.retornarInformacion() + "\n");
                        }
                    }
                    break;

                case 5:
                    if (Cliente.getClientes().isEmpty()){
                        mostrarMensajes("En este momento no hay clientes almacenados" + "\n");
                    }
                    for (int i = 0; i < Cliente.getClientes().size(); i++){
                        cliente = Cliente.getClientes().get(i);
                        mostrarMensajes("Cliente: " + i + "\n" + cliente.retornarInformacion() + "\n");
                    }
                    break;

                case 6:
                    guardar();
                    mostrarMensajes("Saliendo del menu de mostrar..." + "\n");
                    break;

                default:
                    mostrarMensajes("Ingrese una opcion valida" + "\n");
                    break;
            }
        }
    }
}
package uMain;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.Cliente;
import gestorAplicacion.Codeudor;
import gestorAplicacion.Contrato;
import gestorAplicacion.Duenho;
import gestorAplicacion.Local;
import gestorAplicacion.Plaza;
import gestorAplicacion.Sector;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;



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

            mostrarMensajes("Ingrese la cédula del dueño\n\n" + sector.mostrarDuenhos(duenhos));

            cedulaDuenho = sc.nextInt();

            cedulaExistente = sector.buscarDuenho(duenhos, cedulaDuenho);

            if (!cedulaExistente)
                mostrarMensajes("Ingrese una cédula valida");

        }

        nuevoLocal.setCedulaDuenho(cedulaDuenho);

        sector.getLocales().add(nuevoLocal);

        mostrarMensajes("Local agregado correctamente al sector");

    }

    /**
     * Este metodo se encarga de crear un sector nuevo y agregarlo a la lista
     */
    public static void agregarSector() {
        Sector nuevoSector = new Sector();
        obtenerDatos(nuevoSector);
        nuevoSector.setCodigo(plaza.getSectores().size() + 1);

        plaza.getSectores().add(nuevoSector);

        mostrarMensajes("Sector agregado correctamente a la plaza");
    }

    /**
     * Este metodo se encarga de crear un local nuevo dentro de un sector dado y agregarlo
     * a la lista de locales dentro de dicho sector
     */
    public static void agregarLocal() {
        if (plaza.getSectores().size() == 0) {
            mostrarMensajes("Antes de agregar locales debe agregar sectores a la plaza");
        }
        else if (plaza.getDuenhos().size() == 0)
            mostrarMensajes("Antes de agregar locales debe agregar dueños");
        else {
            int codigoSector = -1;

            int indiceSector = -1;

            while (indiceSector == -1) {

                mostrarMensajes("Ingrese el codigo del sector donde sera agregado el local\n\n" + plaza.mostrarSectores());

                codigoSector = sc.nextInt();

                indiceSector = plaza.buscarSector(codigoSector);

                if (indiceSector == -1)

                    mostrarMensajes("Ingrese un codigo de sector valido");

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

            if (plaza.buscarDuenho(cedulaDuenho) != -1){
                mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
            }

            else {
                cedulaExistente = false;
            }
        }

        Duenho nuevoDuenho = new Duenho();
        nuevoDuenho.setCedula(cedulaDuenho);
        obtenerDatos(nuevoDuenho);

        plaza.getDuenhos().add(nuevoDuenho);

        mostrarMensajes("Dueño registrado correctamente");
    }

    /**
     * Este método se encarga de crear un cliente nuevo y agregarlo a la lista de clientes
     */
    public static void registrarCliente() {
        if (plaza.getCodeudores().size() == 0 || plaza.getCodeudores().size() == 1)
            mostrarMensajes("Para registrar un cliente deben existir por lo menos 2 codeudores");

        else {

            int cedulaCliente = -1;

            boolean cedulaExistente = true;

            while (cedulaExistente) {

                mostrarMensajes("Ingrese la cedula del cliente");

                cedulaCliente = sc.nextInt();

                if (plaza.buscarCliente(cedulaCliente) != -1){
                    mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
                }

                else {
                    cedulaExistente = false;
                }
            }

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setCedula(cedulaCliente);
            obtenerDatos(nuevoCliente);

            int cedulaCodeudor1 = plaza.getCodeudores().get(seleccionarCodeudor(1)).getCedula();
            int cedulaCodeudor2;

            do {

                cedulaCodeudor2 = plaza.getCodeudores().get(seleccionarCodeudor(2)).getCedula();

                if (cedulaCodeudor1 == cedulaCodeudor2)
                    mostrarMensajes("Los codeudores deben ser distintos");

            } while (cedulaCodeudor1 == cedulaCodeudor2);

            plaza.getClientes().add(nuevoCliente);

            mostrarMensajes("Cliente registrado correctamente");

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

            if (plaza.buscarCodeudor(cedulaCodeudor) != -1){
                mostrarMensajes("Esta cedula ya existe, por favor ingrese una distinta");
            }

            else {
                cedulaExistente = false;
            }

        }

        Codeudor nuevoCodeudor = new Codeudor();
        nuevoCodeudor.setCedula(cedulaCodeudor);
        obtenerDatos(nuevoCodeudor);

        plaza.getCodeudores().add(nuevoCodeudor);

        mostrarMensajes("Codeudor registrado correctamente");
    }

    /**
     * Este método se encarga elegir un local y un cliente, para proceder a alquilar un local
     * y luego de esto se genera un contrato y es agregado a la lista de contratos
     */
    public static void alquilarLocal() {
        if (plaza.getClientes().size() == 0)
            mostrarMensajes("Para alquilar un local primero debe registrar clientes");

        else if (plaza.getSectores().size() == 0)
            mostrarMensajes("Para alquilar un local primero debe agregar sectores");

        else if (plaza.obtenerCantidadLocales() == 0)
            mostrarMensajes("Para alquilar un local primero debe agregar locales");

        else {

            int cedulaCliente = plaza.getClientes().get(seleccionarCliente()).getCedula();

            int[] indicesLocal = seleccionarLocal();
            plaza.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]).setOcupado(true);
            Local local = plaza.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]);

            int codigoLocal = local.getCodigo();
            int montoMensual = local.getPrecioBase() + (plaza.getSectores().get(indicesLocal[0]).getPrecioBaseM2() * local.getTamanho());
            montoMensual *= (local.getTecho()) ? Plaza.porcentajeAumentoConTecho : 1;
            montoMensual *= (local.getCamaraRefrigerante()) ? Plaza.porcentajeAumentoConCamaraRefrigerante : 1;
            montoMensual *= 1.2;

            Contrato contrato = new Contrato();
            contrato.setNumero(plaza.getContratos().size() + 1);
            contrato.setCedulaCliente(cedulaCliente);
            contrato.setCodigoLocal(codigoLocal);
            contrato.setMontoMensual(montoMensual);
            obtenerDatos(contrato);

            plaza.getContratos().add(contrato);

            mostrarMensajes("Alquiler completado correctamente");

        }
    }

    /**
     * Este método lee una cédula y la busca, retornando el índice del cliente
     * @return El índice del cliente si lo encontró o -1 si no
     */
    public static int seleccionarCliente() {
        int indice = -1;

        do {

            mostrarMensajes("Ingrese la cedula del cliente\n\n" + plaza.mostrarClientes());

            indice = plaza.buscarCliente(sc.nextInt());

            if (indice == -1)
                mostrarMensajes("No existe ningun cliente registrado con esta cedula");

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

            mostrarMensajes("Ingrese la cédula del codeudor #" + n + "\n\n" + plaza.mostrarCodeudores());

            indice = plaza.buscarCodeudor(sc.nextInt());

            if (indice == -1)
                mostrarMensajes("No existe ningún codeudor registrado con esta cédula");

        } while (indice == -1);

        return indice;

    }

    /**
     * Este método muestra un listado de los locales desocupados en el momento
     */
    public static void mostrarLocalesDesocupados() {
        String listado = "Listado de los locales desocupados\n\n";

        for (Sector sector : plaza.getSectores())
            for (Local local : sector.getLocales())
                if (!local.isOcupado())
                    listado += local.retornarInformacion() + "\n";
                else{
                    mostrarMensajes("No hay locales desocupados");
                }
        mostrarMensajes(listado);
    }

    /**
     * Este método muestra un listado de los locales ocupados en el momento
     */
    public static void mostrarLocalesOcupados() {
        String listado = "Listado de los locales ocupados\n\n";

        for (Sector sector : plaza.getSectores())
            for (Local local : sector.getLocales())
                if (local.isOcupado())
                    listado += local.retornarInformacion() + "\n";
                else{
                    mostrarMensajes("No hay locales desocupados");
                }
        mostrarMensajes(listado);
    }

    /**
     * Este método muestra el historial de alquiler de un local elegido
     */
    public static void mostrarHistorialAlquilerLocal() {
        if (plaza.obtenerCantidadLocales() == 0)
            mostrarMensajes("No hay locales actualmente");

        else {

            int[] indicesLocal = seleccionarLocal();
            Local local = plaza.getSectores().get(indicesLocal[0]).getLocales().get(indicesLocal[1]);

            String informacion = "Historial de alquiler del local con codigo: " + local.getCodigo() + "\n\n";
            int totalDineroAdministracion = 0;

            for (Contrato contrato : plaza.getContratos()) {

                informacion += contrato.retornarInformacion() + "\n";
                totalDineroAdministracion += contrato.getMontoMensual();

            }

            informacion += "\nTotal de dinero recaudado perteneciente a admnistracion: $" + totalDineroAdministracion;

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

            for (Contrato contrato : plaza.getContratos())
                if (fechaBusqueda.after(contrato.getFechaInicio()) && fechaBusqueda.before(contrato.getFechaFin()))
                    informacion += contrato.retornarInformacion() + "\n";

            mostrarMensajes(informacion);

        }
        catch (ParseException ex) {
            mostrarMensajes("La fecha ingresada no es valida");
        }

    }

    /**
     * Este método se encarga de leer el código de un local y retornar el índice de este
     * @return El índice del sector y del local respectivamente si se encuentra o {-1, -1} si no se encuentra
     */
    public static int[] seleccionarLocal() {
        int[] indices = {-1, -1}; // Retorna el índice del sector y del local respectivamente

        do {

            mostrarMensajes("Ingrese el codigo del local\n\n" + plaza.mostrarLocales());

            indices = plaza.buscarLocal(sc.nextInt());

            if (indices[0] == - 1 || indices[1] == -1)
                mostrarMensajes("No se encontro ningun local con el codigo seleccionado");

        } while (indices[0] == - 1 || indices[1] == -1);

        return indices;
    }

    /**
     * Este método se encarga de leer los atributos de la clase Sector
     *
     * @Class Sector
     */
    public static void obtenerDatos(Sector obj) {
        mostrarMensajes("Ingrese el nombre del sector");
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
        String nombre = sc.next();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del dueño");
        int telefono = sc.nextInt();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del dueño");
        String direccion = sc.next();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del dueño");
        char genero = sc.next().charAt(0);
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del dueño");
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
        String nombre = sc.next();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del cliente");
        int telefono = sc.nextInt();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del cliente");
        String direccion = sc.next();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del cliente");
        char genero = sc.next().charAt(0);
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del cliente");
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
        String nombre = sc.next();
        obj.setNombre(nombre);

        mostrarMensajes("Ingrese el telefono del codeudor");
        int telefono = sc.nextInt();
        obj.setTelefono(telefono);

        mostrarMensajes("Ingrese la dirección del codeudor");
        String direccion = sc.next();
        obj.setDireccion(direccion);

        mostrarMensajes("Ingrese el genero del codeudor");
        char genero = sc.next().charAt(0);
        obj.setGenero(genero);

        mostrarMensajes("Ingrese el estado civil del codeudor");
        String estado = sc.next();
        obj.setDireccion(estado);
    }

    /**
     * Este método se encarga de solicitar los datos y hacer una validación los mismos
     *
     * @Class Contrato
     */
    public static void obtenerDatos(Contrato obj) {
        mostrarMensajes("Ingrese nombre de interventor");
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
                mostrarMensajes("La fecha ingresada no es valida");
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
                mostrarMensajes("La fecha ingresada no es valida");
            }
        }
    }

    /**
     * Este método se encarga de leer los atributos de la clase Local
     *
     * @Class Local
     */
    public static void obtenerDatos(Local obj) {
        mostrarMensajes("¿El local tiene techo?");
        boolean techo = sc.nextBoolean();
        obj.setTecho(techo);

        mostrarMensajes("¿Tiene camara refrigerante?");
        boolean camaraRefrigerante = sc.nextBoolean();
        obj.setCamaraRefrigerante(camaraRefrigerante);

        mostrarMensajes("Ingrese el tamaño del local (En M2)");
        int tamanho = sc.nextInt();
        obj.setTamanho(tamanho);

        mostrarMensajes("Ingrese el precio base dado por el dueño");
        int precioBase = sc.nextInt();
        obj.setPrecioBase(precioBase);
    }

    public int menu() {
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

        Plaza plaza = new Plaza();
        Main main = new Main();

        int opc = -1;

        while (opc != 12) {
            opc = main.menu();

            switch (opc) {
                case 1:
                    agregarSector();
                    break;

                case 2:
                    agregarLocal();
                    break;

                case 3:
                    registrarDuenho();
                    break;

                case 4:
                    registrarCliente();
                    break;

                case 5:
                    registrarCodeudor();
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
                    mostrarMensajes("Ingrese una opción valida");
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

    public static Sector generarSectorAleatorio() {
        Random random = new Random();

        int[] codigoSector = {99, 98, 98};

        String[] nombreSector = {"Minorista", "Mayorista", "Placita", "Tunjuelito"};

        int[] precioBaseSector = {10000000, 15000000, 20000000, 25000000};

        Local[] locales = {
                new Local(99, false, false, 500, 500000, 4378, false),
                new Local(98, true, false, 800, 800000, 8435, false),
                new Local(97, true, true, 400, 450000, 9647, false),
                new Local(96, false, false, 500, 500000, 4378, false),
                new Local(95, false, true, 480, 520000, 3574, false),
        };

        ArrayList<Local> sectorLocales = new ArrayList<Local>();
        sectorLocales.add(locales[random.nextInt(locales.length)]);

        return new Sector(codigoSector[random.nextInt(codigoSector.length)], nombreSector[random.nextInt(nombreSector.length)],
                precioBaseSector[random.nextInt(precioBaseSector.length)], sectorLocales);

    }

    public static Codeudor generarCodeudorAleatorio() {
        Random random = new Random();

        int[] cedulaCodeudor = {1001, 1000, 1002, 1003, 1004, 1010, 1100};

        String[] nombreCodeudor = {"Daniel Puentes", "Fernanda Arango", "Thomas Rueda", "Andres Soto",
                "Angela Patiño", "Mary Bayona", "Brandon Salinas"};

        int[] telefonoCodeudor = {2147000, 2054028, 6104548, 3927814, 5054028, 4440123, 2406038, 3054967};

        String[] direccionCodeudor = {"Cll 63", "Cra 45", "Cll 65", "Cra 80", "Dg 98", "Cll 10", "Cra 70"};

        char[] generoCodeudor = {'M', 'F' };

        String[] estadoCivilCodeudor = {"Soltero", "Casado", "Union Libre"};

        return new Codeudor(cedulaCodeudor[random.nextInt(cedulaCodeudor.length)], nombreCodeudor[random.nextInt(nombreCodeudor.length)],
                telefonoCodeudor[random.nextInt(telefonoCodeudor.length)], direccionCodeudor[random.nextInt(direccionCodeudor.length)],
                generoCodeudor[random.nextInt(generoCodeudor.length)], estadoCivilCodeudor[random.nextInt(estadoCivilCodeudor.length)]);
    }

        public static int menuOpcionesAvanzadas(){
            mostrarMensajes(
                    "Bienvenido a las opciones avanzadas de '" + Plaza.nombre + "'"
                            + "\n\n1) Generar sector a la plaza"
                            + "\n2) Salir de opciones avanzadas"
                            + "\n\nSeleccione una opcion"
            );

            return sc.nextInt();
        }
        public static void opcionesAvanzadas(){
            Sector sector;

            int opc1 = -1;

            while (opc1 != 2) {

                opc1 = menuOpcionesAvanzadas();

                switch (opc1) {
                    case 1:
                        sector = generarSectorAleatorio();
                        mostrarMensajes("Se genero satisfactoriamente el sector:" + (Sector.getSectores().size() - 1
                        + sector.toString())
                    + sector);
                        break;

                    case 2:
                        mostrarMensajes("Saliendo de opciones avanzadas...");
                        break;

                    default:
                        mostrarMensajes("Ingrese una opción valida");
                        break;
                }
            }
        }
}

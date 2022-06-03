package uMain;

import gestorAplicacion.clientes.Codeudor;
import gestorAplicacion.clientes.Duenho;
import gestorAplicacion.infraestructura.Local;
import gestorAplicacion.infraestructura.Sector;

import java.util.ArrayList;
import java.util.Random;

public class Automatizacion {
    public static Local generarLocalAleatorio() {
        Random random = new Random();

        int[] codigoLocal = {101, 102, 103, 104, 105, 106, 107, 108, 109};

        boolean[] tcoLocal = {true, false};

        int[] tamanhoLocal = {50, 51, 52, 53, 54, 55, 56, 57, 58, 59};

        int[] precioBaseLocal = {10000, 13000, 15000, 17000, 20000, 23000, 25000,
                27000, 30000};

        int[] cedulaLocal = {5000, 5001, 5002, 5003, 5004};

        return new Local(codigoLocal[random.nextInt(codigoLocal.length)], tcoLocal[random.nextInt(tcoLocal.length)],
                tcoLocal[random.nextInt(tcoLocal.length)], tamanhoLocal[random.nextInt(tamanhoLocal.length)],
                precioBaseLocal[random.nextInt(precioBaseLocal.length)], cedulaLocal[random.nextInt(cedulaLocal.length)],
                tcoLocal[random.nextInt(tcoLocal.length)]);

    }

    public static Sector generarSectorAleatorio() {
        Random random = new Random();

        int[] codigoSector = {99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85};

        String[] nombreSector = {"Minorista", "Mayorista", "Placita", "Tunjuelito", "Galeria", "La Alameda"};

        int[] precioBaseSector = {10000, 13000, 15000, 17000, 20000, 23000, 25000,
                27000, 30000};

        Local[] locales = {generarLocalAleatorio()};

        ArrayList<Local> sectorLocales = new ArrayList<Local>();
        sectorLocales.add(locales[random.nextInt(locales.length)]);

        /*Local[] locales = {
                new Local(99, false, false, 500, 500000, 4378, false),
                new Local(98, true, false, 800, 800000, 8435, false),
                new Local(97, true, true, 400, 450000, 9647, false),
                new Local(96, false, false, 500, 500000, 4378, false),
                new Local(95, false, true, 480, 520000, 3574, false),
        };

        ArrayList<Local> sectorLocales = new ArrayList<Local>();
        sectorLocales.add(locales[random.nextInt(locales.length)]);*/

        return new Sector(codigoSector[random.nextInt(codigoSector.length)], nombreSector[random.nextInt(nombreSector.length)],
                precioBaseSector[random.nextInt(precioBaseSector.length)], sectorLocales);

    }

    public static Duenho generarDuenhoAleatorio() {
        Random random = new Random();

        int[] cedulaDuenho = {1001, 1000, 1002, 1003, 1004, 1010, 1100};

        String[] nombreDuenho = {"Daniel Puentes", "Fernanda Arango", "Thomas Rueda", "Andres Soto",
                "Angela Patiño", "Mary Bayona", "Brandon Salinas"};

        int[] telefonoDuenho = {2147000, 2054028, 6104548, 3927814, 5054028, 4440123, 2406038, 3054967};

        String[] direccionDuenho = {"Cll 63", "Cra 45", "Cll 65", "Cra 80", "Dg 98", "Cll 10", "Cra 70"};

        char[] generoDuenho = {'M', 'F'};

        String[] estadoCivilDuenho = {"Soltero", "Casado", "Union Libre"};

        return new Duenho(cedulaDuenho[random.nextInt(cedulaDuenho.length)], nombreDuenho[random.nextInt(nombreDuenho.length)],
                telefonoDuenho[random.nextInt(telefonoDuenho.length)], direccionDuenho[random.nextInt(direccionDuenho.length)],
                generoDuenho[random.nextInt(generoDuenho.length)], estadoCivilDuenho[random.nextInt(estadoCivilDuenho.length)]);
    }

    public static Codeudor generarCodeudorAleatorio() {
        Random random = new Random();

        int[] cedulaCodeudor = {2001, 2000, 2002, 2003, 2004, 2010, 2100};

        String[] nombreCodeudor = {"Felipe Rocha", "Antonia Garcia", "Linda Perez", "Mario Guerra",
                "Juan Gonzalez", "Jeronimo Ochoa", "Victor Orozco"};

        int[] telefonoCodeudor = {5475233, 7845655, 7845112, 2561948, 417651, 9846511, 2506590, 5616518};

        String[] direccionCodeudor = {"Cll 36", "Cra 54", "Cll 56", "Cra 8", "Dg 89", "Cll 1", "Cra 7"};

        char[] generoCodeudor = {'M', 'F'};

        String[] estadoCivilCodeudor = {"Soltero", "Casado", "Union Libre"};

        return new Codeudor(cedulaCodeudor[random.nextInt(cedulaCodeudor.length)], nombreCodeudor[random.nextInt(nombreCodeudor.length)],
                telefonoCodeudor[random.nextInt(telefonoCodeudor.length)], direccionCodeudor[random.nextInt(direccionCodeudor.length)],
                generoCodeudor[random.nextInt(generoCodeudor.length)], estadoCivilCodeudor[random.nextInt(estadoCivilCodeudor.length)]);
    }

}

from excepciones.excepcionPresenciaDatos import ExcepcionPresenciaDatos
from excepciones.excepcionTipoFloat import ExcepcionTipoFloat
from excepciones.excepcionTipoInt import ExcepcionTipoInt
from excepciones.excepcionTipoString import ExcepcionTipoString
from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.plaza import Plaza
from gestorAplicacion.infraestructura.sector import Sector
import datetime
class ObtenerDatos():

    def __init__(self):
        pass

    def obtenerDatosSector(self, obj):
        print("Ingrese el nombre del sector (sin dejar espacios)")
        nombreSector = input()
        obj.setNombre(nombreSector)

        print("Ingrese el precio base por m2 del sector")
        precioBase = float(input())
        obj.setPrecioBaseM2(precioBase)
        criterios=["nombre del sector", "precio base por m2"]
        valores= [nombreSector, precioBase]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return

        try:
            ExcepcionTipoFloat.tipoFloat(["precio base por m2"], [precioBase])
        except ExcepcionTipoFloat:
            return

        try:
            ExcepcionTipoString.tipoString(["nombre del sector"], [nombreSector])
        except ExcepcionTipoString:
            return


    def obtenerDatosDuenho(self, obj):
        nombreDuenho = input("Ingrese el nombre del duenho\n")
        obj.setNombre(nombreDuenho)
        telefonoDuenho = int(input("Ingrese el telefono del duenho\n"))
        obj.setTelefono(telefonoDuenho)
        direccionDuenho = input("Ingrese la direccion del duenho\n")
        obj.setDireccion(direccionDuenho)
        generoDuenho = input("Ingrese el genero del duenho (M ó F)\n")
        obj.setGenero(generoDuenho)
        estadoCivilDuenho = input("Ingrese el estado civil del duenho (sin dejar espacios)\n")
        obj.setEstadoCivil(estadoCivilDuenho)
        criterios=["nombre del duenho", "telefono del duenho", "direccion del duenho", "genero del duenho", "estado civil del duenho"]
        valores= [nombreDuenho, telefonoDuenho, direccionDuenho, generoDuenho, estadoCivilDuenho]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return
        try:
            ExcepcionTipoString.tipoString(["nombre del duenho", "genero del duenho", "estado civil del duenho"], [nombreDuenho, generoDuenho, estadoCivilDuenho])
        except ExcepcionTipoString:
            return
        try:
            ExcepcionTipoInt.tipoInt(["telefono del duenho"], [telefonoDuenho])
        except ExcepcionTipoInt:
            return

    def obtenerDatosCliente(self, obj):
        nombreCliente = input("Ingrese el nombre del cliente")
        obj.setNombre(nombreCliente)
        telefonoCliente = input("Ingrese el telefono del cliente")
        obj.setTelefono(telefonoCliente)
        direccionCliente = input("Ingrese la direccion del cliente")
        obj.setDireccion(direccionCliente)
        generoCliente = input("Ingrese el genero del cliente (M ó F)")
        obj.setGenero(generoCliente)
        estadoCivilCliente = input("Ingrese el estado civil del cliente (sin dejar espacios)")
        obj.setEstadoCivil(estadoCivilCliente)
        criterios=["nombre del cliente", "telefono del cliente", "direccion del cliente", "genero del cliente", "estado civil del cliente"]
        valores= [nombreCliente, telefonoCliente, direccionCliente, generoCliente, estadoCivilCliente]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return

        try:
            ExcepcionTipoString.tipoString(["nombre del cliente", "genero del cliente", "estado civil del cliente"], [nombreCliente, generoCliente, estadoCivilCliente])
        except ExcepcionTipoString:
            return
        try:
            ExcepcionTipoInt.tipoInt(["telefono del cliente"], [telefonoCliente])
        except ExcepcionTipoInt:
            return

    def obtenerDatosCodeudor(self, obj):
        nombreCodeudor = input("Ingrese el nombre del codeudor ")
        obj.setNombre(nombreCodeudor)
        telefonoCodeudor = input("Ingrese el telefono del codeudor ")
        obj.setTelefono(telefonoCodeudor)
        direccionCodeudor = input("Ingrese la direccion del codeudor ")
        obj.setDireccion(direccionCodeudor)
        generoCodeudor = input("Ingrese el genero del codeudor (M ó F) ")
        obj.setGenero(generoCodeudor)
        estadoCivilCodeudor = input("Ingrese el estado civil del codeudor (sin dejar espacios) ")
        obj.setEstadoCivil(estadoCivilCodeudor)
        criterios=["nombre del codeudor", "telefono del codeudor", "direccion del codeudor", "genero del codeudor", "estado civil del codeudor"]
        valores= [nombreCodeudor, telefonoCodeudor, direccionCodeudor, generoCodeudor, estadoCivilCodeudor]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return
        try:
            ExcepcionTipoString.tipoString(["nombre del codeudor", "genero del codeudor", "estado civil del codeudor"], [nombreCodeudor, generoCodeudor, estadoCivilCodeudor])
        except ExcepcionTipoString:
            return
        try:
            ExcepcionTipoInt.tipoInt(["telefono del codeudor"], [telefonoCodeudor])
        except ExcepcionTipoInt:
            return

    def obtenerDatosContrato(self, obj):
        nombreInterventor= input("Ingrese nombre de interventor (sin dejar espacios)")
        obj.setNombreInterventor(nombreInterventor)
        fechaInicioCorrecta = False
        while not fechaInicioCorrecta:
            try:
                fechaInicioSTR = input("Ingrese la fecha de inicio del contrato (Año/Mes/Dia)")
                fechaInicio = datetime.datetime.strptime(fechaInicioSTR, "%Y/%m/%d")
                obj.setFechaInicio(fechaInicio)
                fechaInicioCorrecta = True
            except ValueError:
                print("La fecha ingresada no es valida")
        fechaFinCorrecta = False
        while not fechaFinCorrecta:
            try:
                fechaFinSTR = input("Ingrese la fecha de fin del contrato (Año/Mes/Dia)")
                fechaFin = datetime.datetime.strptime(fechaFinSTR, "%Y/%m/%d")
                obj.setFechaFin(fechaFin)
                fechaFinCorrecta = True
            except ValueError:
                print("La fecha ingresada no es valida")

    def obtenerDatosLocal(self, obj):
        techoStr= input("¿El local tiene techo? (Si ó No) ").capitalize()
        try:
            ExcepcionPresenciaDatos(["techo"], [techoStr])
        except ExcepcionPresenciaDatos:
            techoStr= "No" # Si no se ingresa nada, se asume que no tiene techo
        if techoStr == "Si":
            techo = True
        else:
            techo = False
        obj.setTecho(techo)
        camaraRefrigeranteStr= input("¿El local tiene piso? (Si ó No) ").capitalize()
        try:
            ExcepcionPresenciaDatos(["piso"], [camaraRefrigeranteStr])
        except ExcepcionPresenciaDatos:
            camaraRefrigeranteStr = "No" # Si no se ingresa nada, se asume que no tiene piso
        if camaraRefrigeranteStr == "Si":
            camaraRefrigerante = True
        else:
            camaraRefrigerante = False
        obj.setCamaraRefrigerante(camaraRefrigerante)

        tamanho= int(input("Ingrese el tamanho del local (en metros cuadrados) "))
        try:
            ExcepcionTipoInt(["tamanho"], [tamanho])
        except ExcepcionTipoInt:
            return
        obj.setTamanho(tamanho)
        precioBase= float(input("Ingrese el precio base del local"))
        try:
            ExcepcionTipoFloat(["precio base"], [precioBase])
        except ExcepcionTipoFloat:
            return
        obj.setPrecioBase(precioBase)


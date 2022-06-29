from gestorAplicacion.infraestructura.local import Local


class Sector():
    sectores = []
    locales = []
    duenhos = []
    def __init__(self, codigo=0, nombre="", precioBaseM2=0, locales=[]):
        self._codigo = codigo
        self._nombre = nombre
        self._precioBaseM2 = precioBaseM2
        self._locales = locales
        Sector.sectores.append(self)

    def retornarInformacionLocales(self):
        informacion = ""
        for local in Local.getLocales():
            informacion += local.retornarInformacion() + "\n"
        return informacion

    def buscarDuenho(self, duenhos, cedula):
        for duenho in duenhos:
            if duenho.getCedula() == cedula:
                return True
        return False

    def mostrarDuenhos(self, duenhos):
        informacion = ""
        for duenho in duenhos:
            informacion += duenho.retornarInformacionCorta() + "\n"
        return informacion

    def retornarInformacionSinLocales(self):
        return "Codigo: " + str(self._codigo) + "\nNombre: " + str(self._nombre) + "\nPrecio base por M2: $" + str(self._precioBaseM2)

    def retornarInformacion(self):
        return ("Codigo: " + str(self._codigo) + "\nNombre: " + str(self._nombre) + "\nPrecio base por M2: $" + str(self._precioBaseM2) + "\n\nInformacion de los locales: " + str(self.retornarInformacionLocales()))

    def getLocalesS(self):
        return self._locales

    def setLocalesS(self, local):
        self._locales.append(local)

    def getCodigo(self):
        return self._codigo

    def setCodigo(self, codigo):
        self._codigo = codigo

    def getNombre(self):
        return self._nombre

    def setNombre(self, nombre):
        self._nombre = nombre

    def getPrecioBaseM2(self):
        return self._precioBaseM2

    def setPrecioBaseM2(self, precioBaseM2):
        self._precioBaseM2 = precioBaseM2

    @classmethod
    def getSectores(self):
        return self.sectores

    @classmethod
    def setSectores(self, sectores):
        self.sectores = sectores
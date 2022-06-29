from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.sector import Sector

class Plaza():
    PORCENTAJE_AUMENTO_CON_TECHO = 1.15 # 15%
    PORCENTAJE_AUMENTO_CON_CAMARA_REFRIGETANTE = 1.15 # 15%
    nombre = "CENTRAL LA NACHO"
    _sectores = []
    _duenhos = []
    _clientes = []
    _codeudores = []
    _contratos = []

    def buscarLocales(self, codigoLocal):
        for i in range(len(self._sectores)):
            for j in range(len(self._sectores[i].getLocalesS())):
                if self._sectores[i].getLocalesS()[j].getCodigo() == codigoLocal:
                    return self._sectores[i].getLocalesS()[j]
        for i in range(len(Sector.getSectores())):
            for j in range(len(Sector.getSectores()[i].getLocalesS())):
                if Sector.getSectores()[i].getLocalesS()[j].getCodigo() == codigoLocal:
                    return [i, j]
        return [-1, -1]

    def obtenerCantidadLocales(self):
        nLocales = 0
        for sector in self.getSectores():
            nLocales += len(sector.getLocalesS())
        return nLocales + 1

    def mostrarSectores(self):
        informacion = ""
        for sector in Sector.getSectores():
            informacion += sector.retornarInformacionSinLocales() + "\n"
        return informacion

    def mostrarLocales(self):
        informacion = ""
        for sector in Sector.getSectores():
            informacion += sector.retornarInformacionLocales() + "\n"
        return informacion

    def mostrarCodeudores(self):
        informacion = ""
        for codeudor in Codeudor.getCodeudores():
            informacion += codeudor.retornarInformacionCorta() + "\n"
        return informacion

    def mostrarClientes(self):
        informacion = ""
        for cliente in Cliente.getClientes():
            informacion += cliente.retornarInformacionCorta() + "\n"
        return informacion

    def buscarSector(self, codigo):
        a = len(Sector.getSectores())
        b = -1
        for i in range(a):
            if Sector.getSectores()[i].getCodigo() == codigo:
                b = i
        return b

    def buscarDuenho(self, cedula):
        a = len(Duenho.getDuenhos())
        b = -1
        for i in range(a):
            if Duenho.getDuenhos()[i].getCedula() == cedula:
                b = i
        return b

    def buscarCliente(self, cedula):
        a = len(Cliente.getClientes())
        b = -1
        for i in range(a):
            if Cliente.getClientes()[i].getCedula() == cedula:
                b = i
        return b

    def buscarCodeudor(self, cedula):
        a = len(Codeudor.getCodeudores())
        b = -1
        for i in range(a):
            if Codeudor.getCodeudores()[i].getCedula() == cedula:
                b= i
        return b

    def getCodigoSectores(self):
        listado= len(self._sectores)
        return listado

    def getSectores(self):
        return self._sectores

    def setSectores(self, sectores):
        self._sectores = sectores

    def getDuenhos(self):
        return self._duenhos

    def setDuenhos(self, duenhos):
        self._duenhos = duenhos

    def getClientes(self):
        return self._clientes

    def setCliente(self, clientes):
        self._clientes = clientes

    def getCodeudores(self):
        return self._codeudores

    def setCodeudores(self, codeudores):
        self._codeudores.append(codeudores)

    def getContratos(self):
        return self._contratos

    def setContratos(self, contratos):
        self._contratos = contratos
class Contrato():
    contratos = []

    def __init__(self, numero=0, fechaInicio=0, fechaInicioStr="", fechaFinStr="", fechaFin=0, montoMensual=0, nombreInterventor=0, cedulaCLiente=0, codigoLocal=0):
        self._numero = numero
        self._fechaInicio = fechaInicio
        self._fechaInicio = fechaInicio
        self._fechaFinStr = fechaFinStr
        self._fechaFin = fechaFin
        self._montoMensual = montoMensual
        self._nombreInterventor = nombreInterventor
        self._cedulaCLiente = cedulaCLiente
        self._codigoLocal = codigoLocal
        Contrato.contratos.append(self)

    def retornarInformacion(self):
        return "Numero de contrato: " + self._numero + "\nFecha de inicio: " + self._fechaInicio +\
               "\nFecha de finalizacion: " + self._fechaFin + "\nMonto mensual: " + self._montoMensual +\
               "\nNombre del interventor: " + self._nombreInterventor + "\nCedula del cliente: " + self._cedulaCLiente +\
               "\nCodigo del local: "+self._codigoLocal

    def getNumero(self):
        return self._numero

    def setNumero(self, numero):
        self._numero = numero

    def getFechaInicioStr(self):
        return self._fechaInicioStr
    def setFechaInicioStr(self, fechaInicioStr):
        self._fechaInicioStr = fechaInicioStr

    def getFechaInicio(self):
        return self._fechaInicio

    def setFechaInicio(self, fechaInicio):
        self._fechaInicio = fechaInicio

    def getFechaFinStr(self):
        return self._fechaFinStr
    def setFechaFinStr(self, fechaFinStr):
        self._fechaFinStr = fechaFinStr

    def getFechaFin(self):
        return self._fechaFin

    def setFechaFin(self, fechaFin):
        self._fechaFin = fechaFin

    def getMontoMensual(self):
        return self._montoMensual

    def setMontoMensual(self, monto):
        self._montoMensual = monto

    def getNombreIterventor(self):
        return self._nombreInterventor

    def setNombreIterventor(self, nombre):
        self._nombreInterventor = nombre

    def getCedulaCliente(self):
        return self._cedulaCliente

    def setCedulaCliente(self, cedulaCliente):
        self._cedulaCliente = cedulaCliente

    def getCodigoLocal(self):
        return self.codigoLocal

    def setCodigoLocal(self, codigoLocal):
        self._codigoLocal = codigoLocal

    @classmethod
    def getContratos(cls):
        return cls.contratos

    @classmethod
    def setContratos(cls, contratos):
        cls.contratos = contratos
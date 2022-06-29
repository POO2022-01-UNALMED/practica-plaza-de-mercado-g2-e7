from gestorAplicacion.clientes.persona import Persona


class Cliente(Persona):
    clientes = []

    def __init__(self, cedula=0, nombre="", telefono=0, direccion="", genero='', estadoCivil="", cedulaCodeudor1=0, cedulaCodeudor2=0):
        super().__init__(cedula, nombre, telefono, direccion, genero, estadoCivil)
        self._cedulaCodeudor1 = cedulaCodeudor1
        self._cedulaCodeudor2 = cedulaCodeudor2
        Cliente.clientes.append(self)

    def retornarInformacion(self):
        return "Cedula: " + self._cedula + "\nNombre: " + self._nombre + "\nTelefono: " + self._telefono +\
               " \nDireccion: " + self._direccion + "\nGenero: " + self._genero + "\nEstadoCivil: " + self._estadoCivil +\
               "\nCedula del codeudor # 1" + self._cedulaCodeudor1 + "\nCedula del codeudor # 2" + self._cedulaCodeudor2

    def getCedulaCodeudor1(self):
        return self._cedulaCodeudor1
    def setCedulaCodeudor1(self, cedula):
        self._cedulaCodeudor1 = cedula

    def getCedulaCodeudor2(self):
        return self._cedulaCodeudor2
    def setCedulaCodeudor2(self, cedula):
        self._cedulaCodeudor2= cedula

    @classmethod
    def getClientes(cls):
        return cls.clientes
    @classmethod
    def setClientes(cls, clientes):
        cls.clientes = clientes

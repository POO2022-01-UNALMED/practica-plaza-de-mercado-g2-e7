from gestorAplicacion.clientes.persona import Persona

class Codeudor(Persona):
    codeudores = []

    def __init__(self, cedula=0, nombre="", telefono=0, direccion='', genero='', estadoCivil=''):
        super().__init__(cedula, nombre, telefono, direccion, genero, estadoCivil)
        Codeudor.codeudores.append(self)

    def retornarInformacion(self):
        return "Cedula: " + self._cedula + "\nNombre: " + self._nombre + "\nTelefono: " + self._telefono +\
               " \nDireccion: " + self._direccion + "\nGenero: " + self._genero + "\nEstadoCivil: " + self._estadoCivil

    @classmethod
    def getCodeudores(cls):
        return cls.codeudores
    @classmethod
    def setCodeudores(cls, codeudores):
        cls.codeudores = codeudores


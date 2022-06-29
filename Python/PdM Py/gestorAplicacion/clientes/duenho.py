from gestorAplicacion.clientes.persona import Persona


class Duenho(Persona):
    duenhos = []

    def __init__(self, cedula=0, nombre="", telefono=0, direccion="", genero='', estadoCivil=''):
        super().__init__(cedula, nombre, telefono, direccion, genero, estadoCivil)
        Duenho.duenhos.append(self)

    def retornarInformacion(self):
        return "Cedula: " + self._cedula + "\nNombre: " + self._nombre + "\nTelefono: " + self._telefono +\
               "\nDireccion: " + self._direccion + "\nGenero: " + self._genero + "\nEstadoCivil: " + self._estadoCivil

    @classmethod
    def getDuenhos(cls):
        return cls.duenhos

    @classmethod
    def setDuehos(cls, duenhos):
        cls.duenhos = duenhos


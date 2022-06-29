class Persona():
    def __init__(self, cedula, nombre, telefono, direccion, genero, estadoCivil):
        self._cedula = cedula
        self._nombre = nombre
        self._telefono = telefono
        self._direccion = direccion
        self._genero = genero
        self._estadoCivil = estadoCivil

    def retornarInformacionCorta(self):
        return ("cedula: " + str(self._cedula) + " \nNombre: " + str(self._nombre))

    def getCedula(self):
        return self._cedula
    def setCedula(self, cedula):
        self._cedula = cedula

    def getNombre(self):
        return self._nombre
    def setNombre(self, nombre):
        self._nombre = nombre

    def getTelefono(self):
        return self._telefono
    def setTelefono(self, telefono):
        self._telefono = telefono

    def getDireccion(self):
        return self._direccion
    def setDireccion(self, direccion):
        self._direccion = direccion

    def getGenero(self):
        return self._genero
    def setGenero(self, genero):
        self._genero = genero

    def getEstadoCivil(self):
        return self._estadoCivil
    def setEstadoCivil(self, estadoCivil):
        self._estadoCivil = estadoCivil
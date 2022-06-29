from excepciones.errores import Errores

class ExcepcionTipo(Errores):
    
    def __init__(self, mensaje):
        super().__init__("¡El tipo de dato no corresponde al solicitado!\n" + mensaje)
        self._mensaje = mensaje
        
import os
import pathlib
import pickle


from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.sector import Sector


class Deserializador():
    def deserializar(lista, className):
        def camino(className):
            string = os.path.join(pathlib.Path(__file__).parent.absolute(), "temp\\" + className + ".txt")
            return string
        try:
            pickefile = open(camino(className), 'rb')
        except:
            pickefile = open(camino(className), 'x')
            pickefile = open(camino(className), 'rb')
        if os.path.getsize(camino(className)) > 0:
            lista = pickle.load(pickefile)

        pickefile.close()
        return lista

    def deserializarTodo():
        Cliente.clientes = Deserializador.deserializar(Cliente.clientes, "Clientes")
        Codeudor.codeudores = Deserializador.deserializar(Codeudor.codeudores, "Codeudores")
        Duenho.duenhos = Deserializador.deserializar(Duenho.duenhos, "Duenhos")
        Contrato.contratos = Deserializador.deserializar(Contrato.contratos, "Contratos")
        Local.locales = Deserializador.deserializar(Local.locales, "Locales")
        Sector.sectores = Deserializador.deserializar(Sector.sectores, "Sectores")

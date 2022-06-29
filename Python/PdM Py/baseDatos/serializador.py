import os
import pathlib
import pickle

from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.sector import Sector


class Serializador():
    def serializar(lista, className):
        def camino(className):
            string = os.path.join(pathlib.Path(__file__).parent.absolute(), "temp\\" + className + ".txt")
            return string
        try:
            # Creo el arhivo pickle para guardar los objetos
            picklefile = open(camino(className), 'wb')
            # pickle el objeto en el arhivo
            pickle.dump(lista, picklefile)
            # cierro el archivo para guardar
            pickle.close()
        except:
            print("Hay un error")

    def serializarTodo():
        Serializador.serializar(Cliente.getClientes(), "Clientes")
        Serializador.serializar(Codeudor.getCodeudores(), "Codeudores")
        Serializador.serializar(Duenho.getDuenhos(), "Duenhos")
        Serializador.serializar(Contrato.getContratos(), "Contratos")
        Serializador.serializar(Local.getLocales(), "Locales")
        Serializador.serializar(Sector.getSectores(), "Sectores")
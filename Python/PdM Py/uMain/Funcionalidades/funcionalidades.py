from tkinter import Frame, BOTH, Toplevel, Tk, Label, Button, LEFT, RIGHT



from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.sector import Sector
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.ObtenerDatos import ObtenerDatos
import datetime

from uMain.excepciones.excepcionPresenciaDatos import ExcepcionPresenciaDatos
from uMain.excepciones.excepcionTipoInt import ExcepcionTipoInt
from uMain.fieldFrame import FieldFrame


class Funcionalidades(Frame):
    def __init__(self):
        super().__init__()
        self.plaza = Plaza()
        self.sector = Sector()

    def seleccionarCodeudor(self, n):
        indiceCodeudor = -1
        while indiceCodeudor == -1:
            print("Ingrese la cedula del codeudor # " + str(n) + "\n\n")
            print(self.plaza.mostrarCodeudores())
            cedulaCodeudor = int(input())
            try:
                ExcepcionPresenciaDatos.presenciaDatos(["cedula"],cedulaCodeudor)
            except ExcepcionPresenciaDatos:
                return
            try:
                ExcepcionTipoInt.tipoInt(["cedula"],cedulaCodeudor)
            except ExcepcionTipoInt:
                return

            indiceCodeudor = self.plaza.buscarCodeudor(cedulaCodeudor)
            if indiceCodeudor == -1:
                print("No existe ningun codeudor registrado con esa cedula")
        return  indiceCodeudor

    def seleccionarCliente(self):
        indiceCliente = -1
        while indiceCliente == -1:
            print("Ingrese la cedula del cliente\n\n" +self.plaza.mostrarClientes()+"\n")
            cedulaCliente = int(input())
            indiceCliente = self.plaza.buscarCliente(cedulaCliente)
            if indiceCliente == -1:
                print("No existe ningun cliente registradp con esa cedula")
        return indiceCliente

    def seleccionarLocal(self):
        indices= [-1,-1]
        while indices[0] == -1 or indices[1] == -1:
            print("Ingrese el codigo del local\n\n" + self.plaza.mostrarLocales() + "\n")
            aux= input()
            try:
                ExcepcionPresenciaDatos.presenciaDatos(["codigo"],aux)
            except ExcepcionPresenciaDatos:
                return
            try:
                ExcepcionTipoInt.tipoInt(["codigo"],aux)
            except ExcepcionTipoInt:
                return
            indices = self.plaza.buscarLocales(aux)
            if indices[0] == -1 or indices[1] == -1:
                print("No se encontro ningun local con el codigo seleccionado")
        return indices


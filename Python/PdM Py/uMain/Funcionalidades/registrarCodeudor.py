from tkinter import Frame, BOTH, Button, LEFT, RIGHT, Label, messagebox

from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.infraestructura.sector import Sector
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.Funcionalidades.funcionalidades import Funcionalidades
from uMain.ObtenerDatos import ObtenerDatos
import datetime

from uMain.excepciones.excepcionPresenciaDatos import ExcepcionPresenciaDatos
from uMain.excepciones.excepcionTipoInt import ExcepcionTipoInt
from uMain.excepciones.excepcionTipoString import ExcepcionTipoString
from uMain.fieldFrame import FieldFrame


class RegistrarCodeudor(Funcionalidades):
    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Registrar Codeudor", font="Helvetica 10")
        info = "En este apartado se podra registrar un codeudor, que es necesario para la realizacion de un contrato y para poder registrar un cliente"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese la cedula del codeudor", "Ingrese el nombre del codeudor",
                          "Ingrese el telefono del codeudor", "Ingrese la direccion del codeudor",
                          "Ingrese el genero del codeudor (M ó F)", "Ingrese el estado civil del codeudor"]
        self.valores = ["", "", "", "", False, ""]
        self.habilitados = [True, True, True, True, True, True]
        self.combobox = [False, False, False, False, ["M", "F"], False]
        self.dialogos = FieldFrame(self, "Criterios", self.criterios, "Valores", self.valores, self.habilitados, self.combobox)
        self.dialogos.pack(padx=5, pady=5)

        botones = Frame(master=self)
        aceptar = Button(master=self, text="Aceptar", font="Helvetica 11 bold",
                         bg="grey", fg="white", borderwidth=3, relief="raised",
                         command=self.aceptar)
        aceptar.pack(side=LEFT, padx=5, pady=5)
        borrar = Button(master=self, text="Borrar", font="Helvetica 11 bold",
                        bg="grey", fg="white", borderwidth=3, relief="raised",
                        command=self.borrar)
        borrar.pack(side=RIGHT, padx=5, pady=5)

    def borrar(self):
        self.dialogos.getComponente("Ingrese la cedula del codeudor").delete(0, "end")
        self.dialogos.getComponente("Ingrese el nombre del codeudor").delete(0, "end")
        self.dialogos.getComponente("Ingrese el telefono del codeudor").delete(0, "end")
        self.dialogos.getComponente("Ingrese la direccion del codeudor").delete(0, "end")
        self.dialogos.getComponente("Ingrese el genero del codeudor (M ó F)").set("")
        self.dialogos.getComponente("Ingrese el estado civil del codeudor").delete(0, "end")

    def aceptar(self):
        cedulaExistente = True
        cedulaCodeudor = self.dialogos.getValue("Ingrese la cedula del codeudor")
        while cedulaExistente:

            try:
                ExcepcionPresenciaDatos.presenciaDatos(["cedula"],[cedulaCodeudor])
            except ExcepcionPresenciaDatos:
                return
            try:
                ExcepcionTipoInt.tipoInt(["cedula"],[cedulaCodeudor])
            except ExcepcionTipoInt:
                return
            if self.plaza.buscarCodeudor(cedulaCodeudor) != -1:
                 messagebox.showinfo("Advertencia","Esta cedula ya existe, por favor ingrese una distinta")
                 cedulaCodeudor= -1
            else:
                cedulaExistente= False

        nombreCodeudor = self.dialogos.getValue("Ingrese el nombre del codeudor")
        telefonoCodeudor = self.dialogos.getValue("Ingrese el telefono del codeudor")
        direccionCodeudor = self.dialogos.getValue("Ingrese la direccion del codeudor")
        generoCodeudor = self.dialogos.getValue("Ingrese el genero del codeudor (M ó F)")
        estadoCivilCodeudor = self.dialogos.getValue("Ingrese el estado civil del codeudor")

        criterios=["cedula del codeudor", "nombre del codeudor", "telefono del codeudor", "direccion del codeudor", "genero del codeudor", "estado civil del codeudor"]
        valores= [cedulaCodeudor, nombreCodeudor, telefonoCodeudor, direccionCodeudor, generoCodeudor, estadoCivilCodeudor]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return
        try:
            ExcepcionTipoString.tipoString(["nombre del codeudor", "genero del codeudor", "estado civil del codeudor"], [nombreCodeudor, generoCodeudor, estadoCivilCodeudor])
        except ExcepcionTipoString:
            return
        try:
            ExcepcionTipoInt.tipoInt(["telefono del codeudor"], [telefonoCodeudor])
        except ExcepcionTipoInt:
            return

        nuevoCodeudor= Codeudor()
        nuevoCodeudor.setCedula(cedulaCodeudor)
        nuevoCodeudor.setNombre(nombreCodeudor)
        nuevoCodeudor.setTelefono(telefonoCodeudor)
        nuevoCodeudor.setDireccion(direccionCodeudor)
        nuevoCodeudor.setGenero(generoCodeudor)
        nuevoCodeudor.setEstadoCivil(estadoCivilCodeudor)
        self.plaza.setCodeudores(nuevoCodeudor)
        messagebox.showinfo("Registrar Codeudor","Codeudor registrado correctamente")


    def registrarCodeudor(self):
        obj = ObtenerDatos()
        cedulaCodeudor= -1
        cedulaExistente = True
        while cedulaExistente:
            cedulaCodeudor = int(input("Ingrese la cedula del codeudor"))
            try:
                ExcepcionPresenciaDatos.presenciaDatos(["cedula"],cedulaCodeudor)
            except ExcepcionPresenciaDatos:
                return
            try:
                ExcepcionTipoInt.tipoInt(["cedula"],cedulaCodeudor)
            except ExcepcionTipoInt:
                return
            if self.plaza.buscarCodeudor(cedulaCodeudor)!= -1:
                print("Esta cedula ya existe, por favor ingrese una distinta")
            else:
                cedulaExistente= False
        nuevoCodeudor= Codeudor()
        nuevoCodeudor.setCedula(cedulaCodeudor)
        obj.obtenerDatosCodeudor(nuevoCodeudor)
        self.plaza.setCodeudores(nuevoCodeudor)
        print("Codeudor registrado correctamente")
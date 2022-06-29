from tkinter import Frame, BOTH, Label, LEFT, Button, RIGHT, messagebox

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


class RegistrarCliente(Funcionalidades):
    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Registrar Cliente", font="Helvetica 10")
        info = "Los cliente deben er registrados antes de realizar un contrato para poder relizar la validacion de que tenga lo 2 codeudores requeridos"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese la cedula del cliente", "Ingrese el nombre del cliente",
                          "Ingrese el telefono del cliente", "Ingrese la direccion del cliente",
                          "Ingrese el genero del cliente (M ó F)", "Ingrese el estado civil del cliente",
                          "Ingrese la cedula del codeudor 1", "Ingrese el nombre del codeudor 2"]
        self.valores = ["", "", "", "", False, "", False, False]
        self.habilitados = [True, True, True, True, True, True, True, True]
        self.combobox = [False, False, False, False, ["M", "F"], False, self.plaza.getCodeudores(), self.plaza.getCodeudores()]

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
        self.dialogos.getComponente("Ingrese la cedula del cliente").delete(0, "end")
        self.dialogos.getComponente("Ingrese el nombre del cliente").delete(0, "end")
        self.dialogos.getComponente("Ingrese el telefono del cliente").delete(0, "end")
        self.dialogos.getComponente("Ingrese la direccion del cliente").delete(0, "end")
        self.dialogos.getComponente("Ingrese el genero del cliente (M ó F)").set("")
        self.dialogos.getComponente("Ingrese el estado civil del cliente").delete(0, "end")
        self.dialogos.getComponente("Ingrese la cedula del codeudor 1").set("")
        self.dialogos.getComponente("Ingrese el nombre del codeudor 2").set("")

    def aceptar(self):
        if len(self.plaza.getCodeudores())==0 and len(self.plaza.getCodeudores()) <= 1 or len(Codeudor.getCodeudores())<=0 and len(Codeudor.getCodeudores()) <= 1:
            messagebox.showwarning("Advertencia","Para registrar un cliente deben existir por lo menos 2 codeudores ")
        else:
            cedulaCliente = self.dialogos.getValue("Ingrese la cedula del cliente")
            cedulaExistente=True
            while cedulaExistente:
                try:
                    ExcepcionPresenciaDatos.presenciaDatos(["cedula"],[cedulaCliente])
                except ExcepcionPresenciaDatos:
                    return
                try:
                    ExcepcionTipoInt.tipoInt(["cedula"],[cedulaCliente])
                except ExcepcionTipoInt:
                    return
                if self.plaza.buscarCliente(cedulaCliente) != -1:
                    messagebox.showinfo("Advertencia","Esta cedula ya existe, por favor ingrese una distinta ")
                    cedulaCliente = -1
                else:
                    cedulaExistente = False
            nombreCliente = self.dialogos.getValue("Ingrese el nombre del cliente")
            telefonoCliente = self.dialogos.getValue("Ingrese el telefono del cliente")
            direccionCliente = self.dialogos.getValue("Ingrese la direccion del cliente")
            generoCliente = self.dialogos.getValue("Ingrese el genero del cliente (M ó F)")
            estadoCivilCliente = self.dialogos.getValue("Ingrese el estado civil del cliente")
            cedulaCodeudor1 = self.dialogos.getValue("Ingrese la cedula del codeudor 1")
            nombreCodeudor2 = self.dialogos.getValue("Ingrese el nombre del codeudor 2")

            criterios=["cedula del cliente", "nombre del cliente", "telefono del cliente", "direccion del cliente", "genero del cliente", "estado civil del cliente"]
            valores= [cedulaCliente, nombreCliente, telefonoCliente, direccionCliente, generoCliente, estadoCivilCliente]
            try:
                ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
            except ExcepcionPresenciaDatos:
                return

            try:
                ExcepcionTipoString.tipoString(["nombre del cliente", "genero del cliente", "estado civil del cliente"], [nombreCliente, generoCliente, estadoCivilCliente])
            except ExcepcionTipoString:
                return
            try:
                ExcepcionTipoInt.tipoInt(["telefono del cliente"], [telefonoCliente])
            except ExcepcionTipoInt:
                return

            nuevoCliente = Cliente()
            nuevoCliente.setCedula(cedulaCliente)
            nuevoCliente.setNombre(nombreCliente)
            nuevoCliente.setTelefono(telefonoCliente)
            nuevoCliente.setDireccion(direccionCliente)
            nuevoCliente.setGenero(generoCliente)
            nuevoCliente.setEstadoCivil(estadoCivilCliente)
            cedulaCodeudor1 = Codeudor.getCodeudores()[self.seleccionarCodeudor(1)].getCedula()
            cedulaCodeudor2 = cedulaCodeudor1
            while cedulaCodeudor1 == cedulaCodeudor2:
                cedulaCodeudor2= Codeudor.getCodeudores()[self.seleccionarCodeudor(2)].getCedula()
                if cedulaCodeudor1 == cedulaCodeudor2:
                    messagebox.showinfo("Advertencia","Los codeudores deben ser distintos")
            Cliente.getClientes().append(nuevoCliente)
            messagebox.showinfo("Registrar Cliente","Cliente registrado correctamente ")
    def registrarCliente(self):
        obj = ObtenerDatos()
        if len(self.plaza.getCodeudores())==0 and len(self.plaza.getCodeudores()) == 1 or len(Codeudor.getCodeudores())==0 and len(Codeudor.getCodeudores()) == 1:
            print("Para registrar un cliente deben existir por lo menos 2 codeudores ")
        else:
            cedulaCliente = -1
            cedulaExistente=True

            while cedulaExistente:
                cedulaCliente= int(input("Ingrese la cedula del cliente"))
                try:
                    ExcepcionPresenciaDatos.presenciaDatos(["cedula"],cedulaCliente)
                except ExcepcionPresenciaDatos:
                    return
                try:
                    ExcepcionTipoInt.tipoInt(["cedula"],cedulaCliente)
                except ExcepcionTipoInt:
                    return
                if self.plaza.buscarCliente(cedulaCliente) != -1:
                    print("Esta cedula ya existe, por favor ingrese una distinta ")
                else:
                    cedulaExistente = False
            nuevoCliente = Cliente()
            nuevoCliente.setCedula(cedulaCliente)
            obj.obtenerDatosCliente(nuevoCliente)
            cedulaCodeudor1 = Codeudor.getCodeudores()[self.seleccionarCodeudor(1)].getCedula()
            cedulaCodeudor2 = cedulaCodeudor1
            while cedulaCodeudor1 == cedulaCodeudor2:
                cedulaCodeudor2= Codeudor.getCodeudores()[self.seleccionarCodeudor(2)].getCedula()
                if cedulaCodeudor1 == cedulaCodeudor2:
                    print("Los codeudores deben ser distintos")
            Cliente.getClientes().append(nuevoCliente)
            print("Cliente registrado correctamente ")
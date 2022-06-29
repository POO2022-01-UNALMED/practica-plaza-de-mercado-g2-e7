from tkinter import Frame, BOTH, Label, Button, LEFT, RIGHT, messagebox

from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.clientes.codeudor import Codeudor
from gestorAplicacion.clientes.duenho import Duenho
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


class RegistrarDuenho(Funcionalidades):
    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Registrar Duenho", font="Helvetica 10")
        info = "En este apartado se podra registrar un nuevo duenho, para luego poder asignarlo a un local"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese la cedula del duenho", "Ingrese el nombre del duenho",
                          "Ingrese el telefono del duenho", "Ingrese la direccion del duenho",
                          "Ingrese el genero del duenho (M ó F)", "Ingrese el estado civil del duenho"]
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
        self.dialogos.getComponente("Ingrese la cedula del duenho").delete(0, "end")
        self.dialogos.getComponente("Ingrese el nombre del duenho").delete(0, "end")
        self.dialogos.getComponente("Ingrese el telefono del duenho").delete(0, "end")
        self.dialogos.getComponente("Ingrese la direccion del duenho").delete(0, "end")
        self.dialogos.getComponente("Ingrese el genero del duenho (M ó F)").set("")
        self.dialogos.getComponente("Ingrese el estado civil del duenho").delete(0, "end")

    def aceptar(self):
        cedulaDuenho = self.dialogos.getValue("Ingrese la cedula del duenho")
        nombreDuenho = self.dialogos.getValue("Ingrese el nombre del duenho")
        telefonoDuenho = self.dialogos.getValue("Ingrese el telefono del duenho")
        direccionDuenho = self.dialogos.getValue("Ingrese la direccion del duenho")
        generoDuenho = self.dialogos.getValue("Ingrese el genero del duenho (M ó F)")
        estadoCivilDuenho = self.dialogos.getValue("Ingrese el estado civil del duenho")

        criterios=["cedula del duenho", "nombre del duenho", "telefono del duenho", "direccion del duenho", "genero del duenho", "estado civil del duenho"]
        valores= [cedulaDuenho, nombreDuenho, telefonoDuenho, direccionDuenho, generoDuenho, estadoCivilDuenho]
        try:
            ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
        except ExcepcionPresenciaDatos:
            return
        try:
            ExcepcionTipoString.tipoString(["nombre del duenho", "genero del duenho", "estado civil del duenho"], [nombreDuenho, generoDuenho, estadoCivilDuenho])
        except ExcepcionTipoString:
            return
        try:
            ExcepcionTipoInt.tipoInt(["telefono del duenho"], [telefonoDuenho])
        except ExcepcionTipoInt:
            return

        cedulaDuenho = -1
        cedulaExistente = True

        while cedulaExistente:
            cedulaDuenho = int(cedulaDuenho)

            if self.plaza.buscarDuenho(cedulaDuenho) != -1:
                messagebox.showinfo("Advertencia", "Esta cedula ya existe, por favor ingrese una distinta ")
            else:
                cedulaExistente = False
        nuevoDuenho = Duenho()
        nuevoDuenho.setCedula(cedulaDuenho)
        nuevoDuenho.setNombre(nombreDuenho)
        nuevoDuenho.setTelefono(telefonoDuenho)
        nuevoDuenho.setDireccion(direccionDuenho)
        nuevoDuenho.setGenero(generoDuenho)
        nuevoDuenho.setEstadoCivil(estadoCivilDuenho)

        self.plaza.getDuenhos().append(nuevoDuenho)
        messagebox.showinfo("Registrar Duenho","Dueño registrado correctamente")

from tkcalendar import Calendar
from tkinter import Frame, BOTH, Button, LEFT, RIGHT, Label, Toplevel, messagebox

from gestorAplicacion.infraestructura.contrato import Contrato

import datetime

from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.fieldFrame import FieldFrame


class MostrarListadoContratosVigentes(Frame):
    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Listado de contratos vigentes", font="Helvetica 10")
        info = "Esta opcion muestra los contratos vigentes apartir de una fecha determinada"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese la fecha a buscar (Año/Mes/Dia)"]
        self.valores = [""]
        self.habilitados = [True]
        self.combobox = [False]
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
        self.dialogos.getComponente("Ingrese la fecha a buscar (Año/Mes/Dia)").delete(0, "end")

    def aceptar(self):
        plaza = Plaza()
        fechaBusquedaSTR = self.dialogos.getValue("Ingrese la fecha a buscar (Año/Mes/Dia)")
        try:
            fechaBusqueda = datetime.datetime.strptime(fechaBusquedaSTR, "%Y/%m/%d")
            listado = "Listado de contratos vigentes\n\n" + fechaBusquedaSTR + "\n\n"
            for contrato in plaza.getContratos():
                if fechaBusqueda - contrato.getFechaInicio() >= datetime.timedelta(days=0) and fechaBusqueda - contrato.getFechaFin() < datetime.timedelta(days=0):
                    listado += contrato.retornarInformacion() + "\n"
            for contrato in Contrato.getContratos():
                if fechaBusqueda - contrato.getFechaInicio() >= datetime.timedelta(
                        days=0) and fechaBusqueda - contrato.getFechaFin() < datetime.timedelta(days=0):
                    listado += contrato.retornarInformacion() + "\n"
            messagebox.showinfo(message=listado, title="Contratos vigentes")
        except ValueError:
            messagebox.showinfo(message="La fecha ingresada no es valida", title="Advertencia")

    def mostrarListadoContratosVigentes(self):
        try:
            fechaBusquedaSTR = input("Ingrese la fecha a buscar (Año/Mes/Dia)")
            fechaBusqueda = datetime.datetime.strptime(fechaBusquedaSTR, "%Y/%m/%d")
            listado = "Listado de contratos vigentes\n\n" + fechaBusquedaSTR + "\n\n"
            for contrato in self.plaza.getContratos():
                if fechaBusqueda-contrato.getFechaInicio() >= datetime.timedelta(days=0) and fechaBusqueda-contrato.getFechaFin() < datetime.timedelta(days=0):
                    listado += contrato.retornarInformacion() + "\n"
            for contrato in Contrato.getContratos():
                if fechaBusqueda - contrato.getFechaInicio() >= datetime.timedelta(
                        days=0) and fechaBusqueda - contrato.getFechaFin() < datetime.timedelta(days=0):
                    listado += contrato.retornarInformacion() + "\n"
            print(listado)
        except ValueError:
            print("La fecha ingresada no es valida" + "\n")
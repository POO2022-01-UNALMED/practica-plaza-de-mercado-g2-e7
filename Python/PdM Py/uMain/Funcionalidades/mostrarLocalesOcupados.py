from tkinter import Frame, BOTH, messagebox, Label

from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.Funcionalidades.funcionalidades import Funcionalidades
from uMain.ObtenerDatos import ObtenerDatos
import datetime

class MostrarLocalesOcupados(Frame):

    def __init__(self):
        super().__init__()
        local= Local()
        info = "Listado de los locales desocupados: \n\n"
        for i in range(len(local.getLocales())):
            local = Local.getLocales()[i]
            if local.isOcupado() == True:
                info += "\n" + local.retornarInformacion()+"\n\n"
            else:
                messagebox.showinfo(message="No hay locales ocupados", title="Advertencia")

            print(info)
        nombre = Label(master=self, text="Locales Ocupados", font="Helvetica 10")
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)



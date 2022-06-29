from tkinter import Frame, BOTH, Button, LEFT, RIGHT, Label, messagebox

from gestorAplicacion.infraestructura.local import Local

import datetime

from uMain.Funcionalidades.funcionalidades import Funcionalidades


class MostrarLocalesDesocupados(Funcionalidades):
    def __init__(self):
        super().__init__()
        local= Local()
        info = "Listado de los locales desocupados: \n\n"
        for i in range(len(local.getLocales())):
            local= Local.getLocales()[i]
            if not local.isOcupado():
                info += "\n"+ local.retornarInformacion()+"\n\n"
            else:
                messagebox.showinfo(message="No hay locales desocupados", title="Advertencia")

            print(info)
        nombre = Label(master=self, text="Locales Desocupados", font="Helvetica 10")
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)


    def mostrarLocalesDesocupados(self):
        local= Local()
        listado= "Listado de los locales desocupados\n\n"

        for i in range(len(local.getLocales())):
            local= Local.getLocales()[i]
            if not local.isOcupado():
                listado += "\n"+ local.retornarInformacion()+"\n\n"
            else:
                print("No hay locales desocupados\n")

            print(listado)
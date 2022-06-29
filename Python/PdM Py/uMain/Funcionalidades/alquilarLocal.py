from tkinter import Frame, BOTH, Label, Button, LEFT, RIGHT, messagebox

from gestorAplicacion.clientes.cliente import Cliente
from gestorAplicacion.infraestructura.sector import Sector
from gestorAplicacion.infraestructura.contrato import Contrato
from gestorAplicacion.infraestructura.local import Local
from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.Funcionalidades.funcionalidades import Funcionalidades
from uMain.ObtenerDatos import ObtenerDatos

from uMain.fieldFrame import FieldFrame


class AlquilarLocal(Funcionalidades):
    def __init__(self):

        super().__init__()
        plaza = Plaza()
        nombre = Label(master=self, text="Alquilar Local", font="Helvetica 10")
        info = "Esta funcionalidad nos muestra una lista de los locales disponibles para alquilar, y permite realizar el formulario para crear un contrato"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese la cedula del cliente", "Ingrese el codigo del local",
                          "Ingrese el nombre del interventor", "Ingrese la fecha de inicio del contrato",
                          "Ingrese la fecha de finalizacion del contrato"]
        self.valores = [False, False, "", "", ""]
        self.habilitados = [True, True, True, True, True]
        self.combobox = [[plaza.mostrarClientes()], [], False, False, False]


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
        self.dialogos.getComponente("Ingrese la cedula del cliente").set("")
        self.dialogos.getComponente("Ingrese el codigo del local").set("")
        self.dialogos.getComponente("Ingrese el nombre del interventor").delete(0, "end")
        self.dialogos.getComponente("Ingrese la fecha de inicio del contrato").delete(0, "end")
        self.dialogos.getComponente("Ingrese la fecha de finalizacion del contrato").delete(0, "end")

    def aceptar(self):
        cedulaCliente = self.dialogos.getValue("Ingrese la cedula del cliente")
        codigoLocal = self.dialogos.getValue("Ingrese el codigo del local")
        nombreInterventor = self.dialogos.getValue("Ingrese el nombre del interventor")
        fechaInicio = self.dialogos.getValue("Ingrese la fecha de inicio del contrato")
        fechaFin = self.dialogos.getValue("Ingrese la fecha de finalizacion del contrato")
        plaza = Plaza()
        if len(plaza.getClientes())==0 and len(Cliente.getClientes())==0:
            messagebox.showwarning(message="Para alquilar un local primero debe de registrar clientes", title="Advertencia")
        elif len(plaza.getSectores())==0 and len(Sector.getSectores()) == 0:
            messagebox.showwarning(message="Para alquilar un local primero debe agregar sectores", title="Advertencia")
        elif plaza.obtenerCantidadLocales() == 0 and len(Local.getLocales())==0:
            messagebox.showwarning(message="Para alquilar un local primero debe de agregar locales", title="Advertencia")
        else:
            cedulaCliente = Cliente.getClientes()[self.seleccionarCliente()].getCedula()
            indiceLocal= self.seleccionarLocal()
            Sector.getSectores()[indiceLocal[0]].getLocales()[indiceLocal[1]].setOcupado(True)
            local  = Sector.getSectores()[indiceLocal[0]].getLocales()[indiceLocal[1]]
            codigoLocal= local.getCodigo()
            motoMensual= local.getPrecioBase() +( Sector.getSectores()[indiceLocal[0]].getPrecioBaseM2() * local.getTamanho())
            if local.getTecho():
                motoMensual *= Plaza.PORCENTAJE_AUMENTO_CON_TECHO
            else:
                motoMensual  *= 1
            if local.getCamaraRefrigerante():
                motoMensual *= Plaza.PORCENTAJE_AUMENTO_CON_CAMARA_REFRIGETANTE
            else:
                motoMensual *= 1
            motoMensual *= 1.2
            indiceLocal= self.seleccionarLocal()
            Sector.getSectores()[indiceLocal[0]].getLocales()[indiceLocal[1]].setOcupado(True)
            local  = Sector.getSectores()[indiceLocal[0]].getLocales()[indiceLocal[1]]
            codigoLocal= local.getCodigo()
            motoMensual= local.getPrecioBase() +( Sector.getSectores()[indiceLocal[0]].getPrecioBaseM2() * local.getTamanho())
            if local.getTecho():
                motoMensual *= Plaza.PORCENTAJE_AUMENTO_CON_TECHO
            else:
                motoMensual  *= 1
            if local.getCamaraRefrigerante():
                motoMensual *= Plaza.PORCENTAJE_AUMENTO_CON_CAMARA_REFRIGETANTE
            else:
                motoMensual *= 1
            motoMensual *= 1.2
            contrato = Contrato()
            contrato.setNumero(len(Contrato.getContratos())+1)
            contrato.setCedulaCliente(cedulaCliente)
            contrato.setCodigoLocal(codigoLocal)
            contrato.setMontoMensual(motoMensual)
            contrato.setFechaInicio(fechaInicio)
            contrato.setFechaFin(fechaFin)
            contrato.setNombreIterventor(nombreInterventor)
            Contrato.getContratos().append(contrato)
            contrato.setCedulaCliente(cedulaCliente)
            contrato.setCodigoLocal(codigoLocal)
            messagebox.showinfo("Alquilar Local","Alquiler completado correctamente")



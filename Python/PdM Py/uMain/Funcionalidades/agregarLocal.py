from gestorAplicacion.infraestructura.local import Local
from uMain.Funcionalidades.funcionalidades import Funcionalidades
from tkinter import Frame, BOTH, Label, Button, LEFT, RIGHT, NORMAL, messagebox

from uMain.excepciones.excepcionPresenciaDatos import ExcepcionPresenciaDatos
from uMain.excepciones.excepcionTipoInt import ExcepcionTipoInt
from gestorAplicacion.clientes.duenho import Duenho
from gestorAplicacion.infraestructura.sector import Sector
from uMain.excepciones.excepcionTipoString import ExcepcionTipoString

from uMain.fieldFrame import FieldFrame


class AgregarLocal(Funcionalidades):

    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Agregar Local", font="Helvetica 10")
        info = "Esta funcionalidad permite agregar un local a un sector de la plaza"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)

        self.criterios = ["Ingrese el codigo donde sera agregado el local", "El local tiene techo?", "El local tiene camara refrigerante?","Ingrese el tamanho del local",
                          "Ingrese el precio base del local", "Ingrese la cedula del duenho"]
        self.valores = [False, False, "", "", "", ""]
        self.habilitados = [True, True, True, True, True, True]
        self.combobox = [[self.plaza.mostrarSectores()], ["Si", "No"], ["Si", "No"], False, False, False]
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
        self.dialogos.getComponente("Ingrese el codigo donde sera agregado el local").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese el codigo donde sera agregado el local").delete(0, "end")
        self.dialogos.getComponente("El local tiene techo?").set("")
        self.dialogos.getComponente("El local tiene camara refrigerante?").set("")
        self.dialogos.getComponente("Ingrese el tamanho del local").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese el tamanho del local").delete(0, "end")
        self.dialogos.getComponente("Ingrese el precio base del local").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese el precio base del local").delete(0, "end")
        self.dialogos.getComponente("Ingrese la cedula del duenho").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese la cedula del duenho").delete(0, "end")

    def aceptar(self):
        codigoSector = int(self.dialogos.getValue("Ingrese el codigo donde sera agregado el local"))
        techo = self.dialogos.getValue("El local tiene techo?")
        camara = self.dialogos.getValue("El local tiene camara refrigerante?")
        tamanho = int(self.dialogos.getValue("Ingrese el tamanho del local"))
        precio = int(self.dialogos.getValue("Ingrese el precio base del local"))
        cedula = int(self.dialogos.getValue("Ingrese la cedula del duenho"))
        try:
            ExcepcionPresenciaDatos.presenciaDatos(["codigo de sector","techo","camara refrigerante","tamanho","precio","cedula"],[codigoSector,techo,camara,tamanho,precio,cedula])
        except ExcepcionPresenciaDatos:
            return
        try:
            ExcepcionTipoInt.tipoInt(["tamanho","precio","cedula"],[tamanho,precio,cedula])
        except ExcepcionTipoInt:
            return
        try:
            ExcepcionTipoString.tipoString(["techo","camara refrigerante"],[techo,camara])
        except ExcepcionTipoString:
            return


        indiceSector= -1
        while indiceSector == -1:

            indiceSector = self.plaza.buscarSector(codigoSector)
            if indiceSector== -1:
                messagebox.showwarning("Advertencia","Ingrese un codigo de sector valido ")
        obj=self.agregarLocales(Duenho.getDuenhos(), self.plaza.obtenerCantidadLocales(),cedula)
        obj.setTamanho(tamanho)
        obj.setPrecioBase(precio)
        obj.setCamaraRefrigerante(camara)
        obj.setCodigo(techo)


    def agregarLocales(self, duenhos, codigoLocal, cedula):
        nuevoLocal= Local()
        #self.obtenerDatosLocal(nuevoLocal)
        nuevoLocal.setCodigo(codigoLocal)


        cedulaExistente= False

        while not cedulaExistente :
            cedulaExistente = self.sector.buscarDuenho(duenhos, cedula)

            if not cedulaExistente:
                messagebox.showwarning("Advertencia","Ingrese una cedula valida")
                cedula = -1
                cedulaExistente = False
        if cedula != -1:
            nuevoLocal.setCedulaDuenho(cedula)
            self.sector.setLocalesS(nuevoLocal)
            messagebox.showinfo("Agregar Local",self.sector.getLocalesS())
            messagebox.showinfo("Agregar Local","Local agregado correctamente al sector")
            return nuevoLocal

    def agregarLocal(self):
        criterio= ["codigo de sector"]
        if len(self.plaza.getSectores()) == 0 and len(Sector.getSectores())== 0 :
            print("Antes de agregar locales debe de agregar sectores a la plaza ")
        elif len(self.plaza.getDuenhos())==0 and len(Duenho.getDuenhos())== 0:
            print("Antes de agregar locales debe agregar dueños")
        else:
            codigoSector = -1
            indiceSector= -1
            while indiceSector == -1:

                print("Ingrese el codigo del sector donde sera agregado el local "+ self.plaza.mostrarSectores())
                codigoSector = int(input())
                try:
                    ExcepcionPresenciaDatos.presenciaDatos(criterio,codigoSector)
                except ExcepcionPresenciaDatos:
                    return

                try:
                    ExcepcionTipoInt.tipoInt(criterio,codigoSector)
                except ExcepcionTipoInt:
                    return
                indiceSector = self.plaza.buscarSector(codigoSector)
                if indiceSector== -1:
                    print("Ingrese un codigo de sector valido ")
            self.agregarLocales(Duenho.getDuenhos(), self.plaza.obtenerCantidadLocales())
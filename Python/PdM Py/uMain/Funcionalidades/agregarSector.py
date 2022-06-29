from tkinter import Frame, BOTH, messagebox, Label, Button, LEFT, RIGHT, NORMAL, DISABLED



from uMain.excepciones.excepcionTipoFloat import ExcepcionTipoFloat
from uMain.excepciones.excepcionTipoString import ExcepcionTipoString


from uMain.excepciones.excepcionPresenciaDatos import ExcepcionPresenciaDatos
from gestorAplicacion.infraestructura.sector import Sector
from uMain.Funcionalidades.funcionalidades import Funcionalidades

from uMain.fieldFrame import FieldFrame


class AgregarSector(Funcionalidades):

    def __init__(self):
        super().__init__()
        nombre = Label(master=self, text="Agregar Sector", font="Helvetica 10")
        info = "Este formulario permite agregar un sector a la plaza, ingrese los datos solicitados y presione el boton aceptar"
        descripcion= Label(master=self, text= info, font="Helvetica 10")
        nombre.pack(fill=BOTH, padx=5, pady=5)
        descripcion.pack(fill=BOTH, padx=5, pady=5)


        self.criterios = ["Ingrese el nombre del sector", "Ingrese el precio base por m2 del sector"]

        self.valores = ["", ""]

        self.habilitados = [True, True]

        self.combobox = [False, False]

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
        self.dialogos.getComponente("Ingrese el nombre del sector").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese el nombre del sector").delete(0, "end")
        self.dialogos.getComponente("Ingrese el precio base por m2 del sector").configure(state= NORMAL)
        self.dialogos.getComponente("Ingrese el precio base por m2 del sector").delete(0, "end")


    def aceptar(self):
        nombreSector = self.dialogos.getComponente("Ingrese el nombre del sector").get()
        precioBase = self.dialogos.getComponente("Ingrese el precio base por m2 del sector").get()
        if precioBase == "" or precioBase == "":
            messagebox.showinfo("Error", "Debe ingresar todos los datos")
        else:
            criterios=["nombre del sector", "precio base por m2"]
            valores= [nombreSector, precioBase]
            try:
                ExcepcionPresenciaDatos.presenciaDatos(criterios, valores)
            except ExcepcionPresenciaDatos:
                return

            try:
                ExcepcionTipoFloat.tipoFloat(["precio base por m2"], [precioBase])
            except ExcepcionTipoFloat:
                return

            try:
                ExcepcionTipoString.tipoString(["nombre del sector"], [nombreSector])
            except ExcepcionTipoString:
                return

        nuevoSector = Sector()
        nuevoSector.setNombre(nombreSector)
        nuevoSector.setPrecioBaseM2(precioBase)
        nuevoSector.setCodigo(self.plaza.getCodigoSectores()+1)
        self.plaza.getSectores().append(nuevoSector)
        messagebox.showinfo("Agregar Sector", "Sector agregado correctamente")




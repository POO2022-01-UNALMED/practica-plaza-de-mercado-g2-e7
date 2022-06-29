from tkinter import Tk, messagebox, Menu, Frame, Label, Button, BOTH, TOP, RIGHT, LEFT, BOTTOM, ttk, FALSE, W
from PIL import Image, ImageTk


from baseDatos.serializador import Serializador
from baseDatos.deserializador import Deserializador
from gestorAplicacion.infraestructura.plaza import Plaza
from uMain.Funcionalidades.agregarLocal import AgregarLocal
from uMain.Funcionalidades.agregarSector import AgregarSector
from uMain.Funcionalidades.alquilarLocal import AlquilarLocal
from uMain.Funcionalidades.mostrarListadoContratosVigentes import MostrarListadoContratosVigentes
from uMain.Funcionalidades.mostrarLocalesDesocupados import MostrarLocalesDesocupados
from uMain.Funcionalidades.mostrarLocalesOcupados import MostrarLocalesOcupados
from uMain.Funcionalidades.registrarCliente import RegistrarCliente
from uMain.Funcionalidades.registrarCodeudor import RegistrarCodeudor
from uMain.Funcionalidades.registrarDuenho import RegistrarDuenho
from uMain.excepciones.excepcionPresenciaArchivo import ExcepcionPresenciaArchivos
from uMain.excepciones.excepcionPresenciaImagenes import ExcepcionPresenciaImagenes


def inicio():
    a = ["Imagenes/Plaza/1.jpg", "Imagenes/Plaza/2.jpg", "Imagenes/Plaza/3.jpg"]
    b = ["Imagenes/Yo/1.jpg", "Imagenes/Yo/2.jpg", "Imagenes/Yo/3.jpg", "Imagenes/Yo/4.jpg"]
    dir = a + b
    try:
        ExcepcionPresenciaImagenes.presenciaImagenes(dir)
        Deserializador.deserializarTodo()
    except ExcepcionPresenciaArchivos:
        return
    except ExcepcionPresenciaImagenes:
        return


inicio()

# A continuación se encuentran los objetos que fueron guardados originalmente en la persistencia inicial.


ventana = Tk()
ventana.title("Sistema gestor de plaza " + Plaza.nombre)
ventana.geometry("+10+10")
ventana.resizable(False, False)
ventana.option_add("*tearOff", FALSE)
# ventana.iconbitmap("../Imagenes/Z.ico")
ventana.grid_columnconfigure(0, weight=1)
ventana.grid_columnconfigure(1, weight=1)


# Con la siguiente función se ocultan todos los frames de la aplicación, esto para la correcta transición entre menús.
def ocultarTodo():
    ventanaInicio.pack_forget()
    ventanaUsuario.pack_forget()
    ventanaAgregarSector.pack_forget()
    ventanaAgregarLocal.pack_forget()
    ventanaRegistrarDuenho.pack_forget()
    ventanaRegistrarCliente.pack_forget()
    ventanaRegistrarCodeudor.pack_forget()
    ventanaAlquilarLocal.pack_forget()
    ventanaMostrarLocalesDesocupados.pack_forget()
    ventanaMostrarLocalesOcupados.pack_forget()
    ventanaMostrarListadoContratosVigentes.pack_forget()


# Con la siguiente función se borran los campos de FieldFrame para todas las funcionalidades, además que se ocultan
# todas las tablas Treeview de las funcionalidades de consulta.
def borrarTodo():
    ventanaAgregarSector.borrar()
    ventanaAgregarLocal.borrar()
    ventanaRegistrarDuenho.borrar()
    ventanaRegistrarCliente.borrar()
    ventanaRegistrarCodeudor.borrar()
    ventanaAlquilarLocal.borrar()
#    ventanaMostrarLocalesDesocupados.borrar()


# COMANDOS RELATIVOS A LA VENTANA DE INICIO:

# Con estas variables se define la hoja de vida de los autores de la aplicación que será mostrada en la ventana de Inicio.

VidaYo = """Nombre: Daniel Felipe Puentes Rocha.
Estudiante de Estadistica que intenta ser un buen programador.\nAmante a los videojuegos y a la musica.\n"""


# Por medio de la siguiente función se cambia la imagen relativa al sistema (Imágenes de animales).
posicionImagen = 1


def cambiarImagen(e):
    global posicionImagen
    posicionImagen += 1
    if posicionImagen == 4:
        posicionImagen = 1
    FotoAnimal = (Image.open("Imagenes/Plaza/" + str(posicionImagen) + ".jpg")).resize((400, 400), Image.ANTIALIAS)
    FotoAnimal = ImageTk.PhotoImage(FotoAnimal)
    LabelFotoAnimal.configure(image=FotoAnimal)
    LabelFotoAnimal.image = FotoAnimal


# Por medio de la siguiente función se cambia la hoja de vida y las imágenes asociadas a cada autor de la aplicación.
posicionVida = 0


def cambiarVida(e):
    global posicionVida
    posicionVida += 1
    if posicionVida == 4:
        posicionVida = 0
    ListaFotos = ["Yo"]
    Foto1 = (Image.open("Imagenes/" + ListaFotos[posicionVida] + "/1.jpg")).resize((200, 200), Image.ANTIALIAS)
    Foto1 = ImageTk.PhotoImage(Foto1)
    Foto2 = (Image.open("Imagenes/" + ListaFotos[posicionVida] + "/2.jpg")).resize((200, 200), Image.ANTIALIAS)
    Foto2 = ImageTk.PhotoImage(Foto2)
    Foto3 = (Image.open("Imagenes/" + ListaFotos[posicionVida] + "/3.jpg")).resize((200, 200), Image.ANTIALIAS)
    Foto3 = ImageTk.PhotoImage(Foto3)
    Foto4 = (Image.open("Imagenes/" + ListaFotos[posicionVida] + "/4.jpg")).resize((200, 200), Image.ANTIALIAS)
    Foto4 = ImageTk.PhotoImage(Foto4)
    LabelFoto1.configure(image=Foto1)
    LabelFoto1.image = Foto1
    LabelFoto2.configure(image=Foto2)
    LabelFoto2.image = Foto2
    LabelFoto3.configure(image=Foto3)
    LabelFoto3.image = Foto3
    LabelFoto4.configure(image=Foto4)
    LabelFoto4.image = Foto4
    if posicionVida == 0:
        CuerpoVida.config(text=VidaYo)


# La siguiente función es llamada cuando se accede al menú de "Descripción" de la ventana de Inicio. Por medio de esta
# se muestra al usuario la descripción de la aplicación.
def descripcion():
    descripcion = "En el sistema gestor de zoológico podrá administrar todo lo que tiene que ver con su zoológico: Calcular las ganancias del día; pagar a sus empleados; llevar conteo de los visitantes, de los animales, de los empleados, de las especies, y de los hábitats."
    messagebox.showinfo(title="Descripción de la aplicación",
                        message=descripcion)


# La siguiente función es llamada cuando se accede al menú de "Salir" de la ventana de Inicio. Por medio de esta es que
# antes de cerrarse la ventana se serializan los objetos.
def salirInicio():
    salir = messagebox.askyesno(title="Salir",
                                message="¿Confirma que desea salir de la aplicación?",
                                detail="Clic en Sí para salir")
    if salir:
        Serializador.serializarTodo()
        ventana.destroy()
        Serializador.serializarTodo()


# La siguiente función es llamada cuando se presiona el botón Ingresar de la ventana de Inicio. Por medio de esta
# el usuario puede acceder a la ventana de Usuario, desde donde puede hacer uso de las distintas funcionalidades.
def ingresar():
    ocultarTodo()
    borrarTodo()
    ventanaUsuario.pack()
    ventana["menu"] = menuVentanaUsuario


# COMANDOS RELATIVOS A LA VENTANA DEL USUARIO:


def agregarSector():
    ocultarTodo()
    borrarTodo()
    ventanaAgregarSector.pack()


def agregarLocal():
    ocultarTodo()
    borrarTodo()
    ventanaAgregarLocal.pack()

def registrarDuenho():
    ocultarTodo()
    borrarTodo()
    ventanaRegistrarDuenho.pack()

def registrarCliente():
    ocultarTodo()
    borrarTodo()
    ventanaRegistrarCliente.pack()

def registrarCodeudor():
    ocultarTodo()
    borrarTodo()
    ventanaRegistrarCodeudor.pack()

def alquilarLocal():
    ocultarTodo()
    borrarTodo()
    ventanaAlquilarLocal.pack()

def mostrarLocalesDesocupados():
    ocultarTodo()
    borrarTodo()
    ventanaMostrarLocalesDesocupados.pack()

def mostrarLocalesOcupados():
    ocultarTodo()
    borrarTodo()
    ventanaMostrarLocalesOcupados.pack()

def ventanaMostrarListadoContratosVigentes():
    ocultarTodo()
    borrarTodo()
    ventanaMostrarListadoContratosVigentes.pack()


# La siguiente función es llamada cuando se accede al menú de "Aplicación" de la ventana de Usuario. Por medio de esta
# se muestra al usuario NUEVAMENTE la descripción de la aplicación.
def aplicacion():
    descripcion = "En el sistema gestor de zoológico podrá administrar todo lo que tiene que ver con su zoológico: Calcular las ganancias del día; pagar a sus empleados; llevar conteo de los visitantes, de los animales, de los empleados, de las especies, y de los hábitats; Cuidar y curar a los animales; Enviar personal a alimentar a los animales; Enviar personal a limpiar los hábitats "
    messagebox.showinfo(title="Información básica de la aplicación",
                        message=descripcion)


# La siguiente función es llamada cuando se accede al menú de "Salir" de la ventana de Usuario. Por medio de esta
# se regresa a la ventana de Inicio.
def salirUsuario():
    salir = messagebox.askyesno(title="Salir",
                                message="¿Confirma que desea regresar a la ventana de inicio?",
                                detail="Clic en Sí para regresar")
    if salir:
        ocultarTodo()
        ventanaInicio.pack()
        ventana["menu"] = menuVentanaInicio


# La siguiente función es llamada cuando se accede al menú de "Acerca De" de la ventana de Usuario. Por medio de esta
# se muestra al usuario los autores de la aplicación.
def ayuda():
    autores = """Autores:

- Daniel Felipe Puentes Rocha
    """
    messagebox.showinfo(title="Acerca de la aplicación",
                        message=autores)


# COMPONENTES DEL MENÚ DE LA VENTANA DE INICIO:

menuVentanaInicio = Menu(ventana, font="Helvetica 11 bold", fg="red")
menuInicio = Menu(menuVentanaInicio, font="Helvetica 11", )
menuVentanaInicio.add_cascade(menu=menuInicio, label="Inicio")
menuInicio.add_command(label="Descripción", command=descripcion)
menuInicio.add_command(label="Salir", command=salirInicio)
ventana["menu"] = menuVentanaInicio

# COMPONENTES DEL MENÚ DE LA VENTANA DEL USUARIO:

menuVentanaUsuario = Menu(ventana, font="Helvetica 11 bold")
menuArchivo = Menu(menuVentanaUsuario, font="Helvetica 11")
menuVentanaUsuario.add_cascade(menu=menuArchivo, label="Archivo")
menuArchivo.add_command(label="Aplicación", command=aplicacion)
menuArchivo.add_command(label="Salir", command=salirUsuario)
menuProcesos = Menu(menuVentanaUsuario, font="Helvetica 11")
menuVentanaUsuario.add_cascade(menu=menuProcesos, label="Procesos y consultas")
menuProcesos.add_command(label="Agregar sector a la plaza", command=agregarSector)
menuProcesos.add_command(label="Agregar local a la plaza", command=agregarLocal)
menuProcesos.add_command(label="Registrar duenho", command=registrarDuenho)
menuProcesos.add_command(label="Registrar cliente", command=registrarCliente)
menuProcesos.add_command(label="Registrar codeudor", command=registrarCodeudor)
menuProcesos.add_command(label="Alquilar local", command=alquilarLocal)
menuProcesos.add_command(label="Mostrar locales desocupados", command=mostrarLocalesDesocupados)
menuProcesos.add_command(label="Mostrar locales ocupados", command=mostrarLocalesOcupados)
menuProcesos.add_command(label="Mostrar listado de contratos vigentes", command=ventanaMostrarListadoContratosVigentes)

menuAyuda = Menu(menuVentanaUsuario, font="Helvetica 11")
menuVentanaUsuario.add_cascade(menu=menuAyuda, label="Ayuda")
menuAyuda.add_command(label="Acerca de", command=ayuda)

# COMPONENTES DE LA VENTANA DE INICIO:

# Se crean cada uno de los frames especificados para la ventana de Inicio.
ventanaInicio = Frame()
P1 = Frame(master=ventanaInicio, highlightbackground="black", highlightthickness=2)
P2 = Frame(master=ventanaInicio, highlightbackground="black", highlightthickness=2)
P3 = Frame(master=P1, highlightbackground="black", highlightthickness=2)
P4 = Frame(master=P1, highlightbackground="black", highlightthickness=2)
P5 = Frame(master=P2, highlightbackground="black", highlightthickness=2)
P6 = Frame(master=P2, highlightbackground="black", highlightthickness=2)

# Se crea el Label de bienvenida a la aplicación.
Saludo = Label(master=P3, text="""Bienvenido al sistema de plaza de mercado.""", font="Helvetica 11")
# Se crea el botón "Ingresar", que al ser presionado permitirá al usuario acceder a las funcionalidades.
Ingreso = Button(master=P4, text="Ingresar", font="Helvetica 11 bold",
                 bg="grey", fg="white", borderwidth=5, relief="groove",
                 command=ingresar)

# Se crea el título para las hojas de vida de los autores.
TituloVida = Label(master=P5, text="Breve biografía de los autores",
                   font="Helvetica 11 bold")
# Se crea el Label para el texto de las hojas de vida de los autores.
CuerpoVida = Label(master=P5, text=VidaYo, font="Helvetica 10",
                   anchor=W)

# Se crea el título para las funcionalidades de la aplicación.
FotoAnimal = (Image.open("Imagenes/Plaza/1.jpg")).resize((400, 400), Image.ANTIALIAS)
FotoAnimal = ImageTk.PhotoImage(FotoAnimal)
Foto1 = (Image.open("Imagenes/Yo/1.jpg")).resize((200, 200), Image.ANTIALIAS)
Foto1 = ImageTk.PhotoImage(Foto1)
Foto2 = (Image.open("Imagenes/Yo/2.jpg")).resize((200, 200), Image.ANTIALIAS)
Foto2 = ImageTk.PhotoImage(Foto2)
Foto3 = (Image.open("Imagenes/Yo/3.jpg")).resize((200, 200), Image.ANTIALIAS)
Foto3 = ImageTk.PhotoImage(Foto3)
Foto4 = (Image.open("Imagenes/Yo/4.jpg")).resize((200, 200), Image.ANTIALIAS)
Foto4 = ImageTk.PhotoImage(Foto4)

# Se crea el título para las funcionalidades de la aplicación.
LabelFotoAnimal = Label(master=P4, image=FotoAnimal, borderwidth=5, relief="ridge")
LabelFoto1 = Label(master=P6, image=Foto1, borderwidth=5, relief="ridge")
LabelFoto1.grid(column=0, row=0, padx=3, pady=3)
LabelFoto2 = Label(master=P6, image=Foto2, borderwidth=5, relief="ridge")
LabelFoto2.grid(column=1, row=0, padx=3, pady=3)
LabelFoto3 = Label(master=P6, image=Foto3, borderwidth=5, relief="ridge")
LabelFoto3.grid(column=0, row=1, padx=3, pady=3)
LabelFoto4 = Label(master=P6, image=Foto4, borderwidth=5, relief="ridge")
LabelFoto4.grid(column=1, row=1, padx=3, pady=3)

# Se visualizan todos los elementos anteriormente creados.
ventanaInicio.pack()
P1.pack(side=LEFT, fill=BOTH, padx=5, pady=5)
P2.pack(side=RIGHT, fill=BOTH, padx=5, pady=5)
P3.pack(side=TOP, fill=BOTH, padx=5, pady=5)
P4.pack(side=BOTTOM, fill=BOTH, padx=5, pady=5)
P5.pack(side=TOP, fill=BOTH, padx=5, pady=5)
P6.pack(side=BOTTOM, fill=BOTH, padx=5, pady=5)
Saludo.pack(padx=5, pady=5)
Ingreso.pack(side=BOTTOM, padx=5, pady=5)
LabelFotoAnimal.pack(side=TOP, padx=10, pady=10)
TituloVida.pack(padx=5, pady=5)
CuerpoVida.pack(padx=5, pady=5)

# Se asignan los comandos para cambiar de hoja de vida y de imagen relacionada a la aplicación.
CuerpoVida.bind("<Button-1>", cambiarVida)
LabelFotoAnimal.bind("<Enter>", cambiarImagen)

# COMPONENTES DE LA VENTANA DEL USUARIO:

# Se crean cada uno de los frames especificados para la ventana del Usuario.
tutorial = """¡Bienvenido al Sistema de la plaza de la ciudad!

Reglas de cálculo:
-	El código de cada sector es la cantidad de sectores que había en el momento de ser creado más uno.
-	El código de cada local es la cantidad de locales que había en el momento de ser creado más uno.
-	El número de cada contrato es la cantidad de locales que había en el momento de ser creado más uno.
-	El monto mensual de cada contrato se calcula sumando el precio base\ndado por el dueño más el tamaño del local por el precio base\npor metro cuadrado del sector donde está ubicado, y a ese valor se le añade el 20% por administración.
-	Si el local tiene techo, el valor en el monto inicial de los contratos aumenta un porcentaje establecido.
-	Si el local tiene cámara refrigerante, el valor en el monto inicial de los contratos aumenta un porcentaje establecido.

Reglas de estructura y relaciones:
-	Los clientes, los dueños, los codeudores, los contratos y los sectores hacen parte de la plaza.
-	Los locales hacen parte de un determinado sector de la plaza.
-	Un local está relacionado con un dueño.
-	Un cliente está relacionado con dos codeudores.
-	Un contrato está relacionado con un cliente

Reglas de flujo:
-	Para agregar locales deben existir sectores y dueños.
-	Para agregar clientes deben existir al menos 2 codeudores.
-	Para mostrar el historial de alquiles de un local deben existir contratos.
-	Para alquilar un local deben existir locales y clientes.
"""

# Se define el mensaje que aparecerá cuando se accede a la ventana de Usuario desde la ventana de Inicio.
ventanaUsuario = Frame()
tituloInfo = Label(master=ventanaUsuario, text="¿Cómo usar esta aplicación y qué puede hacer con ella?",
                   font="Helvetica 11 bold")
info = Label(master=ventanaUsuario, text=tutorial, font="Helvetica 10")
tituloInfo.pack(fill=BOTH, padx=5, pady=5)
info.pack(fill=BOTH, padx=5, pady=5)

ventanaAgregarSector = AgregarSector()
ventanaAgregarSector.pack_forget()

ventanaAgregarLocal = AgregarLocal()
ventanaAgregarLocal.pack_forget()

ventanaRegistrarDuenho = RegistrarDuenho()
ventanaRegistrarDuenho.pack_forget()

ventanaRegistrarCliente = RegistrarCliente()
ventanaRegistrarCliente.pack_forget()

ventanaRegistrarCodeudor = RegistrarCodeudor()
ventanaRegistrarCodeudor.pack_forget()

ventanaAlquilarLocal = AlquilarLocal()
ventanaAlquilarLocal.pack_forget()

ventanaMostrarLocalesDesocupados = MostrarLocalesDesocupados()
ventanaMostrarLocalesDesocupados.pack_forget()

ventanaMostrarLocalesOcupados = MostrarLocalesOcupados()
ventanaMostrarLocalesOcupados.pack_forget()

ventanaMostrarListadoContratosVigentes = MostrarListadoContratosVigentes()
ventanaMostrarListadoContratosVigentes.pack_forget()

# Se crea la sección de opciones de la aplicación.
style = ttk.Style()
style.theme_use("default")
style.map("Treeview")
style.configure("Treeview.Heading", font="Helvetica 10 bold")
style.configure("Treeview", font="Helvetica 10")

ventana.mainloop()

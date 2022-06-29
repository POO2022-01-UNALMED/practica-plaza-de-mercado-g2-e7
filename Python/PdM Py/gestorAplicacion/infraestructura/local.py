class Local():
    locales = []

    def __init__(self, codigo=0, techo=0, camaraRefrigerante=0, tamanho=0, precioBase=0, cedulaDuenho=0, ocupado=0):
        self._codigo = codigo
        self._techo = techo
        self._camaraRefrigerante = camaraRefrigerante
        self._tamanho = tamanho
        self._precioBase = precioBase
        self._cedulaDuenho = cedulaDuenho
        self._ocupado = ocupado
        Local.locales.append(self)

    def retornarInformacion(self):
        if self._techo :
            techo = "SI"
        else:
            techo = "No"
        if self._camaraRefrigerante:
            camaraRefrigerante = "Si"
        else:
            camaraRefrigerante = "No"
        if self._ocupado:
            ocupado = "Si"
        else:
            ocupado = "No"
        return "Codigo: "+ str(self._codigo) + "\nTiene techo: " + techo + "\nTiene camara refrigerante: " + camaraRefrigerante + \
               "\nTamaño " + str(self._tamanho) + "\nPrecio base dado por el dueño: " + str(self._precioBase) +\
               "\nCedula del dueño: " + str(self._cedulaDuenho) + "\nOcupado " + ocupado

    @classmethod
    def getLocales(cls):
        return cls.locales

    @classmethod
    def setLocales(cls, locales):
        cls.locales = locales

    def getCodigo(self):
        return self._codigo
    def setCodigo(self, codigo):
        self._codigo = codigo

    def getTecho(self):
        return self._techo
    def setTecho(self, techo):
        self._techo = techo

    def getCamaraRefrigerante(self):
        return self._camaraRefrigerante
    def setCamaraRefrigerante(self, cf):
        self._camaraRefrigerante = cf

    def isOcupado(self):
        return self._ocupado

    def getTamanho(self):
        return  self._tamanho
    def setTamanho(self, tamanho):
        self._tamanho = tamanho
    def getPrecioBase(self):
        return  self._precioBase
    def setPrecioBase(self, pb):
        self._precioBase= pb

    def getCedulaDuenho(self):
        return self._cedulaDuenho
    def setCedulaDuenho(self, cd):
        self._cedulaDuenho = cd
    def getOcupado(self):
        return self._ocupado
    def setOcupado(self, ocupado):
        self._ocupado= ocupado

# TAMAN-KEET-2020
Official code of team 3933 for the 2020 INFINITE RECHARGE season.
Bienvenidos a nuestro repositorio para la temporada 2020. Aquí subiremos, probaremos y administraremos el código de la temporada. Recuerden pueden hacer los branchs que quieran, pero, sólo pueden hacer commit con permiso de mentor :)

# To-do list
Por favor, una vez que completen una tarea comentenlo con su nombre, por ejemplo: \n  
Crear pid de drivetrain -Alex, colaborador (si alguien les ayudo).

     Urge:
          Arreglar problema con el elevador,  ??
          Pasar el drivetrain de talones a spark max
          Pasar variables de ids a ids correctos
          Crear el MoveDisk.java //Disco de colores
          Terminar imlementacion de camara
               Crear codigo de la camara
               Crear filtros de colores para solamente ver el verde 
               Pasar lineas de los strips para mejor alineacion
               Programar camara para solamente mandar valores al codigo
          Crear elevador inteligente [falta que electronica ponga los botones]
          Hacer que el intake interno
          Hacer que sirva de forma automatica [faltan botones] ??
          Implementar un ReverseIntake
          Probar ciertas variables ??
          DIVERTIRSE -Todos

          Crear un diccionario de metodos para programacion del robot "ver con jorge detalles"
     Implementaciones no necesarias:
          Hacer que el drivetrain sea field oriented
# Lineamientos del código
Al inicio de la clase, escriban su nombre (Quién está editando).

Nombres de clases con mayúsculas

Nombres de paquetes con minúsculas

Llamen a los métodos por un verbo

Comenten las funciones los métodos.

Si utilizan un botón, pongan en constantes/Controles/ControlDeseado para qué se está utilizando.

NO dupliquen objetos

# Recordatorios:
Robot.java es el sistema general. Ahí se van a construir todos los subsistemas.

Puedes poner métodos dentro de otros métodos.

llamas a los objetos y les asignas un nombre utilizando un .

Para que el objeto haga un método, los unes con un "." (punto).

Nombras los objetos y luego les asignan sus métodos

Llamar constructor:

     Clase NombreDelObjeto = new Clase() asignado en robot.java del objeto- y parámetro (lo que dice el método+ llamas a subsistemas.subsistema) 

Dentro de parámetros puedes poner objetos para que dependa del otro.

Nuestro robot tienen subsistemas.

Para controlar la sensibilidad puedes:

     Multiplicar por una constante menor a uno.
     O elevar al cuadrado la sensibilidad.
     Se hace para no descontrolar al robot .

Método de Robotinit:

     Checklist de lo que necesita el robot para iniciar
     Aquí deben construir TODOS los subsistemas:




void no te regresa nada.

static si quieres que la instancia del objeto/variable sea la misma para todos los de su clase.


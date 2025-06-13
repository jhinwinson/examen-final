BatallaElemental Arena
----Descripción del Proyecto-----
BatallaElemental Arena es un videojuego de simulación por turnos desarrollado en Java. Dos jugadores compiten entre sí eligiendo personajes con diferentes tipos elementales: fuego, agua, rayo o viento.

En esta versión mejorada, cada jugador selecciona dos personajes, los cuales se almacenan en un arreglo (array). Luego, el jugador elige con cuál de sus dos personajes quiere luchar. Esto permite una mejor organización de los personajes y prepara el sistema para posibles futuras expansiones, como peleas en equipo.

-----Características------
Elección de dos personajes por jugador (usando arreglos).

Cada personaje pertenece a un tipo elemental: Fuego, Agua, Rayo o Viento.

Sistema de turnos: cada jugador puede atacar o curarse en su turno.

Control de vida (máxima 100 puntos).

Uso de herencia, encapsulamiento, arreglos, condicionales, y bucles.

Interacción sencilla por consola usando Scanner.

-----Cómo Jugar-------
Al iniciar, cada jugador debe:

Ingresar un nombre para dos personajes.

Elegir un tipo elemental para cada uno.

Luego, se les muestra su equipo y deben elegir con cuál personaje desean luchar.

Se inicia una batalla por turnos.

Opción 1: Atacar (usa el poder del personaje como daño).

Opción 2: Curarse (recupera 20 puntos de vida).

El juego termina cuando uno de los personajes llega a 0 de vida.

---------Integrantes del grupo y roles------------------
Jhinwinson osorio– Programación base del sistema de combate y clases hijas

Maylon riaño – Implementación del uso de arrays y lógica de elección por personaje

Dario Chacon – Validaciones de entrada y control de errores con try/catch

Juan David – Documentación y pruebas del sistema

Diagrama de Clases UML para BatallaElemental Arena

----------------------------------------------------------------------------------------
                |    Personaje       |  
                +--------------------+
                | - nombre: String   |
                | - vida: int        |
                | - poder: int       |
                +--------------------+
                | +Personaje(nombre: String, poder: int) |
                | +getNombre(): String      |
                | +getVida(): int           |
                | +getPoder(): int          |
                | +recibirDanio(danio: int): void |
                | +curar(): void            |
                | +atacar(enemigo: Personaje): void |
                +--------------------+
                         /|\ 
                          |
      -----------------------------------------------
      |         |            |              |       |
+-------------+ +-------------+ +-------------+ +--------------+
| TipoFuego   | | TipoAgua    | | TipoRayo    | | TipoViento   |
+-------------+ +-------------+ +-------------+ +--------------+
| +TipoFuego(nombre: String)  |
| +TipoAgua(nombre: String)   |
| +TipoRayo(nombre: String)   |
| +TipoViento(nombre: String) |
+-----------------------------+

                +---------------------+
                |      Batalla        |
                +---------------------+
                | - leer: Scanner     |
                +---------------------+
                | +iniciarBatalla(j1: Personaje, j2: Personaje): void |
                | +realizarTurno(atacante: Personaje, defensor: Personaje): void |
                +---------------------+

                +-----------------------------+
                |      BatallaElemental       |
                +-----------------------------+
                | +main(args: String[]): void |
                | +elegirPersonaje(nombre: String): Personaje |
                | +mostrarOpciones(equipo: Personaje[]): void |
                +-----------------------------+

----------------------------------------------------------------------------------------------------

1. Recolección de Requisitos

El proyecto busca desarrollar un videojuego educativo tipo RPG por turnos, enfocado en el aprendizaje de programación orientada a objetos. Los requisitos funcionales incluyen selección de personajes elementales (fuego, agua, rayo, viento), combate por turnos, y una interfaz por consola; mientras que los no funcionales priorizan la modularidad del código, usabilidad y posibilidad de escalarlo a versiones futuras.

2. Análisis del Sistema

Se identifican las principales entidades del sistema: los personajes como clases con atributos comunes y especializaciones por tipo, la clase Batalla que gestiona la lógica del juego y la clase principal que orquesta el flujo. Las relaciones de herencia y colaboración entre clases aseguran una estructura clara y extensible, permitiendo un control eficiente de los turnos y las acciones de los jugadores.

3. Diseño del Sistema

El diseño se basa en principios de programación orientada a objetos, aplicando herencia para los tipos de personajes y encapsulamiento para proteger atributos como vida y poder. Se diseña un diagrama UML que representa visualmente las clases y relaciones, y se estructura la lógica del combate en una clase separada para mantener el código limpio y escalable.

4. Implementación

La solución se implementa en Java utilizando Scanner para la entrada del usuario y estructuras condicionales para controlar las acciones de ataque o curación. Las clases están organizadas de forma modular: Personaje como clase base, subclases para los tipos, Batalla para la mecánica de juego, y una clase main para manejar la experiencia del jugador desde el inicio hasta el final del combate.

5. Pruebas

Se realizan pruebas funcionales para validar la correcta ejecución de ataques, curaciones y turnos, así como pruebas de validación para entradas no numéricas o fuera de rango. También se evalúan condiciones límite como que la vida no baje de 0 ni suba de 100, garantizando la integridad del flujo de juego y la estabilidad general del sistema.

6. Despliegue

El juego se ejecuta inicialmente en consola  Esta versión es la vercion basica

7. Mantenimiento

Se contempla la corrección de errores reportados por usuarios y la expansión del juego con nuevos tipos de personajes, habilidades, inteligencia artificial para jugar contra la máquina, y mejoras visuales. El mantenimiento también incluiría compatibilidad multiplataforma, documentación y modularidad para que otros puedan extender el proyecto fácilmente
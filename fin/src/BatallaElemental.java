import java.util.Scanner; // Importamos Scanner para leer lo que el jugador escriba

// Clase base: Personaje
class Personaje {
    private String nombre; // Nombre del personaje
    private int vida;      // Vida del personaje (inicia en 100)
    private int poder;     // Poder de ataque del personaje

    public Personaje(String nombre, int poder) { // Constructor para crear un personaje
        this.nombre = nombre;    // Asigna el nombre
        this.poder = poder;      // Asigna el poder
        this.vida = 100;         // Vida inicial es 100
    }

    public String getNombre() { return nombre; } // Devuelve el nombre
    public int getVida() { return vida; }        // Devuelve la vida
    public int getPoder() { return poder; }      // Devuelve el poder

    public void recibirDanio(int danio) { // Resta vida al recibir daño
        vida -= danio;                    // Quita la cantidad de daño
        if (vida < 0) vida = 0;           // Si la vida baja de 0, se deja en 0
    }

    public void curar() {         // Cura al personaje
        vida += 20;               // Suma 20 de vida
        if (vida > 100) vida = 100; // Vida máxima es 100
    }

    public void atacar(Personaje enemigo) { // Ataca a otro personaje
        enemigo.recibirDanio(poder);        // Le quita vida al enemigo
    }
}

// Clases hijas por tipo (heredan de Personaje)

class TipoFuego extends Personaje {
    public TipoFuego(String nombre) {
        super(nombre, 30); // Fuego tiene poder 30
    }
}

class TipoAgua extends Personaje {
    public TipoAgua(String nombre) {
        super(nombre, 25); // Agua tiene poder 25
    }
}

class TipoRayo extends Personaje {
    public TipoRayo(String nombre) {
        super(nombre, 35); // Rayo tiene poder 35
    }
}

class TipoViento extends Personaje {
    public TipoViento(String nombre) {
        super(nombre, 20); // Viento tiene poder 20
    }
}

// Clase Batalla que controla el combate
class Batalla {
    private Scanner leer = new Scanner(System.in); // Scanner para leer opción del jugador

    public void iniciarBatalla(Personaje jugador1, Personaje jugador2) {
        System.out.println("\n¡Comienza la batalla entre " + jugador1.getNombre() + " y " + jugador2.getNombre() + "!");

        while (jugador1.getVida() > 0 && jugador2.getVida() > 0) { // Sigue hasta que uno llegue a 0
            realizarTurno(jugador1, jugador2); // Turno jugador 1
            if (jugador2.getVida() <= 0) break; // Si jugador 2 muere, termina
            realizarTurno(jugador2, jugador1); // Turno jugador 2
        }

        // Mostrar quién ganó
        if (jugador1.getVida() > 0) {
            System.out.println("\n¡" + jugador1.getNombre() + " gana la batalla!");
        } else {
            System.out.println("\n¡" + jugador2.getNombre() + " gana la batalla!");
        }
    }

    private void realizarTurno(Personaje atacante, Personaje defensor) {
        System.out.println("\nTurno de " + atacante.getNombre());
        System.out.println("1. Atacar\n2. Curarse");

        int opcion = 1;

        try {
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(leer.nextLine()); // Leer opción
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se atacará por defecto."); // Si escriben algo mal, se ataca
        }

        switch (opcion) {
            case 1:
                atacante.atacar(defensor); // Ataca al defensor
                System.out.println(atacante.getNombre() + " atacó a " + defensor.getNombre());
                break;
            case 2:
                atacante.curar(); // Se cura
                System.out.println(atacante.getNombre() + " se curó 20 puntos de vida.");
                break;
            default:
                System.out.println("Opción no válida, se atacará por defecto.");
                atacante.atacar(defensor); // Si elige algo inválido, ataca
                break;
        }

        System.out.println(defensor.getNombre() + " tiene " + defensor.getVida() + " de vida."); // Muestra vida actual del defensor
    }
}

// Clase principal donde comienza el programa
public class BatallaElemental {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in); // Scanner para leer nombres y opciones

        System.out.println("=== BatallaElemental Arena ===");

        Personaje[] equipo1 = new Personaje[2]; // Dos personajes para jugador 1
        Personaje[] equipo2 = new Personaje[2]; // Dos personajes para jugador 2

        // Elegir personajes del jugador 1
        System.out.println("\nJugador 1, elige tus 2 personajes:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Nombre del personaje " + (i + 1) + ": ");
            String nombre = leer.nextLine();
            equipo1[i] = elegirPersonaje(nombre); // Crea personaje con ese nombre
        }

        // Elegir personajes del jugador 2
        System.out.println("\nJugador 2, elige tus 2 personajes:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Nombre del personaje " + (i + 1) + ": ");
            String nombre = leer.nextLine();
            equipo2[i] = elegirPersonaje(nombre);
        }

        // Elegir con cuál personaje luchar (Jugador 1)
        System.out.println("\nJugador 1, elige con qué personaje pelearás:");
        mostrarOpciones(equipo1);
        int opcion1 = Integer.parseInt(leer.nextLine());
        Personaje jugador1 = equipo1[opcion1 - 1]; // Guarda la elección

        // Elegir con cuál personaje luchar (Jugador 2)
        System.out.println("\nJugador 2, elige con qué personaje pelearás:");
        mostrarOpciones(equipo2);
        int opcion2 = Integer.parseInt(leer.nextLine());
        Personaje jugador2 = equipo2[opcion2 - 1];

        // Comienza la batalla
        Batalla batalla = new Batalla();
        batalla.iniciarBatalla(jugador1, jugador2);

        leer.close(); // Cerramos el Scanner al final del juego
    }

    // Mostrar los personajes elegidos con nombre y tipo
    public static void mostrarOpciones(Personaje[] equipo) {
        for (int i = 0; i < equipo.length; i++) {
            System.out.println((i + 1) + ". " + equipo[i].getNombre() + " (" + equipo[i].getClass().getSimpleName() + ")");
        }
        System.out.print("Tu opción: ");
    }

    // Permite elegir qué tipo de personaje crear
    public static Personaje elegirPersonaje(String nombre) {
        Scanner leer = new Scanner(System.in);

        System.out.println("Elige tipo de naturaleza para " + nombre);
        System.out.println("1. Fuego\n2. Agua\n3. Rayo\n4. Viento");

        int opcion = 1;
        try {
            System.out.print("Tu opción: ");
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se usará Fuego por defecto.");
        }

        // Crea el personaje según la opción elegida
        switch (opcion) {
            case 1: return new TipoFuego(nombre);
            case 2: return new TipoAgua(nombre);
            case 3: return new TipoRayo(nombre);
            case 4: return new TipoViento(nombre);
            default: return new TipoFuego(nombre);
        }
    }
}

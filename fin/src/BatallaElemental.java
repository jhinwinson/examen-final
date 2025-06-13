import java.util.Scanner;


// Clase base: Personaje

class Personaje {
    private String nombre;
    private int vida;
    private int poder;

    public Personaje(String nombre, int poder) {
        this.nombre = nombre;
        this.poder = poder;
        this.vida = 100;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getPoder() { return poder; }

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) vida = 0;
    }

    public void curar() {
        vida += 20;
        if (vida > 100) vida = 100;
    }

    public void atacar(Personaje enemigo) {
        enemigo.recibirDanio(poder);
    }
}


// Clases hijas por tipo

class TipoFuego extends Personaje {
    public TipoFuego(String nombre) {
        super(nombre, 30);
    }
}

class TipoAgua extends Personaje {
    public TipoAgua(String nombre) {
        super(nombre, 25);
    }
}

class TipoRayo extends Personaje {
    public TipoRayo(String nombre) {
        super(nombre, 35);
    }
}

class TipoViento extends Personaje {
    public TipoViento(String nombre) {
        super(nombre, 20);
    }
}


// Clase Batalla

class Batalla {
    private Scanner leer = new Scanner(System.in);

    public void iniciarBatalla(Personaje jugador1, Personaje jugador2) {
        System.out.println("\n¡Comienza la batalla entre " + jugador1.getNombre() + " y " + jugador2.getNombre() + "!");

        while (jugador1.getVida() > 0 && jugador2.getVida() > 0) {
            realizarTurno(jugador1, jugador2);
            if (jugador2.getVida() <= 0) break;
            realizarTurno(jugador2, jugador1);
        }

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
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se atacará por defecto.");
        }

        switch (opcion) {
            case 1:
                atacante.atacar(defensor);
                System.out.println(atacante.getNombre() + " atacó a " + defensor.getNombre());
                break;
            case 2:
                atacante.curar();
                System.out.println(atacante.getNombre() + " se curó 20 puntos de vida.");
                break;
            default:
                System.out.println("Opción no válida, se atacará por defecto.");
                atacante.atacar(defensor);
                break;
        }

        System.out.println(defensor.getNombre() + " tiene " + defensor.getVida() + " de vida.");
    }
}


// Clase principal Main

public class BatallaElemental {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        System.out.println("=== BatallaElemental Arena ===");

        // Arreglos para guardar 2 personajes por jugador
        Personaje[] equipo1 = new Personaje[2];
        Personaje[] equipo2 = new Personaje[2];

        // Registro jugador 1
        System.out.println("\nJugador 1, elige tus 2 personajes:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Nombre del personaje " + (i + 1) + ": ");
            String nombre = leer.nextLine();
            equipo1[i] = elegirPersonaje(nombre);
        }

        // Registro jugador 2
        System.out.println("\nJugador 2, elige tus 2 personajes:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Nombre del personaje " + (i + 1) + ": ");
            String nombre = leer.nextLine();
            equipo2[i] = elegirPersonaje(nombre);
        }

        // Elegir personaje de cada equipo
        System.out.println("\nJugador 1, elige con qué personaje pelearás:");
        mostrarOpciones(equipo1);
        int opcion1 = Integer.parseInt(leer.nextLine());
        Personaje jugador1 = equipo1[opcion1 - 1];

        System.out.println("\nJugador 2, elige con qué personaje pelearás:");
        mostrarOpciones(equipo2);
        int opcion2 = Integer.parseInt(leer.nextLine());
        Personaje jugador2 = equipo2[opcion2 - 1];

        // Inicia batalla
        Batalla batalla = new Batalla();
        batalla.iniciarBatalla(jugador1, jugador2);

        leer.close(); // Cerramos el Scanner
    }

    // Muestra opciones del arreglo
    public static void mostrarOpciones(Personaje[] equipo) {
        for (int i = 0; i < equipo.length; i++) {
            System.out.println((i + 1) + ". " + equipo[i].getNombre() + " (" + equipo[i].getClass().getSimpleName() + ")");
        }
        System.out.print("Tu opción: ");
    }

    // Crear personaje según tipo
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

        switch (opcion) {
            case 1: return new TipoFuego(nombre);
            case 2: return new TipoAgua(nombre);
            case 3: return new TipoRayo(nombre);
            case 4: return new TipoViento(nombre);
            default: return new TipoFuego(nombre);
        }
    }
}

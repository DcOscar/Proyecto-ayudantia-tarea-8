/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

public class InfinityGame {

    private ArrayList<Jugador> jugadores;

    public InfinityGame() {
        this.jugadores = new ArrayList<Jugador>();
    }

    public int lanzarDados() {
        int valorDados = 0;
        for (int contador = 0; contador < 2; contador++) {
            Dado dado = new Dado();
            valorDados += dado.getValorDado();
        }

        return valorDados;
    }

    private void meditar(int indice, int largoTablero) {
        int mover;
        int cantidadMeditar;
        cantidadMeditar = this.jugadores.get(indice).getMeditar();
        if (cantidadMeditar == 0) {
            this.jugadores.get(indice).setSalud(-1);
            System.out.println("No le quedan meditaciones");
        } else {
            do {
                System.out.println("Puede moverse dentro de 3 casillas a la redonda, indique cuantas casillas se movera");
                mover = leerDato();
            } while (mover > 3 || mover < -3);
            this.jugadores.get(indice).setMeditar(mover);
            this.jugadores.get(indice).setSalud(1);
        }
        extremosPosiciones(indice, largoTablero);
        extremosSalud();
    }

    private int leerCantidadJugadores() {
        int cantidadJugadores;
        do {
            cantidadJugadores = leerDato();
        } while (cantidadJugadores < 1);
        return cantidadJugadores;
    }

    private void casillaFinal(int indice, int largoTablero, int turno) {
        int posicion;
        posicion = this.jugadores.get(indice).getPosicion();
        if (posicion == largoTablero) {
            System.out.println("Ganó el jugador n°" + indice + 1 + " en el turno " + turno);
        }
        System.exit(0);

    }

    private void agregarJugadores(int cantidadJugadores) {
        String nombreJugador;
        int opcionJugador;
        for (int i = 0; i < cantidadJugadores; i++) {
            System.out.println("Indique que tipo de jugador desea");
            System.out.println("1- Guerrero");
            System.out.println("2-Mago");
            do {
                opcionJugador = leerDato();
            } while (opcionJugador >= 3 && opcionJugador <= 0);
            System.out.println("Indique el nombre del jugador");
            nombreJugador = asignarNombre();
            if (opcionJugador == 1) {
                this.jugadores.add(new Guerrero(nombreJugador));
            } else {
                this.jugadores.add(new Mago(nombreJugador));
            }

        }
    }

    private String asignarNombre() {
        String nombreJugador;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Diga un nombre de jugador");
        nombreJugador = teclado.next();
        return nombreJugador;
    }

    private void mostrarJugadores() {
        String nombre;
        int salud;
        int posicion;
        for (int i = 0; i < this.jugadores.size(); i++) {
            nombre = this.jugadores.get(i).getNombre();
            salud = this.jugadores.get(i).getSalud();
            posicion = this.jugadores.get(i).getPosicion();
            System.out.println((i + 1) + "- " + nombre + ", salud: " + salud + ", posición: " + (posicion + 1));
        }
    }

    private void casillaDesafio(int indice, int largoTablero) {
        int posibilidad = (int) (Math.random() * 2); //"0" se puede avanzar o retroceder, "1" gana o pierde vida
        int azar = (int) (Math.random() * 2); // 0= perder vida o retroceder, 1 ganar vida o avanzar
        int obtenerSalud;
        int perderSalud;
        int retroceder;
        int avanzar;
        System.out.println("Entro en una casilla desafio");
        if (posibilidad == 0) {
            avanzar = (int) (Math.random() * 5 + 1);
            retroceder = -avanzar;
            if (azar == 0) {
                this.jugadores.get(indice).setPosicion(retroceder);

            } else if (azar == 1) {
                this.jugadores.get(indice).setPosicion(avanzar);
            }
        } else if (posibilidad == 1) {
            obtenerSalud = (int) (Math.random() * 4 + 1);
            perderSalud = -obtenerSalud;
            if (azar == 0) {
                for (int i = 0; i < this.jugadores.size(); i++) {
                    this.jugadores.get(i).setSalud(perderSalud);
                }
                this.jugadores.get(indice).setSalud(obtenerSalud);
            } else if (azar == 1) {
                for (int i = 0; i < this.jugadores.size(); i++) {
                    this.jugadores.get(i).setSalud(obtenerSalud);
                }
                this.jugadores.get(indice).setSalud(perderSalud);
            }

        }
        extremosSalud();
        extremosPosiciones(indice, largoTablero);
    }

    private void extremosSalud() {
        int saludMax;
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getSalud() > this.jugadores.get(i).getSaludMax()) {
                saludMax= this.jugadores.get(i).getSaludMax();
                this.jugadores.get(i).setSaludExtremos(saludMax);
            }

            if (this.jugadores.get(i).getSalud() < 0) {

                this.jugadores.get(i).setSaludExtremos(0);
                System.out.println("El jugador n°" + i + 1 + "a perdido todas sus vidas, por lo tanto dejará de jugar");
                this.jugadores.remove(i);
            }
        }
    }

    private void extremosPosiciones(int indice, int largoTablero) {

        if (this.jugadores.get(indice).getPosicion() > largoTablero) {
            this.jugadores.get(indice).setPosicionExtremos(largoTablero);
        }

        if (this.jugadores.get(indice).getPosicion() < 0) {
            this.jugadores.get(indice).setPosicionExtremos(0);
        }
    }

    public void casillaPortal(ArrayList<Character> casillas, int largoTablero, int indice, int posicion) {
        ArrayList<Integer> posicionPortales = new ArrayList<Integer>();
        int portalAzar;
        int aux = -1;
        System.out.println("Entro en una casilla portal");
        for (int i = 0; i < largoTablero; i++) {
            if (casillas.get(i).equals('p')) {  //cada vez que encuentra una casilla portal, agrega su posicion a un array
                posicionPortales.add(i);
                aux++;
                if (i == posicion) {
                    posicionPortales.remove(aux);
                    aux--;
                }
            }   // se elimina la posicion actual, en la suposicion de que es una casilla portal 
        }
        portalAzar = (int) (Math.random() * posicionPortales.size());
        this.jugadores.get(indice).setPosicionExtremos(posicionPortales.get(portalAzar)); //se cambia de posicion a un portal aleatorio
        System.out.println("Se ha transportado a la casilla n°" + (this.jugadores.get(indice).getPosicion() + 1));

    }

    public void casillaSalud(int indice) {
        int azar = (int) (Math.random() * 2);
        int obtenerSalud = (int) (Math.random() * 3 + 1);
        int perderSalud = -obtenerSalud;
        System.out.println("Entro en una casilla salud");
        if (azar == 0) {
            this.jugadores.get(indice).setSalud(obtenerSalud); //obtiene entre 1 y 3 de vida
        } else {
            this.jugadores.get(indice).setSalud(perderSalud);

        }
        extremosSalud();
    }

    private int leerDato() {
        int verificarDato;
        Scanner leer = new Scanner(System.in);
        do {
            try {
                System.out.println("Ingrese un número válido");
                verificarDato = leer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("error, ingresa nuevamente un dato");
                leer.nextLine();
            }
        } while (true);

        return verificarDato;
    }

    private void elegirJugada(int indice, int largoTablero, Reliquia reliquia, Guardian guardian, int turno) {

        int opcion;
        int mover;
        System.out.println("1-Lanzar dados");
        System.out.println("2-Meditar");
        System.out.println("3- Habilidad especial");
        System.out.println("Elija una opción");
        guardian.furiaGuardian(jugadores);
        do {
            opcion = leerDato();
        } while (opcion < 0 && opcion > 4);
        if (opcion == 1) {
            mover = lanzarDados();
            if (mover == 12) {
                guardian.lanzarDadosGuardian(jugadores, indice, 3, turno);
            } else {
                guardian.lanzarDadosGuardian(jugadores, indice, 1, turno);
            }
            this.jugadores.get(indice).setPosicion(mover);
            extremosPosiciones(indice, largoTablero);
            reliquia.reliquiaDados(jugadores, indice, mover);
        } else if (opcion == 2) {
            meditar(indice, largoTablero);
        } else if (opcion == 3) {
              String clase = this.jugadores.get(indice).getTipo();
                        if (clase.equals("Guerrero")) {
                            Guerrero guerrero= (Guerrero) this.jugadores.get(indice);
                            guerrero.enfurecerse(this.jugadores);
                        } else {
                            Mago mago = (Mago) this.jugadores.get(indice);
                            mago.concentracion(guardian);
                        }
           
          
        }
        this.jugadores.get(indice).estadoJugador();

    }

    private void casillaOpcion(ArrayList<Character> casillas, int indice, int largoTablero, int posicion, Reliquia reliquia, int turno) {

        if (posicion >= casillas.size()) {
            posicion = casillas.size();
        }

        switch (casillas.get(posicion)) {
            case 'p':
                casillaPortal(casillas, largoTablero, indice, posicion);
                break;
            case 's':
                casillaSalud(indice);
                reliquia.reliquiaSalud(this.jugadores, indice);
                break;
            case 'd':
                casillaDesafio(indice, largoTablero);
                break;
            case 'b':
                System.out.println("Entro en una casilla en blanco");
                break;
            case 'I':
                System.out.println("Está en la casilla inicial");
                break;
            case 'F':
                casillaFinal(indice, largoTablero, turno);
                break;

        }

    }

    public void bienvenida() {
        System.out.println("Bienvenidos a Infinity Game\n");
        System.out.println("Indique la cantidad de jugadores");
        generarJuego();
    }

    private void generarJuego() {
        Reliquia reliquia = new Reliquia();
        Tablero t = new Tablero();
        int cantidadJugadores;
        int largoTablero;
        int posicion;
        int turno = 1;
        ArrayList<Character> casillas = t.getCasillas();
        cantidadJugadores = leerCantidadJugadores();
        agregarJugadores(cantidadJugadores);
        Guardian guardian = new Guardian(this.jugadores);
        t.leerLargoTablero();
        t.generarTablero();
        largoTablero = t.getLargoTablero();
        mostrarJugadores();
        do {
            for (int indice = 0; indice < this.jugadores.size(); indice++) {
                t.mostrarTablero();
                System.out.println(jugadores.get(indice).getNombre() + " es su turno");
                elegirJugada(indice, largoTablero, reliquia, guardian, turno);
                posicion = jugadores.get(indice).getPosicion();
                casillaOpcion(casillas, indice, largoTablero, posicion, reliquia, turno);
                System.out.println("-----------------------------------------------------------");
            }
            Podio podio = new Podio(this.jugadores, turno);
            System.out.println("-----------------------------------------------------------");
            turno++;
        } while (true);
    }

}

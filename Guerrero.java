/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

public class Guerrero extends Jugador {

    private int furia;

    public Guerrero(String nombre) {
        super(nombre);
        super.salud = 20;
        super.saludMax = 20;
        this.furia = 5;
    }
    

    public void enfurecerse(ArrayList<Jugador> jugadores) {
        int jugadorFocus;
        if (this.furia == 0) {
            System.out.println("Se ha quedado sin furia");
        } else {
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println((i+1)+"- nombre: " + jugadores.get(i).getNombre() + ", salud: " + jugadores.get(i).getSalud() + ", posición: " + (jugadores.get(i).getPosicion()+1));
            }
            System.out.println("Indique a cual jugador le desea inflingir daño");
            jugadorFocus = leerIndiceJugador(jugadores);
            jugadores.get(jugadorFocus).setSalud(-1);
            System.out.println("La salud del jugador elegido ha quedado en " + jugadores.get(jugadorFocus).getSalud());
        }
    }

    private int leerIndiceJugador(ArrayList<Jugador> jugadores) {
        int indice;
        Scanner leer = new Scanner(System.in);
        do {
            try {
                do {
                    indice = leer.nextInt();

                } while (indice > jugadores.size() || indice <= 0);
                break;
            } catch (Exception e) {
                System.out.println("error, ingresa nuevamente un dato");
                leer.nextLine();
            }
        } while (true);
        indice--;
        return indice;
    }

    public String getTipo() {
        return "Guerrero";
    }

  
}

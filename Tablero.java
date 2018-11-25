/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

public class Tablero {

    private ArrayList<Character> tablero;
    private int largoTablero;

    public Tablero() {

        this.tablero = new ArrayList<Character>();
    }

    public void generarTablero() {
       char casillaAzar;
        for (int i = 0; i <= this.largoTablero; i++) {
        Casilla casilla= new Casilla();
        casillaAzar= casilla.getCasilla();
        this.tablero.add(casillaAzar);
        }
        Casilla casillaInicio= new Casilla();
          Casilla casillaFinal= new Casilla();
        this.tablero.set(0, casillaInicio.tipoCasillaInicio());
        this.tablero.set(this.largoTablero, casillaFinal.tipoCasillaFinal());
    }

    public void mostrarTablero() {
        System.out.println(this.tablero);
    }

    public void leerLargoTablero() {
        do {

            System.out.println("Ingrese el largo del tablero, minimo 20 casillas");
            this.largoTablero = leerDato();
        } while (this.largoTablero < 20);
        this.largoTablero--;

    }

    public void setLargoTablero(int cambio) {
        this.largoTablero = cambio;
    }

    private int leerDato() {
        int verificarDato;
        Scanner leer = new Scanner(System.in);
        do {
            try {
                verificarDato = leer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("error, ingresa nuevamente un dato");
                leer.nextLine();
            }
        } while (true);

        return verificarDato;
    }

    public int getLargoTablero() {
        return this.largoTablero;
    }

    public ArrayList getCasillas() {
        return this.tablero;
    }
}

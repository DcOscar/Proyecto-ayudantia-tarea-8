/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

/**
 *
 * @author Oscar
 */
public class Podio {

    private ArrayList<Integer> posicionPodio;
    private ArrayList<String> nombrePodio;

    public Podio(ArrayList<Jugador> jugadores, int turno) {
        this.posicionPodio = new ArrayList<Integer>();
        this.nombrePodio = new ArrayList<String>();
        for (int contador = 0; contador < jugadores.size(); contador++) {
            this.posicionPodio.add(jugadores.get(contador).getPosicion());
            this.nombrePodio.add(jugadores.get(contador).getNombre());
        }
        ordenarPodio();
        mostrarPodio();
        ganarJugador(turno);
    }
   private void ordenarPodio(){
   int posicionMenor;
   int posicionMayor;
   String nombreMenor;
   String nombreMayor;
   for(int x=0; x<=this.posicionPodio.size(); x++){
   for(int contador=0; contador<this.posicionPodio.size()-1; contador++){
    if(this.posicionPodio.get(contador)<this.posicionPodio.get(contador+1)){
    posicionMenor= this.posicionPodio.get(contador);
    posicionMayor= this.posicionPodio.get(contador+1);
    nombreMenor= this.nombrePodio.get(contador);
    nombreMayor= this.nombrePodio.get(contador+1);
    this.posicionPodio.set(contador, posicionMayor); //dato mayor
    this.posicionPodio.set(contador+1,posicionMenor); //dato menor
    this.nombrePodio.set(contador,nombreMayor);
    this.nombrePodio.set(contador+1, nombreMenor);
    }
    } 
    
}
}
private void mostrarPodio(){
for(int x=0; x<this.posicionPodio.size(); x++){
System.out.println((x+1)+"- "+this.nombrePodio.get(x)+", posición= "+(this.posicionPodio.get(x)+1));
}

}
private void ganarJugador(int turno){
    if(this.nombrePodio.size()==1){
    System.out.println("No quedan más jugadores con vida. Jugador "+this.nombrePodio.get(0)+" ha ganado en el turno "+turno);
        System.exit(0);         
}
    
}
}
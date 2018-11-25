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
public class Reliquia {
    private int probabilidad;
    public Reliquia(){
    }
    
 public void reliquiaDados(ArrayList<Jugador> jugadores, int indice, int valorDados) {
        
        if (valorDados == 12) {
            probabilidad = (int) (Math.random() * 1);  //Si obtiene el valor 1 el jugador tendrá su salud máxima
            if (probabilidad == 1) {
                jugadores.get(indice).setSaludExtremos(15);
                System.out.println("Su vida se a restaurado su vida al valor máximo gracias a una reliquia!!");
            }
        } else {
            probabilidad = (int) (Math.random() * 100) + 1; // si obtiene el valor 1, el jugador tendrá su salud máxima
            if (probabilidad == 1) {
                jugadores.get(indice).setSaludExtremos(15);
                System.out.println("Su vida se a restaurado su vida al valor máximo gracias a una reliquia!!");
            }
        }
    }
 
  public void reliquiaSalud(ArrayList<Jugador> jugadores, int indice){
    int probabilidad;
    probabilidad=(int)(Math.random()*20)+1; // si obtiene el valor 1, se agregará una meditacion extra
    if(probabilidad==1){
    jugadores.get(indice).addMeditar();
    System.out.println("Se ha aumentado una meditación extra a su colección gracias a una reliquia");
    }
    }
 
 
 
}

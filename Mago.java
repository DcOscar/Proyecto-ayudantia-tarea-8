/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infinitygame;
import java.util.*;

public class Mago extends Jugador{
    private int mana;
    public Mago(String nombre) {
        super(nombre);
        super.meditar=7;
        super.saludMax=15;
        super.meditar=7;
        this.mana=4;
    }
public void concentracion(Guardian guardian){
 if(this.mana==0){
 System.out.println("Se ha quedado sin maná para la concentración");}
 else{
 System.out.println("Activando habilidad especial concentración");
 guardian.dañarGuardian(-2);
 this.mana--;
 }
}

public String getTipo(){
return "Mago";}

  
}

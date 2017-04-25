/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bomberman;

/**
 *
 * @author Aldo
 */
public class Bomba {
    private Coordenadas c;
    public String icono = " Ó ";
   
    public Coordenadas getC() {
        return c;
    }
    public void setC(Coordenadas c) {
        this.c = c;
    }
    
    public Bomba(int fila, int columna){
        Coordenadas c = new Coordenadas(fila, columna);
        this.setC(c);
    }
    
}

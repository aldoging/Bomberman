/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bomberman;

import java.util.ArrayList;

/**
 *
 * @author Aldo
 */
public class Bomberman {
    private int m;
    private int n;
    private int contB = 0;
    private int cont = 0;
    private final ArrayList<Bomba> b = new ArrayList<Bomba>();
    private final String tabla[][];

    public int getM() {
        return m;
    }
    public void setM(int m) {
        this.m = m;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    
    public Bomberman(int filas, int columnas){
        this.setM(filas);
        this.setN(columnas);
        tabla = new String[filas][columnas];
        for(int f = 0; f < filas; f++){
            for (int c = 0; c < columnas; c++){
                tabla[f][c] = " * ";
            }
        }
    }
    
    public void dibujarTablero(){
        for(int f = 0; f < this.getM(); f++){
            for (int c = 0; c < this.getN(); c++){
                System.out.print(tabla[f][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void colocarBombas(int x, int y){
        Bomba bom = new Bomba(x-1, y-1);
        this.contB++;
        if(bom.getC().getX() >= this.getM() || bom.getC().getY() >= this.getN()){
            System.out.println("La bomba "+ contB +" se encuentra fuera del tablero");
            System.exit(0);
        } else {
            this.b.add(bom);
            tabla[x-1][y-1] = b.get(contB-1).icono;
        }
    }
    
    public void explotarBombas(int rangoExp){
        tabla[this.b.get(cont).getC().getX()][this.b.get(cont).getC().getY()] = "   ";
        this.explotarAdelante(rangoExp);
        this.explotarAtras(rangoExp);
        this.explotarAbajo(rangoExp);
        this.explotarArriba(rangoExp);
    }
    
    public void buscaBomLR(int rangoExp, int f, int c){
        int aux = this.cont;
        for(int conta = 1; conta < this.contB; conta++){
            if(f == this.b.get(conta).getC().getX() && c == this.b.get(conta).getC().getY()){
                this.cont = conta;
                explotarBombas(rangoExp);
                this.cont = aux;
                break;
            }
        }
    }
    
    public void buscaBomUD(int rangoExp, int f, int c){
        int aux = this.cont;
        for(int conta = 1; conta < this.contB; conta++){
            if(c == this.b.get(conta).getC().getY() && f == this.b.get(conta).getC().getX()){
                this.cont = conta;
                explotarBombas(rangoExp);
                this.cont = aux;
                break;
            }
        }
    }
    
    public void explotarAdelante(int rangoExp){
        int valor;
        if(this.b.get(cont).getC().getY()+1 + rangoExp > this.getN()){
            valor = this.getN();
        } else {
            valor = this.b.get(cont).getC().getY()+1 + rangoExp;
        }
        for(int c = this.b.get(cont).getC().getY()+1; c < valor; c++){
            if(tabla[this.b.get(cont).getC().getX()][c] == " * "){
                tabla[this.b.get(cont).getC().getX()][c] = "   ";
            } else if(tabla[this.b.get(cont).getC().getX()][c] == this.b.get(cont).icono){
                buscaBomLR(rangoExp, this.b.get(cont).getC().getX(), c);
            }
        }
    }
    
    public void explotarAtras(int rangoExp){
        int valor;
        if(this.b.get(cont).getC().getY()-1 - rangoExp < 0){
            valor = -1;
        } else {
            valor = this.b.get(cont).getC().getY()-1 - rangoExp;
        }
        for(int c = this.b.get(cont).getC().getY()-1; c > valor; c--){
            if(tabla[this.b.get(cont).getC().getX()][c] == " * "){
                tabla[this.b.get(cont).getC().getX()][c] = "   ";
            } else if(tabla[this.b.get(cont).getC().getX()][c] == this.b.get(cont).icono){
                buscaBomLR(rangoExp, this.b.get(cont).getC().getX(), c);
            }
        }
    }
    
    public void explotarAbajo(int rangoExp){
        int valor;
        if(this.b.get(cont).getC().getX()+1 + rangoExp > this.getM()){
            valor = this.getM();
        } else {
            valor = this.b.get(cont).getC().getX()+1 + rangoExp;
        }
        for(int f = this.b.get(cont).getC().getX()+1; f < valor; f++){
            if(tabla[f][this.b.get(cont).getC().getY()] == " * "){
                tabla[f][this.b.get(cont).getC().getY()] = "   ";
            } else if(tabla[f][this.b.get(cont).getC().getY()] == this.b.get(cont).icono){
                buscaBomUD(rangoExp, f, this.b.get(cont).getC().getY());
            }
        }
    }
    
    public void explotarArriba(int rangoExp){
        int valor;
        if(this.b.get(cont).getC().getX()-1 - rangoExp < 0){
            valor = -1;
        } else {
            valor = this.b.get(cont).getC().getX()-1 - rangoExp;
        }
        for(int f = this.b.get(cont).getC().getX()-1; f > valor; f--){
            if(tabla[f][this.b.get(cont).getC().getY()] == " * "){
                tabla[f][this.b.get(cont).getC().getY()] = "   ";
            } else if(tabla[f][this.b.get(cont).getC().getY()] == this.b.get(cont).icono){
                buscaBomUD(rangoExp, f, this.b.get(cont).getC().getY());
            }
        }
    }
    
    public static void main(String[] args) {
        Bomberman t = new Bomberman(5, 5);//filas * columnas
        t.colocarBombas(1, 1);
        t.colocarBombas(2, 3);
        t.colocarBombas(3, 2);
        t.colocarBombas(2, 2);
        t.colocarBombas(3, 1);
        t.colocarBombas(5, 4);
        t.colocarBombas(4, 2);
        t.colocarBombas(3, 5);
        /*t.colocarBombas(3, 3);
        t.colocarBombas(3, 1);
        t.colocarBombas(1, 1);
        t.colocarBombas(3, 5);*/
        /*t.colocarBombas(1, 3);
        t.colocarBombas(3, 3);
        t.colocarBombas(3, 1);
        t.colocarBombas(1, 5);*/
        /*t.colocarBombas(3, 3);
        t.colocarBombas(3, 2);
        t.colocarBombas(3, 4);*/
        t.dibujarTablero();
        t.explotarBombas(3);//alcance de explocion
        t.dibujarTablero();
    }
    
}
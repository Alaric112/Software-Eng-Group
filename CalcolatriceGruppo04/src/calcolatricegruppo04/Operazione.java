/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcolatricegruppo04;

/**
 *
 * @author patap
 */
public class Operazione {
    
    public double somma(double a, double b){
            return a+b;
    }

    public double differenza(double a, double b){
            return a-b;
        }

    public double prodotto(double a, double b){
            return a*b;
    }

    public double divisione(double a, double b){
        if(b==0)
           throw new ArithmeticException("non puoi dividere un numero per 0");
         return a/b;
        }
    
    public double modulo(double numero){
        
       return Math.abs(numero);
    }
    
    public double potenza(double numero, double esponente){
        
        return Math.pow(numero, esponente);
    }
    
}

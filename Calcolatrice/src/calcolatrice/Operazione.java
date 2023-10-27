/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcolatrice;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author patap
 */
public class Operazione extends Exception {
    
    private ArrayList<Double> number;
    private ArrayList<String> expression;
    private double res;
    
    public Operazione() {
        
        this.number = new ArrayList<>();
        this.expression = new ArrayList<>();
        
    }
    
    public void insertExpression(){
        
        Scanner scan = new Scanner(System.in);  
        
        boolean exit = true;
        
        while(exit){
            
            System.out.print("Inserisci numero: ");
            number.add(scan.nextDouble());
            
            System.out.print("Inserisci operazione: ");
            String temp = scan.next();
            expression.add(temp);
            
            if(temp.equalsIgnoreCase("=")){
                exit = false;
            }
            
        }
    
    }
    
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
    
    public void sin(double a){
        res = Math.sin(a);
         }
    public void cosin(double a){
        res = Math.cos(a);
         }
    public void tang(double a){
        res = Math.tan(a);
    }
    
    public void calculateExpression(){
        
        double x,y, localRes;
        x = 0;
        y = 0;        
        
        int i = 0;
        int j = 0;
        
        boolean exit = true;
        while(exit){    
                
        switch (expression.get(j)) {
            case "+":
                localRes = somma(number.get(i), number.get(i+1));
                System.out.println(localRes);
                res += localRes;
                break;
            case "-":
                localRes = differenza(number.get(i), number.get(i+1));
                System.out.println(localRes);
                res += localRes;
                break;
            case "*":
                localRes = prodotto(number.get(i), number.get(i+1));
                System.out.println(localRes);
                res += localRes;
                break;
            case "/":
                localRes = divisione(number.get(i), number.get(i+1));
                System.out.println(localRes);
                res += localRes;
                break;
            case "=":
                
                exit = false;
                break;
            default:
                System.out.println("Parameter is unknown");
            
        };
            
            
            i++;
            j++;
        }
        
        System.out.println("\nIl risultato e': ");
        System.out.println(res);
        
    }
}

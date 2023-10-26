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
public class Operazione {
    
    private ArrayList<Integer> number;
    private ArrayList<String> expression;
    
    public Operazione() {
        
        this.number = new ArrayList<>();
        this.expression = new ArrayList<>();
        
    }
    
    public void insertExpression(){
        
        Scanner scan = new Scanner(System.in);  
        
        boolean exit = true;
        
        while(exit){
            
            System.out.print("Inserisci numero: ");
            number.add(scan.nextInt());
            
            System.out.print("Inserisci operazione: ");
            String temp = scan.next();
            expression.add(temp);
            
            if(temp.equalsIgnoreCase("=")){
                exit = false;
            }
            
        }
    
    }
    
    public void somma(){
        
               
    }
    
    public void differenza(){
        
        
    }
    
    public void prodotto(){
        
        
    }
    
    public void divisione(){
        
        
        
    }
    
    public void calculateExpression(){
        
        int x,y;
        
        int i = 0;
        int j = 0;
        
        boolean exit = true;
        while(true){    
 
        x = number.get(i);
        
        System.out.print(expression.get(j));
        
        switch (expression.get(j)) {
            case "+":
                somma();
                break;
            case "-":
                differenza();
                break;
            case "*":
                prodotto();
                break;
            case "/":
                divisione();
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
        
    }
}

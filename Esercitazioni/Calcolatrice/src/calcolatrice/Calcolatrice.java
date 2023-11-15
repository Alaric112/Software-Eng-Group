/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calcolatrice;

/**
 *
 * @author patap
 */
public class Calcolatrice {

    private Operazione op = new Operazione();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Operazione op = new Operazione();
       
    while(true){    
        
        op.insertExpression();
        
        op.calculateExpression();
        
        }
    }
    
    
}

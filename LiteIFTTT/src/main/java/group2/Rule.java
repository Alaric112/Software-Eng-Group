/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import javafx.beans.Observable;

/**
 *
 * @author patap
 */
public interface Rule {
    
   void checkRule(); 
   
   String getName();
   
   Trigger getTrigger();
   
   Action getAction();
   
   void switchStatus();
   
}

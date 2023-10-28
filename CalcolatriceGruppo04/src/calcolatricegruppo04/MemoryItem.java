/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcolatricegruppo04;

import java.io.Serializable;

/**
 *
 * @author patap
 */
public class MemoryItem implements Serializable {
    
    private Double value;

    public MemoryItem() {
        this.value = 0.0;
    }
    
    public MemoryItem(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}

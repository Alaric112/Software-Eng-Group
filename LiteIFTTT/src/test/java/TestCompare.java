/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import gruppo02.*;


/**
 *
 * @author Faust
 */
public class TestCompare {
    
    
    @Test
    public void compareTest(){
    compare c = new compare();
    int value = c.compare(1, 0);
    assertEquals(1,value);
            }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author Faust
 */
public class SoundAction implements Action{

    String path = "";
    
    @Override
    public void execute() {
        
        //    Desktop.getDesktop().browseFileDirectory(new File(path));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}

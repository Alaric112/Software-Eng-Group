/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *@author soniabruno
 * 
 * An implementation of the {@link Action} interface that represents
 * the action of appending text to a specified file.
 */
public class TextAppendAction implements Action{

    private String textAppend;
    private File file;

    /**
     * Constructs a new TextAppendAction with a default text value ("Hello World!")
     * and a default non-existing file.
     */
    public TextAppendAction() {
        this.textAppend = "Hello World!";
        this.file= new File("not existing");
    }

    public void setTextAppend(String textAppend) {
        this.textAppend = textAppend;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Executes the text append action by appending the specified text to the specified file.
     * The file is created if it does not exist.
     */
    @Override
    public void execute() {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))){
            String string = "\n"+textAppend;
            writer.append(string);
            System.out.println("ho scritto " + string);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @Override
    public String toString() {
        return "Text append :" +textAppend;
    } 
}
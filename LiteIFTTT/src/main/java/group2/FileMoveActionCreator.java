/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.nio.file.Path;

/**
 *
 * @author soniabruno
 */

public class FileMoveActionCreator extends ActionCreator {

    private FileMoveAction fileMoveAction;

    public FileMoveActionCreator() {
        this.fileMoveAction = new FileMoveAction();
    }

    public void setSourcePath(Path sourcePath) {
        fileMoveAction.setSourcePath(sourcePath);
    }

    public void setDestinationPath(Path destinationPath) {
        fileMoveAction.setDestinationPath(destinationPath);
    }

    @Override
    public Action createAction() {
        return fileMoveAction;
    }
}

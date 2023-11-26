/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.media.MediaException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SoundActionTest {

    private SoundAction soundAction;

    @Before
    public void setUp() {
        soundAction = new SoundAction();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(soundAction);
        assertEquals("Asset//AtDoom'sGate.mp3", soundAction.getPath());
    }

    @Test
    public void testGetPath() {
        assertEquals("Asset//AtDoom'sGate.mp3", soundAction.getPath());
    }

    @Test
    public void testSetPath() {
        soundAction.setPath("newpath.mp3");
        assertEquals("newpath.mp3", soundAction.getPath());
    }

    
}


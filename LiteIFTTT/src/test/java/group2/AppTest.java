/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import static org.junit.Assert.*;

import org.junit.*;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

/**
 *
 * @author patap
 */
public class AppTest {
    
    private App appInstance;

    @Before
    public void setUp() {
        appInstance = App.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        // Verifica che le due istanze siano uguali
        App anotherInstance = App.getInstance();
        assertEquals(appInstance, anotherInstance);
    }

    @Test
    public void testMultipleThreadsSingleton() throws InterruptedException {
        // Verifica che, anche con più thread che richiedono l'istanza, l'istanza sia sempre la stessa
        int numberOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {
                App threadInstance = App.getInstance();
                assertSame(appInstance, threadInstance);
                latch.countDown();
            }).start();
        }

        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSingletonReset() throws NoSuchFieldException, IllegalAccessException {
        // Resetta l'istanza del singleton e verifica che una nuova istanza possa essere creata
        Field instanceField = App.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        App newAppInstance = App.getInstance();
        assertNotNull(newAppInstance);
        assertNotSame(appInstance, newAppInstance);
    }

    // Altri casi di test in base alle funzionalità specifiche della tua classe

    // Chiusura dell'applicazione JavaFX alla fine di tutti i test
    @AfterClass
    public static void cleanUp() {
        Platform.exit();
    }
    
}
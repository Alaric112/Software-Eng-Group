/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Rule.FileManager.RuleList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControlRuleCheckerTest {

    private ControlRuleChecker controlRuleChecker;
    private RuleList ruleList;

    @Before
    public void setUp() {
        // Inizializza l'istanza di ControlRuleChecker prima di ogni test
        controlRuleChecker = ControlRuleChecker.getInstance();
        ruleList = new RuleList(1, "sds"); // Inizializza un RuleList di esempio
        controlRuleChecker.changeRuleset(ruleList); // Imposta il RuleList nell'istanza di ControlRuleChecker
    }

    @After
    public void tearDown() {
    // Pulisce l'istanza di ControlRuleChecker dopo ogni test
    if (controlRuleChecker != null && controlRuleChecker.getPeriodicCheckThread() != null && controlRuleChecker.getPeriodicCheckThread().isAlive()) {
        controlRuleChecker.stopPeriodicCheck();
        try {
            controlRuleChecker.getPeriodicCheckThread().join(); // Attendere che il thread termini
        } catch (InterruptedException e) {
            e.printStackTrace(); // Gestire l'eccezione se necessario
        }
    }

    controlRuleChecker = null;
    ruleList = null;
}

    @Test
public void testStartAndStopPeriodicCheck() throws InterruptedException {

    // Verifica che il thread periodico sia avviato correttamente
    controlRuleChecker.startPeriodicCheck();

    // Verifica che il thread periodico sia interrotto correttamente
    controlRuleChecker.stopPeriodicCheck();
    controlRuleChecker.getPeriodicCheckThread().join(); // Attendere che il thread termini

    assertFalse(controlRuleChecker.getPeriodicCheckThread().isAlive());
}
    

    @Test
    public void testSetTimer() {
        // Imposta un nuovo timer e verifica che sia correttamente memorizzato
        int newTimer = 10;
        controlRuleChecker.setTimer(newTimer);
        assertEquals(newTimer, ruleList.getTimer());
    }

    @Test
    public void testChangeRuleset() {
        // Cambia il RuleList e verifica che l'osservatore sia notificato
        RuleList newRuleList = new RuleList(1,"prova");
        controlRuleChecker.changeRuleset(newRuleList);
        assertSame(newRuleList, controlRuleChecker.getRuleSet());
    }
  
}
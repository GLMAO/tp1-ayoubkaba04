package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

/**
 * Application principale avec interface graphique
 *
 */
public class App {

    private static TimerService timerService;

    public static void main(String[] args) {
        // Créer l'instance du TimerService (singleton)
        timerService = new DummyTimeServiceImpl();
        
        // Lancer l'application avec GUI
        testDuTimeService();
    }

    private static void testDuTimeService() {
        // Créer une horloge
        Horloge horloge = new Horloge("TP");
        
        // Connecter l'horloge au TimerService
        horloge.setTimerService(timerService);
        
        // Afficher l'interface graphique
        horloge.showGUI();
        
        System.out.println("Horloge GUI lancée!");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static TimerService getTimerService() {
        return timerService;
    }
}

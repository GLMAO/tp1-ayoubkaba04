package org.emp.gl.clients ; 

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService ; 


public class Horloge implements TimerChangeListener {

    String name; 
    TimerService timerService ; 
    HorlogeGUI gui;


    public Horloge (String name) {
        this.name = name ; 
        
        // Créer l'interface graphique
        gui = new HorlogeGUI(name);
        
        System.out.println ("Horloge "+name+" initialized!") ; 
    }
    
    public void setTimerService(TimerService timerService) {
        if (this.timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }
        this.timerService = timerService;
        if (this.timerService != null) {
            this.timerService.addTimeChangeListener(this);
            // Mettre à jour l'affichage initial
            updateDisplay();
        }
    }
    
    private void updateDisplay() {
        if (timerService != null && gui != null) {
            gui.updateTime(
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes(),
                timerService.getDixiemeDeSeconde()
            );
        }
    }

    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                timerService.getHeures() +":"+
                                timerService.getMinutes()+":"+
                                timerService.getSecondes()) ; 
    }
    
    public void showGUI() {
        if (gui != null) {
            gui.showGUI();
        }
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Mettre à jour l'affichage GUI à chaque changement de temps
        updateDisplay();
        
        // Afficher aussi dans la console pour le debug
        if (prop.equals(TimerChangeListener.DIXEME_DE_SECONDE_PROP)) {
            // Ne pas afficher tous les dixièmes pour éviter le spam
        } else {
            System.out.println(name + " - " + prop + " changé: " + oldValue + " -> " + newValue);
        }
    }

}

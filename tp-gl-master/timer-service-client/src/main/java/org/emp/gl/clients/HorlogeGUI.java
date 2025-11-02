package org.emp.gl.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * Interface graphique moderne pour afficher l'horloge avec couleur rouge
 */
public class HorlogeGUI extends JFrame {
    
    // Couleur rouge personnalisée
    private static final Color RED_COLOR = new Color(255, 0, 0);
    private static final Color BRIGHT_RED = new Color(255, 50, 50);
    private static final Color DARK_RED = new Color(180, 0, 0);
    private static final Color DARK_BG = new Color(20, 20, 20);
    
    private JLabel heuresLabel;
    private JLabel minutesLabel;
    private JLabel secondesLabel;
    private JLabel dixiemesLabel;
    private JLabel separateur1;
    private JLabel separateur2;
    private JLabel separateur3;
    private String horlogeName;
    
    public HorlogeGUI(String name) {
        this.horlogeName = name;
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Horloge Numérique - " + horlogeName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(DARK_BG);
        
        // Panel principal pour le temps avec padding amélioré
        JPanel timePanel = new JPanel();
        timePanel.setBackground(DARK_BG);
        timePanel.setLayout(new GridBagLayout());
        timePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(12, 10, 12, 10);
        
        // Label pour les heures avec bordure
        heuresLabel = createTimeLabel("00");
        gbc.gridx = 0;
        gbc.gridy = 0;
        timePanel.add(heuresLabel, gbc);
        
        separateur1 = createSeparatorLabel(":");
        gbc.gridx = 1;
        timePanel.add(separateur1, gbc);
        
        // Label pour les minutes
        minutesLabel = createTimeLabel("00");
        gbc.gridx = 2;
        timePanel.add(minutesLabel, gbc);
        
        separateur2 = createSeparatorLabel(":");
        gbc.gridx = 3;
        timePanel.add(separateur2, gbc);
        
        // Label pour les secondes
        secondesLabel = createTimeLabel("00");
        gbc.gridx = 4;
        timePanel.add(secondesLabel, gbc);
        
        separateur3 = createSeparatorLabel(".");
        gbc.gridx = 5;
        timePanel.add(separateur3, gbc);
        
        // Label pour les dixièmes de seconde avec taille réduite
        dixiemesLabel = createTimeLabel("0", new Font("Consolas", Font.BOLD, 75));
        gbc.gridx = 6;
        timePanel.add(dixiemesLabel, gbc);
        
        // Panel pour le nom de l'horloge avec style amélioré
        JPanel namePanel = new JPanel();
        namePanel.setBackground(DARK_BG);
        namePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(80, 0, 0)),
            BorderFactory.createEmptyBorder(10, 0, 10, 0)
        ));
        
        JLabel nameLabel = new JLabel(horlogeName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        nameLabel.setForeground(BRIGHT_RED);
        nameLabel.setBackground(DARK_BG);
        nameLabel.setOpaque(true);
        namePanel.add(nameLabel);
        
        add(timePanel, BorderLayout.CENTER);
        add(namePanel, BorderLayout.SOUTH);
        
        // Configuration de la fenêtre
        pack();
        setSize(780, 270);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(650, 220));
    }
    
    private JLabel createTimeLabel(String text) {
        return createTimeLabel(text, new Font("Consolas", Font.BOLD, 90));
    }
    
    private JLabel createTimeLabel(String text, Font font) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(BRIGHT_RED);
        label.setBackground(new Color(25, 25, 25));
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(115, 135));
        
        // Bordure avec effet de brillance amélioré
        Border outerBorder = BorderFactory.createLineBorder(new Color(50, 0, 0), 3);
        Border middleBorder = BorderFactory.createLineBorder(new Color(100, 0, 0), 1);
        Border innerBorder = BorderFactory.createLineBorder(new Color(150, 0, 0), 1);
        Border compoundBorder = BorderFactory.createCompoundBorder(
            outerBorder, 
            BorderFactory.createCompoundBorder(middleBorder, innerBorder)
        );
        label.setBorder(compoundBorder);
        
        return label;
    }
    
    private JLabel createSeparatorLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Consolas", Font.BOLD, 90));
        label.setForeground(RED_COLOR);
        label.setBackground(DARK_BG);
        label.setOpaque(true);
        
        // Effet de clignotement pour les séparateurs (optionnel)
        return label;
    }
    
    public void updateTime(int heures, int minutes, int secondes, int dixiemes) {
        SwingUtilities.invokeLater(() -> {
            heuresLabel.setText(String.format("%02d", heures));
            minutesLabel.setText(String.format("%02d", minutes));
            secondesLabel.setText(String.format("%02d", secondes));
            dixiemesLabel.setText(String.format("%d", dixiemes));
        });
    }
    
    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            toFront();
            requestFocus();
        });
    }
}


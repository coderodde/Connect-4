package net.coderodde.connect4;

import javax.swing.JFrame;

/**
 * This class bundles all the business logic of Connect 4 game.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 14, 2019)
 */
public final class App {
    
    private static final String FRAME_TITLE = "Connect Four";
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    
    public App() {
    }
    
    public void run() {
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.getContentPane().add(new ConnectFourPanel(COLUMNS, ROWS));
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

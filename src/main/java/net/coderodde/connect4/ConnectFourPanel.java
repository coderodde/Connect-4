package net.coderodde.connect4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class implements the game view.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 14, 2019)
 */
public final class ConnectFourPanel extends JPanel {
    
    private static final int DEFAULT_COLUMNS = 7;
    private static final int DEFAULT_ROWS = 6;
    
    /**
     * By default the diameter of the slot circle is 82 percent of the enclosing
     * cell.
     */
    private static final float DEFAULT_DIAMETER_PERCENTAGE = 0.82f;
    
    private static final class Colors {
        Color backgroundColor;
        Color freeOpeningColor;
        Color redPlayerColor;
        Color selectionBorderColor;
        Color whitePlayerColor;
    }
    
    private static final Colors DEFAULT_COLORS = createDefaultColors();
    
    private final Colors currentColors = createCurrentColors();
    private int columns = DEFAULT_COLUMNS;
    private int rows    = DEFAULT_ROWS;
    private float radiusPercentage = DEFAULT_DIAMETER_PERCENTAGE;
    private final SlotState[][] gameGrid;
    
    public ConnectFourPanel(int width, int height) {
        gameGrid = new SlotState[checkHeight(height)]
                                [checkWidth(width)];
        initializeGameGrid();
    }
    
    private void initializeGameGrid() {
        for (int y = 0; y < this.gameGrid.length; y++) {
            for (int x = 0; x < this.gameGrid[0].length; x++) {
                this.gameGrid[y][x] = SlotState.EMPTY;
            }
        }
    }
    
    private static final int checkHeight(int candidateHeight) {
        if (candidateHeight < 1) {
            throw new IllegalArgumentException("candidateHeight < 1");
        }
        
        return candidateHeight;
    }
    
    private static final int checkWidth(int candidateWidth) {
        if (candidateWidth < 1) {
            throw new IllegalArgumentException("candidateWidth < 1");
        }
        
        return candidateWidth;
    }
    
    @Override
    public void update(Graphics g) {
        int panelWidth  = getWidth();
        int panelHeight = getHeight();
        
        int columnWidth  = getColumnWidth(panelWidth);
        int rowHeight    = getRowHeight(panelHeight);
        int squareLength = Math.min(columnWidth, rowHeight);
        
        int widthLeftoverPixels   = panelWidth  - columns * squareLength;
        int heightLeftowverPixels = panelHeight - rows    * squareLength;
        
        int skipPixelsFromLeftBorder = widthLeftoverPixels   / 2;
        int skipPixelsFromTopBorder  = heightLeftowverPixels / 2;
        
        this.setBackground(currentColors.selectionBorderColor);
        g.fillRect(skipPixelsFromLeftBorder, 
                   skipPixelsFromTopBorder, 
                   squareLength * columns, 
                   squareLength * rows);
        
        int diameter = (int)(radiusPercentage * squareLength);
        int diameterLeftovers = squareLength - diameter;
        int skipPixels = diameterLeftovers / 2;
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                switch (this.gameGrid[y][x]) {
                    case EMPTY:
                        g.setColor(currentColors.freeOpeningColor);
                        break;
                        
                    case RED:
                        g.setColor(currentColors.redPlayerColor);
                        break;
                        
                    case WHITE:
                        g.setColor(currentColors.whitePlayerColor);
                        break;
                }
                
                g.fillOval(skipPixels + x * squareLength 
                                      + skipPixelsFromLeftBorder,
                           skipPixels + y * squareLength 
                                      + skipPixelsFromTopBorder,
                           diameter,
                           diameter);
            }
        }
        
        
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    
    public void setBackgroundColor(Color backgroundColor) {
        currentColors.backgroundColor = 
                backgroundColor != null ?
                backgroundColor :
                DEFAULT_COLORS.backgroundColor;
    }
    
    public void setRedPlayerColor(Color redPlayerColor) {
        currentColors.redPlayerColor = 
                redPlayerColor != null ?
                redPlayerColor : 
                DEFAULT_COLORS.redPlayerColor;
    }
    
    public void setSelectionBorderColor(Color selectionBorderColor) {
        currentColors.selectionBorderColor =
                selectionBorderColor != null ?
                selectionBorderColor : 
                DEFAULT_COLORS.selectionBorderColor;
    }
    
    public void setWhitePlayerColor(Color whitePlayerColor) {
        currentColors.whitePlayerColor =
                whitePlayerColor != null ?
                whitePlayerColor :
                DEFAULT_COLORS.whitePlayerColor;
    }
    
    private final int getColumnWidth(int panelWidth) {
        return panelWidth / columns;
    }
    
    private final int getRowHeight(int panelHeight) {
        return panelHeight / rows;
    }
    
    private static final Colors createDefaultColors() {
        Colors colors = new Colors();
        colors.backgroundColor      = Color.BLACK;
        colors.freeOpeningColor     = new Color(122, 122, 122);
        colors.redPlayerColor       = new Color(198, 3, 3);
        colors.whitePlayerColor     = new Color(239, 239, 239);
        colors.selectionBorderColor = new Color(43, 124, 165);
        return colors;
    }
    
    private static final Colors createCurrentColors() {
        Colors colors = new Colors();
        colors.backgroundColor      = DEFAULT_COLORS.backgroundColor;
        colors.freeOpeningColor     = DEFAULT_COLORS.freeOpeningColor;
        colors.redPlayerColor       = DEFAULT_COLORS.redPlayerColor;
        colors.selectionBorderColor = DEFAULT_COLORS.selectionBorderColor;
        colors.whitePlayerColor     = DEFAULT_COLORS.whitePlayerColor;
        return colors;
    }
}

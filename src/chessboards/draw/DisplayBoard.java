package chessboards.draw;

import java.awt.Color;
import java.awt.Graphics;

import chessboards.Chessboard;

public class DisplayBoard {
    
    private Chessboard chessboard;
    
    private int cellSize;
    
    private int xOffset;
    
    private int yOffset;
    
    private int innerBorder = 3;
    
    public DisplayBoard(Chessboard chessboard) {
        this(chessboard, 10);
    }
    
    public DisplayBoard(Chessboard chessboard, int cellSize) {
        this(chessboard, cellSize, 0, 0);
    }
    
    public DisplayBoard(Chessboard chessboard, int cellSize, int xOffset, int yOffset) {
        this.cellSize = cellSize;
        this.chessboard = chessboard;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void paint(Graphics g) {
        int index = 0;
        for (int y = 0; y < getTotalSize(); y += cellSize) {
            g.setColor(Color.BLACK);
            g.drawLine(xOffset, y + yOffset, xOffset + getTotalSize(), y + yOffset);
            for (int x = 0; x < getTotalSize(); x += cellSize) {
                g.setColor(Color.BLACK);
                g.drawLine(x + xOffset, y + yOffset, x + xOffset, y + yOffset + cellSize);
                if (chessboard.get(index)) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
//                System.out.println("filling at " + x + " " + y + " -> " + (xOffset + x) + " " + (yOffset + y));
                int cx = xOffset + x + innerBorder;
                int cy = yOffset + y + innerBorder;
                g.fillRect(cx, cy, cellSize - (innerBorder * 2), cellSize - (innerBorder * 2));
                index++;
            }
            g.setColor(Color.BLACK);
            g.drawLine(getTotalSize() + xOffset, y + yOffset, getTotalSize() + xOffset, y + yOffset + cellSize);
        }
        g.setColor(Color.BLACK);
        g.drawLine(xOffset, getTotalSize() + yOffset, xOffset + getTotalSize(), getTotalSize() + yOffset);
    }
    
    public int getTotalSize() {
        return chessboard.getDim() * cellSize;
    }
}

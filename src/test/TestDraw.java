package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import chessboards.Chessboard;
import chessboards.draw.DisplayBoard;

public class TestDraw {
    
    @Test
    public void drawABoard() throws IOException {
        int width = 300;
        int height = 300;
        Chessboard cb = new Chessboard(3, "[010|100|001]");
        DisplayBoard board = new DisplayBoard(cb, 100);
        Image image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        board.paint(image.getGraphics());
        ImageIO.write((RenderedImage)image, "PNG", new File("imgA.png"));
    }
    
    public void drawManyBoards(int width, int height, int dim, int metaDimW, int metaDimH, int cellSize, int buffer, String inputFilename, String imgFilename) throws IOException {
        Image image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        int count = 1;
        int xOffset = buffer;
        int yOffset = buffer;
        int colCounter = 0;
        while ((line = reader.readLine()) != null) {
            Chessboard cb = new Chessboard(dim, line.trim());
            DisplayBoard board = new DisplayBoard(cb, cellSize, xOffset, yOffset);
            System.out.println(count + " " + (count % metaDimW) + " drawing " + cb + " at " + xOffset + " " + yOffset);
            board.paint(g);
            xOffset = (count % metaDimW == 0)? buffer : xOffset + buffer + (dim * cellSize);
            yOffset = (count % metaDimW == 0)? yOffset + buffer + (dim * cellSize) : yOffset;
            count++;
            if (count > (metaDimW * metaDimH)) break;
        }
        reader.close();
        
        ImageIO.write((RenderedImage)image, "PNG", new File(imgFilename));
    }
    
    @Test
    public void drawThreeThreeBoards() throws IOException {
        int width = 1050;
        int height = 850;
        int dim = 3;
        int metaDimW = 5;
        int metaDimH = 4;
        int cellSize = 50;
        int buffer = 50;
        drawManyBoards(width, height, dim, metaDimW, metaDimH, cellSize, buffer, "three_three.txt", "three_three.png");
    }
    
    @Test
    public void drawFourFourBoards() throws IOException {
        int width = 1600;
        int height = 2200;
        int dim = 4;
        int metaDimW = 10;
        int metaDimH = 14;
        int cellSize = 25;
        int buffer = 50;
        drawManyBoards(width, height, dim, metaDimW, metaDimH, cellSize, buffer, "four_four.txt", "four_four.png");
    }
    
    @Test
    public void drawFiveFiveBoards() throws IOException {
        int width = 1800;
        int height = 2250;
        int dim = 5;
        int metaDimW = 27;
        int metaDimH = 34;
        int cellSize = 10;
        int buffer = 15;
        drawManyBoards(width, height, dim, metaDimW, metaDimH, cellSize, buffer, "five_five.txt", "five_five.png");
    }
    
}

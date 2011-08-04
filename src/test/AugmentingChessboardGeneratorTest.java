package test;

import group.SSPermutationGroup;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import chessboards.AugmentingChessboardGenerator;
import chessboards.Chessboard;
import chessboards.DiscreteChessboardPartitionRefiner;
import chessboards.handler.FileoutHandler;
import chessboards.handler.HistogramEnumerationHandler;

public class AugmentingChessboardGeneratorTest {
    
    public void countBoards(int dim) {
        HistogramEnumerationHandler handler = new HistogramEnumerationHandler();
        AugmentingChessboardGenerator generator = new AugmentingChessboardGenerator(handler);
        generator.generate(dim);
        Map<Integer, List<Chessboard>> bins = handler.getBins();
        System.out.println("");
        for (int index = 1; index < bins.size(); index++) {
            System.out.println(index + "\t" + bins.get(index).size());
        }
    }
    
    @Test
    public void threeByThreeTest() {
        int dim = 3;
        AugmentingChessboardGenerator generator = new AugmentingChessboardGenerator();
        DiscreteChessboardPartitionRefiner refiner = new DiscreteChessboardPartitionRefiner();
        Chessboard emptyBoard = new Chessboard(dim);
        SSPermutationGroup group = refiner.getAutomorphismGroup(emptyBoard);
        generator.generate(dim, emptyBoard, group);
    }
    
    @Test
    public void writeThreeByThreeTest() throws IOException {
        int dim = 3;
        FileoutHandler handler = new FileoutHandler("three_three.txt");
        AugmentingChessboardGenerator generator = new AugmentingChessboardGenerator(handler);
        generator.generate(dim);
        handler.close();
    }
    
    @Test
    public void writeFourByFourTest() throws IOException {
        int dim = 4;
        FileoutHandler handler = new FileoutHandler("four_four.txt");
        AugmentingChessboardGenerator generator = new AugmentingChessboardGenerator(handler);
        generator.generate(dim);
        handler.close();
    }
    
    @Test
    public void writeFiveByFiveTest() throws IOException {
        int dim = 5;
        FileoutHandler handler = new FileoutHandler("five_five.txt");
        AugmentingChessboardGenerator generator = new AugmentingChessboardGenerator(handler);
        generator.generate(dim);
        handler.close();
    }
    
    @Test
    public void threeByThreeCountTest() {
        int dim = 3;
        countBoards(dim);
    }
    
    @Test
    public void fourByFourCountTest() {
        int dim = 4;
        countBoards(dim);
    }
    
    @Test
    public void fiveByFiveCountTest() {
        int dim = 5;
        countBoards(dim);
    }
    
    @Test
    public void sixBySixCountTest() {
        int dim = 6;
        countBoards(dim);
    }
    
    @Test
    public void sevenBySevenCountTest() {
        int dim = 7;
        countBoards(dim);
    }
    
}

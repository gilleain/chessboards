package test;

import org.junit.Test;

import chessboards.BruteForceChessboardGenerator;

public class BruteForceChessboardGeneratorTest {
    
    @Test
    public void dimThreeTest() {
        BruteForceChessboardGenerator generator = new BruteForceChessboardGenerator();
        generator.generate(3);
    }
}

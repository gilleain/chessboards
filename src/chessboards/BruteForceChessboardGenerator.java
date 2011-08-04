package chessboards;

import java.util.ArrayList;
import java.util.List;

import chessboards.handler.EnumerationHandler;
import chessboards.handler.StdOutEnumerationHandler;

public class BruteForceChessboardGenerator implements ChessboardGenerator {
    
    private EnumerationHandler handler;
    
    private List<Chessboard> generated;
    
    private int attempts = 0;
    
    public BruteForceChessboardGenerator() {
        this(new StdOutEnumerationHandler());
    }
    
    public BruteForceChessboardGenerator(EnumerationHandler handler) {
        this.handler = handler;
        this.generated = new ArrayList<Chessboard>(); 
    }
    
    public void generate(int dim) {
        for (int current = 0; current < dim * dim; current++) {
            generate(dim, new Chessboard(dim, current), current);
        }
        System.out.println(attempts + " attempts");
        System.out.println(generated.size() + " generated");
    }
    
    public void generate(int dim, Chessboard chessboard, int current) {
//        System.out.println("trying " + chessboard + " " + current);
        attempts++;
        for (Chessboard alreadySeen : generated) {
            if (isIsomorphic(chessboard, alreadySeen)) {
                return;
            }
        }
        generated.add(chessboard);
        handler.handle(chessboard);
        for (int next = current + 1; next < dim * dim; next++) {
            if (chessboard.allowed(next)) {
                generate(dim, new Chessboard(dim, chessboard, next), next);
            }
        }
    }
    
    public boolean isIsomorphic(Chessboard boardA, Chessboard boardB) {
        if (boardA.sameAs(boardB)) {
            return true;
        }
        if (boardB.flipHorizontal().sameAs(boardA)) {
            return true;
        }
        if (boardB.flipVertical().sameAs(boardA)) {
            return true;
        }
        Chessboard rotatedBoard;
        for (int r = 0; r < 3; r++) {
            rotatedBoard = boardB.rotateClockwise();
            if (boardA.sameAs(rotatedBoard)) {
                return true;
            }
            if (rotatedBoard.flipHorizontal().sameAs(boardA)) {
                return true;
            }
            if (rotatedBoard.flipVertical().sameAs(boardA)) {
                return true;
            }
        }
        return false;
    }
    
}

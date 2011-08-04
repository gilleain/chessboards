package chessboards;

import chessboards.handler.EnumerationHandler;
import chessboards.handler.StdOutEnumerationHandler;
import group.Permutation;
import group.SSPermutationGroup;

public class AugmentingChessboardGenerator implements ChessboardGenerator {
    
    private EnumerationHandler handler;
    
    public AugmentingChessboardGenerator() {
        this(new StdOutEnumerationHandler());
    }
    
    public AugmentingChessboardGenerator(EnumerationHandler handler) {
        this.handler = handler;
    }
    
    public void generate(int dim) {
        DiscreteChessboardPartitionRefiner refiner = new DiscreteChessboardPartitionRefiner();
        Chessboard emptyBoard = new Chessboard(dim); 
        SSPermutationGroup group = refiner.getAutomorphismGroup(emptyBoard);
        generate(dim, emptyBoard, group);
    }
    
    public void generate(int dim, Chessboard chessboard, SSPermutationGroup group) {
        if (isOk(chessboard, group)) {
            handler.handle(chessboard);
            int next = chessboard.getMaxColor() + 1;
            for (; next < dim * dim; next++) {
                if (chessboard.allowed(next)) {
                    generate(dim, new Chessboard(dim, chessboard, next), group);
                }
            }
        }
    }
    
    public boolean isOk(Chessboard chessboard, SSPermutationGroup group) {
        if (chessboard.numberOfColors() == 0) return true;
        String initial = chessboard.getColorString();
        for (Permutation permutation : group.all()) {
            String permuted = chessboard.getPermutedColorString(permutation);
            if (initial.compareTo(permuted) > 0) {
                return false;
            }
        }
        return true;
    }
    
}

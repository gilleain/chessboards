package chessboards;

import group.AbstractEquitablePartitionRefiner;
import group.IEquitablePartitionRefiner;

import java.util.Set;

public class EquitableChessboardPartitionRefiner 
       extends AbstractEquitablePartitionRefiner 
       implements IEquitablePartitionRefiner {
    
    private Chessboard chessboard;
    
    public EquitableChessboardPartitionRefiner(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    
    @Override
    public int getNumberOfVertices() {
        return chessboard.getDim() * chessboard.getDim();
    }
    
    @Override
    public int neighboursInBlock(Set<Integer> block, int vertexIndex) {
        int neighbourCount = 0;
        for (int element : block) {
            if (chessboard.areNeighbours(element, vertexIndex)) {
                neighbourCount++;
            }
        }
        return neighbourCount;
    }
    
}

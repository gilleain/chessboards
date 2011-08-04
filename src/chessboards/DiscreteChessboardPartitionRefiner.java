package chessboards;

import group.AbstractDiscretePartitionRefiner;
import group.IEquitablePartitionRefiner;
import group.Partition;
import group.Permutation;
import group.SSPermutationGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscreteChessboardPartitionRefiner extends AbstractDiscretePartitionRefiner {
    
    private Chessboard chessboard;
    
    private void setup(Chessboard chessboard) {
        this.chessboard = chessboard;
        SSPermutationGroup group = new SSPermutationGroup(new Permutation(getVertexCount()));
        IEquitablePartitionRefiner refiner = new EquitableChessboardPartitionRefiner(chessboard);
        super.setup(group, refiner);
    }
    
    public List<EquivalenceClass> getEquivalenceClasses(int dim, SSPermutationGroup group) {
        List<EquivalenceClass> eqClasses = new ArrayList<EquivalenceClass>();
        Map<Integer, EquivalenceClass> eqClMap = new HashMap<Integer, EquivalenceClass>();
        for (int index = 0; index < dim * dim; index++) {
            if (!eqClMap.containsKey(index)) {
                EquivalenceClass eqCl = new EquivalenceClass();
                eqClMap.put(index, eqCl);
                eqClasses.add(eqCl);
                for (Permutation p : group.all()) {
                    int pE = p.get(index);
                    if (!eqCl.contains(pE)) {
                        eqCl.add(pE);
                        eqClMap.put(pE, eqCl);
                    }
                }
            }
        }
        return eqClasses;
    }
    
    public SSPermutationGroup getAutomorphismGroup(Chessboard chessboard) {
        setup(chessboard);
        refine(Partition.unit(getVertexCount()));
        return getGroup();
    }
    
    @Override
    public int getVertexCount() {
        return chessboard.getDim() * chessboard.getDim();
    }
    
    @Override
    public boolean isConnected(int i, int j) {
        return chessboard.areNeighbours(i, j);
    }
    
    @Override
    public boolean sameColor(int i, int j) {
        boolean colI = chessboard.get(i);
        boolean colJ = chessboard.get(j);
        return colI == colJ;
    }
    
}

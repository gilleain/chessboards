package test;

import group.Permutation;
import group.SSPermutationGroup;

import java.util.List;

import org.junit.Test;

import chessboards.Chessboard;
import chessboards.DiscreteChessboardPartitionRefiner;
import chessboards.EquivalenceClass;

public class DiscreteChessboardPartitionRefinerTest {
    
    @Test
    public void test3By3Group() {
        Chessboard chessboard = new Chessboard(3);
        DiscreteChessboardPartitionRefiner refiner = new DiscreteChessboardPartitionRefiner();
        SSPermutationGroup group = refiner.getAutomorphismGroup(chessboard);
        for (Permutation p : group.all()) {
            System.out.println(p + "\t" + p.toCycleString());
        }
    }
    
    @Test
    public void test3By3AutPartition() {
        int dim = 3;
        Chessboard chessboard = new Chessboard(dim);
        DiscreteChessboardPartitionRefiner refiner = new DiscreteChessboardPartitionRefiner();
        SSPermutationGroup group = refiner.getAutomorphismGroup(chessboard);
        List<EquivalenceClass> eqClasses = refiner.getEquivalenceClasses(dim, group);
        for (EquivalenceClass eqCl : eqClasses) {
            System.out.println(eqCl);
        }
    }
    
}

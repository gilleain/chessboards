package test;

import group.Permutation;
import group.SSPermutationGroup;

import java.util.BitSet;

import org.junit.Assert;
import org.junit.Test;

import chessboards.Chessboard;
import chessboards.DiscreteChessboardPartitionRefiner;

public class ChessboardTest {
	
	@Test
	public void printTest() {
	    BitSet bitSet = new BitSet(9);
	    bitSet.set(1);
	    bitSet.set(3);
	    bitSet.set(5);
	    bitSet.set(7);
		Chessboard cb = new Chessboard(3, bitSet);
		System.out.println(cb);
	}
	
	@Test
    public void setTest() {
	    Chessboard cb = new Chessboard(3);
	    cb.setByRowCol(0, 1);
	    cb.setByRowCol(1, 0);
	    cb.setByRowCol(1, 2);
	    cb.setByRowCol(2, 1);
	    System.out.println(cb);
	}
	
	@Test
    public void roundtripTest() {
        Chessboard cb = new Chessboard(3, "[010|101|010]");
        System.out.println(cb);
        Chessboard rt = new Chessboard(3, cb.toString());
        System.out.println(rt);
    }
	
	@Test
	public void sameAsTPTest() {
	    Chessboard cbA = new Chessboard(3, "[010|101|010]");
	    Chessboard cbB = new Chessboard(3, "[010|101|010]");
	    Assert.assertTrue(cbA.sameAs(cbB));
	}
	
	@Test
    public void sameAsTNTest() {
        Chessboard cbA = new Chessboard(3, "[010|101|010]");
        Chessboard cbB = new Chessboard(3, "[101|010|101]");
        Assert.assertFalse(cbA.sameAs(cbB));
    }
	
	@Test
	public void testIndexToRowCol() {
	    int dim = 3;
	    Chessboard cb = new Chessboard(dim, "[010|101|010]");
	    for (int index = 0; index < dim * dim; index++) {
	        System.out.println(index + " = (" + cb.indexToRow(index) + ", " + cb.indexToCol(index) + ")");
	    }
	}
	
	@Test
    public void testGetNeighbour() {
        int dim = 3;
        Chessboard cb = new Chessboard(dim, "[010|101|010]");
        for (int index = 0; index < dim * dim; index++) {
            for (Chessboard.Neighbour neighbour : Chessboard.Neighbour.values()) {
                System.out.println(index + "\t" + neighbour + "\t" + cb.getNeighbourIndex(index, neighbour));
            }
        }
    }
	
	@Test
	public void testPermutedColorString() {
	    int dim = 3;
        Chessboard cb = new Chessboard(dim, "[010|001|000]");
        DiscreteChessboardPartitionRefiner refiner = new DiscreteChessboardPartitionRefiner();
        SSPermutationGroup group = refiner.getAutomorphismGroup(cb);
        for (Permutation p : group.all()) {
            System.out.println(cb.getPermutedColorString(p));
        }
	}

}

package chessboards;

import group.Permutation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Chessboard {
	
	private int dim;
	
	private BitSet colors;
	
	public enum Neighbour { N, E, S, W };
	
	public Chessboard(int dim) {
		this.dim = dim;
		this.colors = new BitSet(dim * dim);
	}
	
	public Chessboard(int dim, int indexToColor) {
	    this(dim);
	    this.set(indexToColor);
	}
	
	public Chessboard(int dim, Chessboard other, int indexToColor) {
        this(dim);
        this.colors.or(other.colors);
        this.set(indexToColor);
    }
	
	public Chessboard(int dim, BitSet colors) {
		this.dim = dim;
		this.colors = colors;
	}
	
	public Chessboard(int dim, String colorString) {
		this(dim);
		
		int row = 0;
		int col = 0;
		for (int stringIndex = 1; stringIndex < colorString.length() - 1; stringIndex++) {
			char cI = colorString.charAt(stringIndex);
			switch(cI) {
			    case '[': break;
			    case '0': 
			        col++; 
			        break;
			    case '1':  
			        setByRowCol(col, row);
			        col++; 
			        break;
			    case '|': 
			        row++; 
			        col = 0; 
			        break;
			    case ']': break;
			    default: break;
			}
		}
	}
	
	/**
	 * Checks all colored positions to see if they would be neighbours of the supplied index.
	 * 
	 * @param indexToColor
	 * @return
	 */
	public boolean allowed(int indexToColor) {
	    for (Neighbour neighbour : Neighbour.values()) {
	        int neighbourIndex = getNeighbourIndex(indexToColor, neighbour); 
	        if (neighbourIndex != -1) {
	            if (colors.get(neighbourIndex)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	public boolean areNeighbours(int indexA, int indexB) {
	    int rowA = indexToRow(indexA);
	    int rowB = indexToRow(indexB);
	    if (Math.abs(rowA - rowB) > 1) return false;
	    int colA = indexToCol(indexA);
	    int colB = indexToCol(indexB);
	    if (Math.abs(colA - colB) > 1) return false;
	    return true;
	}
	
	public int getNeighbourIndex(int index, Neighbour neighbour) {
	    int row = indexToRow(index);
	    int col = indexToCol(index);
	    int neighbourIndex;
	    switch (neighbour) {
	        case N: neighbourIndex = (row == 0)? -1 : rowColToIndex(row - 1, col); break;
	        case E: neighbourIndex = (col == dim - 1)? -1 : rowColToIndex(row, col + 1); break;
	        case S: neighbourIndex = (row == dim - 1)? -1 : rowColToIndex(row + 1, col); break;
	        case W: neighbourIndex = (col == 0)? -1 : rowColToIndex(row, col - 1); break;
	        default: return -1;
	    }
	    return neighbourIndex;
	}
	
	public int indexToRow(int index) {
	    return (int)(Math.floor(index/dim));
	}
	
	public int indexToCol(int index) {
	    return index % dim;
	}
	
	public int rowColToIndex(int row, int col) {
	    return col + (row * dim);
	}
	
	public void setByRowCol(int row, int col) {
	    colors.set(rowColToIndex(row, col));
	}
	
	public void set(int indexToColor) {
	    this.colors.set(indexToColor);
	}
	
	public boolean get(int index) {
        return this.colors.get(index);
    }
	
	/**
	 * Create a new chessboard rotated by 90 deg.
	 *  
	 * @return a new chessboard rotated by 90 deg.
	 */
	public Chessboard rotateClockwise() {
        // TODO : more general rotation 
	    return permute(new int[] { 2, 5, 8, 1, 4, 2, 0, 3, 6 });
	}
	
	public Chessboard flipHorizontal() {
        // TODO : more general flip
	    return permute(new int[] {2, 1, 0, 5, 4, 3, 8, 7, 6});
	}
	
	public Chessboard flipVertical() {
        // TODO : more general flip
        return permute(new int[] {6, 7, 8, 3, 4, 5, 0, 1, 2});
    }
	
	public Chessboard permute(int[] permutation) {
	    Chessboard permuted = new Chessboard(dim);
	    for (int i = 0; i < dim * dim; i++) {
            if (get(i)) {
                permuted.set(permutation[i]);
            }
        }
	    return permuted;
	}
	
	public boolean sameAs(Chessboard board) {
	    return this.colors.equals(board.colors);
	}
	
	public static List<EquivalenceClass> getEquivalenceClassesFromDim(int dim) {
	    List<EquivalenceClass> eqClasses = new ArrayList<EquivalenceClass>();
	    // TODO : more general eq cl generation
	    if (dim == 3) {
	        eqClasses.add(new EquivalenceClass(0,2,6,8));
	        eqClasses.add(new EquivalenceClass(1,3,5,9));
	        eqClasses.add(new EquivalenceClass(4));
	    }
	    return eqClasses;
	}
	
	public int numberOfColors() {
	    return colors.cardinality();
	}
	
    public int getDim() {
        return dim;
    }
    
    public String getPermutedColorString(Permutation permutation) {
       return permute(permutation.getValues()).getColorString(); 
    }
    
    public String getColorString() {
        return colors.toString();
    }
    
    public int getMaxColor() {
        int max = -1;
        for (int i = 0; i < dim * dim; i++) {
            if (colors.get(i)) {
                max = i;
            }
        }
        return max;
    }
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				int bitSetIndex = (dim * row) + col;
//				System.out.println(row + "\t" + col + "\t" + bitSetIndex);
				if (colors.get(bitSetIndex)) {
					sb.append('1');
				} else {
					sb.append('0');
				}
			}
			if (row < dim - 1) {
				sb.append('|');
			}
		}
		sb.append(']');
		return sb.toString();
	}

}

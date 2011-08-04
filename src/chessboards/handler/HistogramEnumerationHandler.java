package chessboards.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessboards.Chessboard;

public class HistogramEnumerationHandler implements EnumerationHandler {
    
    private Map<Integer, List<Chessboard>> bins;
    
    private int count;
    
    public HistogramEnumerationHandler() {
        bins = new HashMap<Integer, List<Chessboard>>();
    }
    
    @Override
    public void handle(Chessboard chessboard) {
        if (count % 10 == 0) System.out.print(".");
        if (count % 1000 == 0) System.out.println();
        count++;
        int size = chessboard.numberOfColors();
        if (size == 0) return;
        if (bins.containsKey(size)) {
            bins.get(size).add(chessboard);
        } else {
            List<Chessboard> bin = new ArrayList<Chessboard>();
            bin.add(chessboard);
            bins.put(size, bin);
        }
    }
    
    public Map<Integer, List<Chessboard>> getBins() {
        return bins;
    }
    
}

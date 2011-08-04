package chessboards.handler;

import chessboards.Chessboard;

public class StdOutEnumerationHandler implements EnumerationHandler {
    
    private int count;
    
    public StdOutEnumerationHandler() {
        this.count = 0;
    }

    @Override
    public void handle(Chessboard chessboard) {
        System.out.println(count + "\t" + chessboard + "\t" + chessboard.numberOfColors());
        count++;
    }
    
}

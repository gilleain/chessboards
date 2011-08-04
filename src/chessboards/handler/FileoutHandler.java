package chessboards.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import chessboards.Chessboard;

public class FileoutHandler implements EnumerationHandler {
    
    private BufferedWriter writer;
    
    public FileoutHandler(String filepath) throws IOException {
        this(new File(filepath));
    }
    
    public FileoutHandler(File file) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(file));
    }
    
    @Override
    public void handle(Chessboard chessboard) {
        try {
            writer.write(chessboard.toString());
            writer.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void close() throws IOException {
        writer.close();
    }
    
}

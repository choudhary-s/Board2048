import entity.Board;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        try {
            Board board = new Board(4);
            while(true){
                System.out.println("Select Move:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine().trim();
                int control = Integer.parseInt(s);
                switch (control){
                    case 1:
                        //right
                        board.rightControl();
                        break;
                    case 2:
                        //left
                        board.leftControl();
                        break;
                    case 3:
                        //Up
                        board.upControl();
                        break;
                    case 4:
                        //down
                        board.downControl();
                        break;
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

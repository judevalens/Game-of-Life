import java.util.ArrayList;
import java.util.Scanner;

public class CellMatrix {
    int matRow, matCol;
    Cell[][] mainMatrix;

    int iteration = 1;

    ArrayList<Cell> surroundingBox = new ArrayList<Cell>();

    int cellLeftBound;
    int cellRighBound;

    int cellTopBound;
    int cellBottomBound;


    public CellMatrix(int w, int l) {
        matRow = w;
        matCol = l;
        mainMatrix = new Cell[matRow][matCol];

        init();

    }

    public void iteration() {
        /// set up nextState
        for (int i = 0; i < matRow; i++) {
            for (int j = 0; j < matRow; j++) {
                surroundingBox.clear();
                setSurrounding(i, j);

                Cell currentCell = mainMatrix[i][j];

                // x < 2 dies by underpopulation
                // 2 <= x <= 3 lives
                // x > 3 dies by overpopulation
                // x == 3 born
                int fate = 0;

                for (Cell c : surroundingBox) {
                    if (c.getState() == 'l') {
                        fate++;
                    }
                }
                if (currentCell.getState() == 'l') {

                    if (fate < 2) {
                        currentCell.setNextState('d');
                    } else if (fate >= 2 && fate <= 3) {
                        currentCell.setNextState('l');
                    } else if (fate > 3) {
                        currentCell.setNextState('d');
                    }
                } else {
                    if (fate == 3) {
                        currentCell.setNextState('l');
                    }
                }
            }
        }
        updateCellsState();
    }

    public void setSurrounding(int r, int c) {

        int left = c - 1;

        int right = c + 1;

        int top = r - 1;
        int bottom = r + 1;

        for (int i = top; i <= bottom; i++) {
            int currentRow = i % matRow;
            if (currentRow < 0) {
                currentRow += matRow;
            }

            for (int j = left; j <= right; j++) {

                int currentCol = j % matCol;
                if (currentCol < 0) {
                    currentCol += matCol;
                }
                // mainMatrix[currentRow][currentCol] = new Cell('l');

                if (currentRow != r || currentCol != c) {

                    surroundingBox.add(mainMatrix[currentRow][currentCol]);
                }

            }

        }
    }

    public void init() {

        for (int i = 0; i < matRow; i++) {
            for (int j = 0; j < matCol; j++) {
                Cell c = new Cell('d');

                mainMatrix[i][j] = c;
            }
        }

/*
      // GLIDER
        mainMatrix[0][1] = new Cell('l');
        mainMatrix[1][2] = new Cell('l');
        mainMatrix[2][0] = new Cell('l');
        mainMatrix[2][1] = new Cell('l');
        mainMatrix[2][2] = new Cell('l');


        //Square
        mainMatrix[0][1] = new Cell('l');
        mainMatrix[0][2] = new Cell('l');
        mainMatrix[1][1] = new Cell('l');
        mainMatrix[1][2] = new Cell('l');
        

*/
        //Exploder
        
        mainMatrix[5][6] = new Cell('l');
        mainMatrix[6][6] = new Cell('l');
        mainMatrix[7][6] = new Cell('l');
        mainMatrix[8][6] = new Cell('l');
        mainMatrix[9][6] = new Cell('l');

        mainMatrix[5][10] = new Cell('l');
        mainMatrix[6][10] = new Cell('l');
        mainMatrix[7][10] = new Cell('l');
        mainMatrix[8][10] = new Cell('l');
        mainMatrix[9][10] = new Cell('l');

        mainMatrix[5][8] = new Cell('l');

        mainMatrix[9][8] = new Cell('l');



    }

    public void start() {

        Scanner s = new Scanner(System.in);

        String st = " ";

        init();
        st = s.nextLine();

        while (st.equals("")) {

            iteration();
            printMat();

            st = s.nextLine();
        }
        /*
         * for (int[] x : surroundingBox) { for (int z : x) {
         * 
         * System.out.print(z + " "); }
         * 
         * System.out.println();
         * 
         * }
         */

    }

    public void printMat() {
        for (Cell[] r : mainMatrix) {
            for (Cell c : r) {
                if (c.getNextState() == 'd') {
                    System.out.print(". ");
                } else if (c.getNextState() == 'l') {
                    System.out.print("# ");
                }

                c.setState();
            }

            System.out.println();
        }

        System.out.println("ITERATION " + iteration++);

    }

    public Cell[][] getMat(){
        return mainMatrix;
    }

    public void updateCellsState(){
        for (Cell[] r : mainMatrix) {
            for (Cell c : r) {
                c.setState();
            }

            System.out.println();
        }
    }
}
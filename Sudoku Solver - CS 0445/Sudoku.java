package cs445.a3;

import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sudoku {
	
	//static variables changed in extend, used in next
	static int rowIndex = 0;
	static int colIndex = 0;
	
	/*accepts a partial solution
	*return: true if complete, valid solution.
	*/
    static boolean isFullSolution(int[][] board) {
        //loop through the board and check for any empty places
		//if any empty spaces (0), returns false
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == 0){
					return false;
				}
			}
		}
		
		//checks to see if board is invalid
		if(reject(board)){
			return false;
		}
        
		//if no zeros are found in the board, returns true
		return true;
    }

	/*accepts a partial solution
	*returns true if it should be rejected
	*rejected if it can never be extended into complete solution.
	*/
    static boolean reject(int[][] board) {
		
		//loops through the array to check column and row for any repeated values
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				int k = 0;
				while(k < 9){
					if(board[i][j] != 0 && i != k && board[i][j] == board[k][j]){
						//number in the same column
						return true;
					}else if(board[i][j] != 0 && j != k && board[i][j] == board[i][k]){
						//number in the same row
						return true;
					}
					k++;	
				}
			}
		}
		
		//loops through array to check each 3x3 block for repeated values
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				
				//narrowing the current spot into its 3x3 block
				int rowBox = (i / 3) * 3;
				int colBox = (j / 3) * 3;
				
				for(int k = 0; k < 3; k++){
					for(int l = 0; l < 3; l++){
						if(board[i][j] != 0 && (i != rowBox + k && j != colBox + l) && board[i][j] == board[rowBox + k][colBox + l]){
							//number in the same block
							return true;
						}
					}
				}
			}
		}
		
		//no values found to be in conflict
        return false;
    }

	/*accepts a partial solution
	*returns another partial solution with one more choice added one
	*returns null if no more choices to add to solution
	*makes NEW partial solution, does not just edit the original
	*/
    static int[][] extend(int[][] board) {
		//create a new array and a value for restriction in the if statments
		int[][] temp = new int[9][9];
		int zerocount = 0;
		
		//loops through and searches for next space to put a value while copying array
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] != 0){
					temp[i][j] = board[i][j];
				}else if(board[i][j] == 0 && zerocount == 0){
					temp[i][j] = 1;
					zerocount++; //makes sure this "else if" statement is only done once
					rowIndex = i;
					colIndex = j; //adjusts the static variables for the "next" method
				}else if(board[i][j] == 0 && zerocount > 0){
					temp[i][j] = board[i][j];
				}else if(i == 8 && j == 8 && board[i][j] != 0 && zerocount == 0){
					return null; //end of the board reached and no zero values found
				}
			}
		}
		
		//returns the new array if a value was changed
		if(zerocount == 1){
			return temp;
		}
		
		//if nothing was changed, returns null
		return null;
    }

	/*accepts a partial solution
	*returns another partial solution where most recent choice
	*has been changed to its next option
	*returns null if no more options for most recent choice
	*/
    static int[][] next(int[][] board, int row, int col) {
		//new array and tracking value created
		int[][] temp = new int[9][9];
		int plusonecount = 0;
		
		//loops through to copy the array and "next" the most recently added value
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				//checks against the static variables changed in extend method
				if(i == row && j == col && board[i][j] == 0){
					return null; //should not be reached since the most recent changed should be 1 or higher
				}else if(i == row && j == col && board[i][j] < 9 && plusonecount == 0){
					temp[i][j] = board[i][j] + 1; //adds one to "next" the most recently "extended" spot
					plusonecount++;
				}else if(i == row && j == col && board[i][j] == 9){
					return null; //if the value is already at its highest possible value, then loop ends, return null
				}else{
					temp[i][j] = board[i][j];
				}
				
			}
			
		}
		
		//if array copied successfully, returns the copy
		return temp;
    
	}

	static void testIsFullSolutionUnit(int[][] test){
		if(isFullSolution(test) == true){
			System.out.println("Full Solution: ");
			printBoard(test);
			System.out.println("");
		}else{
			System.out.println("Not Full Solution: ");
			printBoard(test);
			System.out.println("");
		}
	}
	
    static void testIsFullSolution() {
        //Full solutions:
		testIsFullSolutionUnit(new int[][] {{5,2,4,1,3,6,9,8,7}, {1,8,3,7,4,9,6,5,2}, {7,9,6,8,5,2,4,3,1}, {9,6,7,2,1,3,5,4,8}, {2,4,5,9,8,7,1,6,3}, {8,3,1,5,6,4,7,2,9}, {6,7,9,4,2,8,3,1,5}, {3,1,8,6,7,5,2,9,4}, {4,5,2,3,9,1,8,7,6}});
			//solved board
			
		testIsFullSolutionUnit(new int[][] {{3,9,1,7,2,5,6,8,4}, {2,4,8,6,9,1,7,3,5}, {5,6,7,3,4,8,9,1,2}, {8,2,5,9,3,4,1,6,7}, {9,7,4,1,5,6,8,2,3}, {6,1,3,8,7,2,5,4,9}, {4,3,6,5,8,7,2,9,1}, {7,8,9,2,1,3,4,5,6}, {1,5,2,4,6,9,3,7,8}});
			//solved board
		
		//Not full solutions:
		testIsFullSolutionUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}); 
			//empty board
			
		testIsFullSolutionUnit(new int[][] {{5,2,4,1,3,6,9,8,7}, {1,8,3,0,4,9,6,5,2}, {7,9,6,8,5,0,4,3,1}, {9,6,7,2,1,3,5,4,8}, {2,4,5,9,8,7,1,6,3}, {8,3,1,5,6,4,7,2,9}, {6,7,9,4,2,8,3,0,5}, {3,1,8,6,7,5,2,9,4}, {4,5,2,3,0,1,8,7,6}}); 
			//a few zeros throughout
			
		testIsFullSolutionUnit(new int[][] {{5,2,4,1,3,6,9,8,7}, {1,8,3,7,4,9,6,5,2}, {7,9,6,8,5,2,4,3,1}, {9,6,7,2,1,3,5,4,8}, {2,4,5,9,8,7,1,6,3}, {8,3,1,5,6,4,7,2,9}, {6,7,9,4,2,8,3,1,5}, {3,1,8,6,7,5,2,9,4}, {4,5,2,3,9,1,8,7,0}}); 
			//a zero in the last spot
			
		testIsFullSolutionUnit(new int[][] {{3,9,1,7,2,5,6,8,4}, {2,4,8,6,9,1,7,3,5}, {5,6,7,3,4,8,9,1,2}, {8,2,5,9,3,4,4,6,7}, {9,7,4,1,5,6,8,2,3}, {6,1,3,8,7,2,5,4,9}, {4,3,6,6,8,7,2,9,1}, {7,8,9,2,1,3,4,5,6}, {1,5,2,4,6,9,3,7,8}}); 
			//has a conflict and will go to the reject statement
    }

	static void testRejectUnit(int[][] test){
		if(reject(test) == true){
			System.out.println("Rejected: ");
			printBoard(test);
			System.out.println("");
			System.out.println("");
		}else{
			System.out.println("Not Rejected: ");
			printBoard(test);
			System.out.println("");
			System.out.println("");
		}
	}
	
    static void testReject() {
        //Not Rejected:
		testRejectUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}});
			//empty board
			
		testRejectUnit(new int[][] {{3,9,1,7,2,5,6,8,4}, {2,4,0,6,9,1,7,3,5}, {5,6,7,3,4,0,9,1,2}, {0,0,0,0,0,0,0,0,0}, {9,7,4,1,5,6,8,2,3}, {6,1,3,8,7,2,5,4,9}, {4,3,6,5,8,7,2,9,1}, {7,8,9,2,1,3,4,5,6}, {1,5,2,4,6,9,3,7,8}});
			//unsolved board without any conflicts
		
		testRejectUnit(new int[][] {{3,4,2,1,8,9,7,6,5}, {8,5,9,7,6,4,1,3,2}, {1,6,7,2,5,3,4,9,8}, {4,7,8,5,3,1,9,2,6}, {9,3,1,4,2,6,8,5,7}, {6,2,5,8,9,7,3,4,1}, {7,1,3,6,4,5,2,8,9}, {5,8,4,9,7,2,6,1,3}, {2,9,6,3,1,8,5,7,4}});
			//solved board with no conflicts
			
		//Rejected:
		testRejectUnit(new int[][] {{0,3,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,3,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}); 
			//first 3x3 block failure
			
		testRejectUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,2,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,2,0}}); 
			//last 3x3 block failure
			
		testRejectUnit(new int[][] {{0,0,0,5,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,5,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}); 
			//same column failure (5 in 1st and 7th row, 4th spot)
			
		testRejectUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,4,0,0,0,0,4,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}); 
			//same row failure (2 fours in 7th row)
    }

	static void testExtendUnit(int[][] test){
		System.out.println("Extended ");
		printBoard(test);
		System.out.println("");
		System.out.println("to ");
		printBoard(extend(test));
		System.out.println("");
		System.out.println("");
	}
	
    static void testExtend() {
        //Cannot be extended:
		testExtendUnit(new int[][] {{2,3,9,8,4,1,5,6,7}, {1,5,4,7,9,6,8,2,3}, {7,8,6,2,3,5,9,1,4}, {6,1,8,9,7,2,4,3,5}, {4,7,2,5,1,3,6,8,9}, {5,9,3,4,6,8,2,7,1}, {3,4,1,6,8,9,7,5,2}, {9,6,5,3,2,7,1,4,8}, {8,2,7,1,5,4,3,9,6}}); 
			//solved board
			
		testExtendUnit(new int[][] {{2,7,4,3,5,1,6,9,8}, {2,7,4,3,5,1,6,9,8}, {9,5,6,7,4,8,2,3,1}, {4,2,7,1,3,5,8,6,9}, {3,6,5,9,8,2,4,1,7}, {1,9,8,4,6,7,5,2,3}, {7,8,2,6,1,3,9,5,4}, {5,4,1,8,2,9,3,7,6}, {6,3,9,5,7,4,1,8,2}}); 
			//would fail reject, but that shouldn't affect extend
			
		//Can be extended: 
		testExtendUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}); 
			//empty board
			
		testExtendUnit(new int[][] {{2,9,5,7,4,3,8,6,1}, {4,3,1,8,6,5,9,2,7}, {8,7,6,1,9,2,5,4,3}, {3,8,0,4,5,9,0,0,6}, {6,1,2,3,8,7,4,9,5}, {5,4,9,0,0,0,7,3,0}, {7,6,3,5,3,4,1,8,9}, {9,2,8,6,7,1,3,5,4}, {1,5,4,9,3,8,6,7,2}}); 
			//empty spots in 4th row
			
		testExtendUnit(new int[][] {{3,7,4,9,6,2,8,1,5}, {9,8,1,3,4,5,2,7,6}, {6,5,2,7,8,1,3,9,4}, {7,4,8,5,2,9,1,6,3}, {1,2,3,6,7,8,5,4,9}, {5,6,9,1,3,4,7,8,2}, {4,1,7,2,9,3,6,5,8}, {2,9,6,8,5,7,4,3,1}, {8,3,5,4,1,6,9,2,0}}); 
			//last spot empty
    }

	static void testNextUnit(int[][] test, int row, int col){
		System.out.println("Nexted ");
		printBoard(test);
		System.out.println("");
		System.out.println("to ");
		printBoard(next(test, row, col));
		System.out.println("");
		System.out.println("");
	}
	
    static void testNext() {
        //Cannot be "nexted"
		int row = 8;
		int col = 8;
		testNextUnit(new int[][] {{5,3,4,6,7,8,9,1,2}, {6,7,2,1,9,5,3,4,8}, {1,9,8,3,4,2,5,6,7}, {8,5,9,7,6,1,4,2,3}, {4,2,6,8,5,3,7,9,1}, {7,1,3,9,2,4,8,5,6}, {9,6,1,5,3,7,2,8,4}, {2,8,7,4,1,9,6,3,5}, {3,4,5,2,8,6,1,7,9}}, row, col); 
			//full board with last digit 9
			
		row = 0;
		col = 8;
		testNextUnit(new int[][] {{3,4,5,2,8,6,1,7,9}, {0,0,0,0,0,0,0,0,0},	{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}, row, col); 
			//last digit in first row is 9

		row = 0;
		col = 0;
		testNextUnit(new int[][] {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}}, row, col); 
			//empty board
		
		//Can be "nexted"
		row = 8;
		col = 8;
		testNextUnit(new int[][] {{3,4,5,2,8,6,1,7,9}, {2,8,7,4,1,9,6,3,5}, {9,6,1,5,3,7,2,8,4}, {7,1,3,9,2,4,8,5,6}, {4,2,6,8,5,3,7,9,1}, {8,5,9,7,6,1,4,2,3}, {1,9,8,3,4,2,5,6,7}, {6,7,2,1,9,5,3,4,8}, {5,3,4,6,7,8,9,1,2}}, row, col); 
			//full board without last digit 9
		
		row = 2;
		col = 8;
		testNextUnit(new int[][] {{3,4,5,2,8,6,1,7,9}, {2,8,7,4,1,9,6,3,5}, {9,6,1,5,3,7,2,8,4}, {0,1,3,9,2,4,0,5,6}, {0,2,6,8,5,3,0,9,1}, {0,5,9,7,6,1,0,2,3}, {1,7,8,3,4,5,9,6,2}, {6,9,2,1,0,8,3,4,7}, {5,3,4,6,7,2,0,1,8}}, row, col); 
			//random empty spots

    }

	//prints the whole board out
    static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

	//loads a board from a file and places it in the array
    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (Exception e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

	//recursive calls to solve the sudoku board
    static int[][] solve(int[][] board) {
		if (reject(board)) return null;
        if (isFullSolution(board)) return board;
        int[][] attempt = extend(board);
		
		//local variables for the extended space
		int row = rowIndex;
		int col = colIndex;		
		
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt, row, col); 
        }

        return null;
    }

	//begins the program and either loads a file or runs the test methods
    public static void main(String[] args) {
        if (args[0].equals("-t")) {
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else {
            int[][] board = readBoard(args[0]);
            printBoard(solve(board));
        }
    }
}

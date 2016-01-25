package edu.princeton.cs.algs4;

public class Sudoku {
	public static void main(String[] main){
		int[][] board = {
				{9,0,0,0,2,0,4,0,1},
				{0,0,5,0,9,0,0,2,0},
				{0,4,0,1,0,0,0,9,7},
				{5,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,0,0,0,2},
				{4,0,0,5,7,0,3,0,9},
				{0,5,3,0,0,2,1,8,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,2,8,0,0,0,0,3}
		};
		Print(board);
		System.out.println();
		if(solve(board)){
			System.out.println("Find a solution");
			Print(board);
		}else{
			System.out.println("No solution");
		}
		
	}
	public static void Print(int[][] board){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static boolean solve(int[][] board){
		int[][] status = new int[board.length][board[0].length]; //support matrix
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				status[i][j] = board[i][j]>0?2:0; //set status as 2 means fixed
			}
		}
		//now we use both matrices to start from the 1st element
		return Solve(board, status, 0, 0);
	}
	private static boolean Solve(int[][] board, int[][] status, int x, int y){
		if(x == 9){ //first step, judge if we come to the end, from (0,0) to (8,8), (9,0) invalid
			int count = 0; //check if all values are set
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					count += status[i][j]>0?1:0; //update count if status is non zero
				}
			}
			if(count == 81) return true; //find a solution
			else return false; //trial failed
		}
		//not end, proceed further
		if(status[x][y] >= 1){
			int nextX = x;
			int nextY = y+1; //proceed from left to right, if reach the end, jump to next row
			if(nextY == 9){
				nextX = x+1; nextY = 0;
			}
			//recursive call of next position
			return Solve(board, status, nextX, nextY); 
		}else{ //check row, column and cell to decide all possible values 
			boolean[] used = new boolean[9];
			for(int i = 0; i < 9; i++){ //check row
				if(status[x][i] >= 1){
					used[board[x][i] - 1] = true; //use board[x][i] value - 1 as index
				}
			}
			for(int i = 0; i < 9; i++){ //check column
				if(status[i][y] >= 1){
					used[board[i][y] - 1] = true;
				}
			}
			for(int i = x-(x%3); i < x-(x%3)+3; i++){ //check cell, row and column start from 0,3,6
				for(int j = y-(y%3); j < y-(y%3)+3; j++){
					if(status[i][j] >= 1){
						used[board[i][j] - 1] = true;
					}
				}
			}
			//after the check, we start trying each possible values and set the status to 1
			for(int i = 0; i < used.length; i++){
				if(!used[i]){
					status[x][y] = 1;
					board[x][y] = i+1; //index + 1 is the value
					int nextX = x;
					int nextY = y+1; //proceed from left to right, if reach the end, jump to next row
					if(nextY == 9){
						nextX = x+1; nextY = 0;
					}
					if(Solve(board, status, nextX, nextY)){
						return true;
					}
					//now it means the setting failed we should reverse the setting and try next value
					for(int m = 0; m < 9; m++){
						for(int n = 0; n < 9; n++){
							if(m > x || (m == x && n >= y)){ //only reset those values behind current position
								if(status[m][n] == 1){
									status[m][n] = 0;
									board[m][n] = 0;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}

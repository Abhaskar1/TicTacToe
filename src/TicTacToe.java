import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static List<Integer> playerPositions=new ArrayList<>();
	static List<Integer> cpuPositions=new ArrayList<>();
	
	public static void main(String[] args) {	
		char[][] board= {
				{' ','|',' ','|',' ' },
				{'-','+','-','+','-' },
				{' ','|',' ','|',' ' },
				{' ','+','-','+','-' },
				{' ','|',' ','|',' ' }
				};
		printBoard(board);
		Scanner sc=new Scanner(System.in);
		while (true) {
			System.out.println("Enter your placement(1-9)");
			int playerPos = sc.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("This position is already taken. Please enter a different position");
				playerPos=sc.nextInt();
			}
			placePeice(board, playerPos, "player");
			String result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos=rand.nextInt(9)+1;
			}
			placePeice(board, cpuPos, "cpu");
			result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
		}
	}
	public static void printBoard(char[][] board) {
		for(char[] row: board) {
			for(char c:row) {
				System.out.print(c);
			}
			System.out.println();
			
		}
	}
	public static void placePeice(char[][] board,int pos,String user) {
		char symbol=' ';
		if(user.equalsIgnoreCase("player")) {
			symbol='X';
			playerPositions.add(pos);
		}
		else if(user.equalsIgnoreCase("cpu")) {
			symbol='O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
		case 1:
			board[0][0]=symbol;
			break;
		case 2:
			board[0][2]=symbol;
			break;
		case 3:
			board[0][4]=symbol;
			break;
		case 4:
			board[2][0]=symbol;
			break;
		case 5:
			board[2][2]=symbol;
			break;
		case 6:
			board[2][4]=symbol;
			break;
		case 7:
			board[4][0]=symbol;
			break;
		case 8:
			board[4][2]=symbol;
			break;
		case 9:
			board[4][4]=symbol;
			break;
		default:
		}
		printBoard(board);
		

	}
	public static String checkWinner() {
		List topRow=Arrays.asList(1,2,3);
		List midRow=Arrays.asList(4,5,6);
		List botRow=Arrays.asList(7,8,9);
		List topCol=Arrays.asList(1,4,7);
		List midCol=Arrays.asList(2,5,8);
		List botCol=Arrays.asList(3,6,9);
		List cross1=Arrays.asList(1,5,9);
		List cross2=Arrays.asList(7,5,3);
		
		List<List<Integer>> winningPositions=new ArrayList<>();
		winningPositions.add(topRow);
		winningPositions.add(midRow);
		winningPositions.add(botRow);
		winningPositions.add(topCol);
		winningPositions.add(midCol);
		winningPositions.add(botCol);
		
		for(List l:winningPositions) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won";
			}
			else if(cpuPositions.containsAll(l)) {
				return "Oops Better Luck Next Time";
			}
			else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw";
			}
		}
		
		
		
		return "";
	}
}

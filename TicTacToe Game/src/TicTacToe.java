import java.util.HashSet;
import java.util.Scanner;

public class TicTacToe {
	//creating hash set for user and computer to store values and operatons
	static HashSet<Integer>user_set = new HashSet<Integer>();// declaring hashset for user
	static  HashSet<Integer>comp_set = new HashSet<Integer>(); // declaring hashset for computer
	public static void main(String[] args) {
		// step 1 create game board using char 2d array and print it 
		char [][] g_board = {{' ','|',' ','|',' '},
							 {'-','|','-','|','-'},
							 {' ','|',' ','|',' '},
							 {'-','|','-','|','-'},
							 {' ','|',' ','|',' '},
							 };
		//step2 create a print method to debug g_board using loops 
		print_board(g_board);
		// creating scanner input for the user and computer in infinite loop using while
		Scanner sc = new Scanner(System.in);
		while(true)// true for infinite loop
		{
			System.out.println("input value from 1-9:");
			int user_pos = sc.nextInt();
			while(user_set.contains(user_pos)||comp_set.contains(user_pos)) // this condn checks whether the userposition and comp position are same if the pos values are more then 9 then this loop will start
			{
				System.out.println();
				System.out.println("Retry,enter value from 1-9:");
				user_pos = sc.nextInt(); // we make user loop the input till he enters valid vlaue 
			}
			place_holder(g_board,user_pos,"You"); //this is the main method for user
			
			String res = check_winner();
			if(res.length()>0)
			{
				System.out.println(res);
				break;
			}
			int cpu_pos = gen_random(); // creating main method for cpu
			while(user_set.contains(cpu_pos)||comp_set.contains(cpu_pos)) // this condn checks whether the userposition and comp position are same if the pos values are more then 9 then this loop will start
			{
				cpu_pos= gen_random(); // we generating random value to the hashset for compset
			}
			place_holder(g_board,cpu_pos,"comp"); //this is the main method for comp
			res = check_winner();
			if(res.length()>0)
			{
				System.out.println(res);
				break;
			}
		}
	}
	static String check_winner() // checking the winner condition
	{
		HashSet<Integer> r1 = new HashSet<Integer>();
		r1.add(1);r1.add(2);r1.add(3);
		HashSet<Integer> r2 = new HashSet<Integer>();
		r2.add(4);r2.add(5);r2.add(6);
		HashSet<Integer> r3 = new HashSet<Integer>();
		r3.add(7);r3.add(8);r3.add(9);
		HashSet<Integer> c1 = new HashSet<Integer>();
		c1.add(1);c1.add(4);c1.add(7);
		HashSet<Integer> c2 = new HashSet<Integer>();
		c2.add(2);c2.add(5);c2.add(8);
		HashSet<Integer> c3 = new HashSet<Integer>();
		c3.add(3);c3.add(6);c3.add(9);
		HashSet<Integer> d1 = new HashSet<Integer>();
		d1.add(1);d1.add(5);d1.add(9);
		HashSet<Integer> d2 = new HashSet<Integer>();
		d2.add(3);d2.add(5);d2.add(7);
		//instead of looping around each hash set checking user_set and comp_set we will create hashset for hash set
		HashSet<HashSet> set = new HashSet<HashSet>();
		set.add(r1); set.add(r2); set.add(r3);
		set.add(c1); set.add(c2); set.add(c3);
		set.add(d1); set.add(d2);// we have added alll tyhe hash set in main set of set
		// now we will loop using for each loop
		for(HashSet c:set)
		{
			if(user_set.containsAll(c))
			{
				return "YOU WON";
			}
			else if(comp_set.containsAll(c))
			{
				return "SORRY YOU LOOSE";
			}	
		}
		if(user_set.size()+comp_set.size()==9) // hash set has the method size when all size of the hash set is exhausted then it will show draw
		{
			return "ITS A DRAW";
			
		}
		return "" ;
	}
	private static int gen_random()  // random method for random
	{
		int max = 9;
		int min = 1;
		int range =max-min+1;
		int res = (int)(Math.random()*range)+min; // we are restricting the random by type casting it to int and applying the the range multiplication with addition of the min 
		return res;
	}
	static void print_board(char [][] g_board) {
		for(int row=0; row<g_board.length;row++) {
			for (int col=0; col<g_board[0].length; col++) {
				System.out.print(g_board[row][col]);
			}
			System.out.println();
		}
	}
	static void place_holder(char [][] g_board,int pos,String user) {
		//create a symbol
		char syb = 'X';
		if(user.equals("you")){
			syb = 'X';
			user_set.add(pos); // this a hash set used to update the operation in user set
		}
		else if(user.equals("comp")){
			syb = 'O';
			comp_set.add(pos); // this a hash set used to update the operation in computer
			
		}
		switch(pos) {
		case 1: 
			g_board[0][0]=syb; //place holder of 00 first row first col
			break ;
		case 2: 
			g_board[0][2]=syb; // place holder of 02 forst row 2nd col coz first row 1st col is used by the vertical line
			break ;
		case 3: 
			g_board[0][4]=syb; //place holder of 02 forst row 4nd col coz first row 3st col is used by the vertical line
			break ;
		case 4:
			g_board[2][0]=syb; // place holder 20 is 1 is used ny the horizonatl line so we take 2nd row with above vertical condition
			break ;
		case 5:
			g_board[2][2]=syb;
			break ;
		case 6:
			g_board[2][4]=syb;
			break ;
		case 7:
			g_board[4][0]=syb; // place holder  40 is 3 is used ny the horizonatl line so we take 4nd row with above vertical condition 
			break ;
		case 8:
			g_board[4][2]=syb;
		break ;
		case 9:
			g_board[4][4]=syb;
			break ;
			default:
				System.out.println("invalid input select till 9th element only");
		}
		System.out.println();
		print_board(g_board); // this method is that evry operation should follow in game board
	}
}
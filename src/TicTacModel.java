
public class TicTacModel {
	int board[] = new int[9];
	boolean winSquare[] = new boolean[9];
	
	int winner = EMPTY;
	boolean canMove = true;
	boolean isCrossTurn = true;
	
	static final int EMPTY = 0;
	static final int CROSS = 1;
	static final int CIRCLE = 2;
	
	public boolean checkForWin() {
		int winRows[][] = {
				{ 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 2, 4, 6 }
		};
		for(int i = 0; i < winRows.length; i++)
		{
			if(
					board[ winRows[i][0] ] != EMPTY &&
					board[ winRows[i][0] ] == board[ winRows[i][1] ] && 
					board[ winRows[i][0] ] == board[ winRows[i][2] ]
				)
			{
				winSquare[ winRows[i][0] ] = true;
				winSquare[ winRows[i][1] ] = true;
				winSquare[ winRows[i][2] ] = true;
				winner = board[ winRows[i][0] ];
			}
		}
		return winner != EMPTY;
	}
	
	public void clear()
	{
		for(int i = 0; i < 9; i++)
		{
			board[i] = EMPTY;
			winSquare[i] = false;
		}
		winner = EMPTY;
		canMove = true;
		// isCrossTurn = ?? - usually, losing player starts
	}
}

/* Stephen Klein
// This program searches a matrix for the words entered in terminal and highlights them with red text, then 
// prints the matrix.
*/

import java.util.Scanner;

public class MatrixSearch
{	
	/** The searcher method cycles through the char array until it finds the first letter of the word we are searching for,
	/** then it calls the searchWord method to check for the rest of the word in the specified directions */
	public void searcher(char[][] charArray, int x, int y, boolean[][] boolArray, String[] wordsToSearch, MatrixSearch mtrx)
 	{
 		for(int i = 0; i < wordsToSearch.length; i++)
 		{
 			for(int row = 0; row < x; row++)
 				for(int col = 0; col < y; col++)
 				{
 					if(charArray[row][col] == wordsToSearch[i].charAt(0))
 					{
						/** Searches horizontally to the right */
						if((y - 1 - col + 1) >= (wordsToSearch[i].length()))
						{
							mtrx.searchWord(row, col, 0, 1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches horizontally to the left*/
						if((col - 0 + 1) >= wordsToSearch[i].length())
						{
							mtrx.searchWord(row, col, 0, -1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches vertically down */
						if(((x - 1) - row + 1) >= (wordsToSearch[i].length()))
						{
							mtrx.searchWord(row, col, 1, 0, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches diagonally down and to the right */
						if(((y - 1 - col + 1) >= (wordsToSearch[i].length()))&& ((row - 0 + 1) <= (wordsToSearch[i].length())))
						{
							mtrx.searchWord(row, col, 1, 1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches diagonally down and to the left */
						if(((col - 0 + 1) >= wordsToSearch[i].length()) && ((row - 0 + 1) <= (wordsToSearch[i].length())))
						{
							mtrx.searchWord(row, col, 1, -1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches vertically up */
						if((row - 0 + 1) >= (wordsToSearch[i].length()))
						{
							mtrx.searchWord(row, col, -1, 0, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches diagonally up and to the right */
						if(((row - 0 + 1) >= (wordsToSearch[i].length())) && ((y - 1 - col + 1) >= (wordsToSearch[i].length())))
						{
							mtrx.searchWord(row, col, -1, 1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
						
						/** Searches diagonally up and to the left */
						if(((row - 0 + 1) >= (wordsToSearch[i].length())) && ((col - 0 + 1) >= wordsToSearch[i].length()))
						{
							mtrx.searchWord(row, col, -1, -1, boolArray, charArray, wordsToSearch[i], mtrx);
						}
					}
 				} 
		}
	}
	
	/** The searchWord method checks to see if the rest of the word is there when given the specific direction to search.
	/** it calls the updateBoolean method when it finds a complete match, or breaks the loop if one of the characters does not match */
	public void searchWord(int x, int y, int dx, int dy, boolean[][] boolArray, char[][] matrix, String word, MatrixSearch mtrx)
	{
		for(int w = 1; w < word.length(); w++)
		{
			if((w == (word.length() - 1)) && (matrix[x + (dx * w)][y + (dy * w)] == word.charAt(w)))
			{
				mtrx.updateBoolean(x, y, dx, dy, word.length(), boolArray);
			}
			
			if(matrix[x + (dx * w)][y + (dy * w)] != word.charAt(w))
			{
				break;
			}
		}
	}
	
	/** The updateBoolean method changes the locations in the boolean array to true if the word is found */
	public void updateBoolean(int x, int y, int dx, int dy, int length, boolean[][] boolArray)
	{
		for(int i = 0; i < length; i++)
		{
			boolArray[x + (dx * i)][y + (dy * i)] = true;
		}
	}
	
	public static void main (String[] args)
	{
		Scanner matrixInput = new Scanner(System.in);
		
		/** The dimensions of the array */
		int rows = matrixInput.nextInt();
		int columns = matrixInput.nextInt();
		
		
		char[][] wordSearch = new char[rows][columns];
		boolean[][] letterChecker = new boolean[rows][columns];
		MatrixSearch test = new MatrixSearch();
		
		/** Fills the char array with the chars from the matrix in the text file */
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < wordSearch[row].length; col++)
			{
				String token = matrixInput.next();
				wordSearch[row][col] = token.charAt(0);
			}
			
			test.searcher(wordSearch, rows, columns, letterChecker, args, test);
		
		for(int row = 0; row < rows; row++)
			{
				for(int col = 0; col < wordSearch[row].length; col++)
				{
					if(letterChecker[row][col] == true) // Prints the chars in red text if they are found to be inside of a word
						System.out.print("\033[31m" + wordSearch[row][col] + "\033[0m" + " ");
					
					else
						System.out.print(wordSearch[row][col] + " ");
				}
				System.out.println();
			}
	}
}
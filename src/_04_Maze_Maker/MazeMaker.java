package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		int cel = randGen.nextInt();
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(uncheckedCells.get(cel));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		Cell celll;
		Cell popedCell;
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if (!unvisitors.isEmpty()) {
	
			//C1. select one at random.
			celll = unvisitors.get(randGen.nextInt());
			//C2. push it to the stack
			uncheckedCells.push(celll);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, celll);
			//C4. make the new cell the current cell and mark it as visited
		currentCell = celll;
		currentCell.setBeenVisited(true);
			
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}	
		//D. if all neighbors are visited
		
		if (unvisitors.isEmpty()) {
			//D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
			
				// D1a. pop a cell from the stack
			popedCell =	uncheckedCells.pop();
				// D1b. make that the current cell
				popedCell = currentCell;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX()-c2.getX() > 0) {
			c1.setEastWall(false);
			c2.setWestWall(false);
			
		}
		
		if (c1.getY()-c2.getY() > 0) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		else if (c1.getX()-c2.getX() < 0) {
			c1.setWestWall(false);
			c2.setEastWall(false);
			
		}
		
		else if (c1.getY()-c2.getY() < 0) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	
	}
	
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		if (c == null) {
			return null;
		}
		else if (c != null) {
			getUnvisitedNeighbors(c).add(c);
		}
		return null;
	}
}


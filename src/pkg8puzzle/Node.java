/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import static java.lang.Math.abs;
import java.util.*;

/**
 *
 * @author yarae
 */
public final class Node {

    public List<Node> children = new ArrayList<Node>();
    public Node parent = null;
    boolean goal = false;
    public int[][] p = new int[3][3];
    int x = 0;
    int y = 0;
    int manhattan_cost = 0;
    int Euclidean_cost = 0;
    int depth;
    String move = null;
    int finalstate[][] = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    
    public Node(int p[][], String move) {
        this.p = p;
        this.move = move;
        Manhattan_Euclidean();
        
    }
    public void setdepth(int d){
        this.depth=d;
    }
    public int getdepth(){
        return depth;
    } 
    
    //clone the matrix
    void clonepuzzle(int c[][]) {
        for (int i = 0; i < p.length; i++) {
            System.arraycopy(p[i], 0, c[i], 0, p[i].length);
        }
    }
     
    
    //get X and Y coordinates of zero in the matrix
    public void getXY() {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (p[i][j] == 0) {
                    this.x = i;
                    this.y = j;
                }
            }
        }

    }
    
    //check if the next move is valid or not 
    boolean isvalid(int x, int y) {
        return (x >= 0 && x < 3 && y >= 0 && y < 3);
    }
    
    
    //Expand the node and get it's childeren with all possible moves
    public void ExpandNode() {
        getXY(); 
        move("right", x, y + 1); 
        move("left", x, y - 1);
        move("down", x + 1, y);
        move("up", x - 1, y);
       
       
       
        
    }
 
   //add children which have valid moves 
   //and set their Manhattan and Euclidean cost plus their parents cost as will 
    public void move(String move, int x1, int y1) {
        int c[][] = new int[3][3];
        clonepuzzle(c);
        if (isvalid(x1, y1)) {
            c[x][y] = c[x1][y1];
            c[x1][y1] = 0;
            Node child = new Node(c, move);
            children.add(child);
            child.parent = this;
            child.manhattan_cost +=  manhattan_cost;
            child.Euclidean_cost += Euclidean_cost;
        }

    }
//to print matrix
    public void printPuzzle() {
        for (int[] row : p) {
            System.out.println(Arrays.toString(row));
        }

    }
//check if it's the goal node or not
    public boolean myGoal() {
        if (Arrays.deepEquals(p, finalstate)) {
            return true;
        }
        return false;
    }
   //return X and Y coordintes of the misplaced cell 
    public int[] XY(int i) {
        int[] ans = new int[2];
        for (int row = 0; row < p.length; row++) {
            for (int col = 0; col < p[0].length; col++) {
                if (p[row][col] == i) {
                    ans[0] = row;
                    ans[1] = col;
                }
            }
        }
        return ans;
    }
//calculate manhattan and Euclidean cost of the node
//loops over the matrix and check if it contains the right number in the right cell
 //if not get where this number is 
    public void Manhattan_Euclidean() {
        int i = 0;
        for (int goalrow = 0; goalrow < p.length; goalrow++) {
            for (int goalcol = 0; goalcol < p[0].length; goalcol++) {
                if (p[goalrow][goalcol] != i) {
                    int[] ans = XY(i);
                    this.manhattan_cost += abs((goalrow - ans[0]) + (goalcol - ans[1]));
                    this.Euclidean_cost += Math.sqrt((goalrow - ans[0]) ^ 2 + (goalcol - ans[1]) ^ 2);
                }
                i++;
            }
        }
        
    }
    public int getmanhattan(){
        return manhattan_cost;
    }
    public int geteuclidean(){
        return Euclidean_cost;
    }
}

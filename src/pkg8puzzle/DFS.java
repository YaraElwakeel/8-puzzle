/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author yarae
 */
public class DFS {

    Stack<Node> frontier = new Stack<>();
    Set<Node> explored = new HashSet<>();
    boolean flag1 = false;
    boolean flag2 = false;
    int expanded=0;
    
    ArrayList<Node> path = new ArrayList<>();
    Node state = null;

    public ArrayList<Node> DFS(Node iNode) {
         frontier.push(iNode);
        //loop on frontier
        while (!frontier.isEmpty()) {
            state = frontier.pop();
            explored.add(state);
            //if goal is found
            if (state.myGoal()) {
                path.add(state);
                while (state.p != iNode.p) {
                    path.add(state.parent);
                    state = state.parent;
                }               
                return path;
                //goal is not found 
            } else {
                state.ExpandNode();
               expanded++;
                for (Node nNode : state.children) {
                    flag1=false;
                    flag2=false;
                    for (Node explored1 : explored) {
                        if (Arrays.deepEquals(explored1.p, nNode.p)) {
                            flag1 = true;
                            break;
                        }
                    }
                    if (!flag1) {
                    for (Node x : frontier) {
                        if (Arrays.deepEquals(x.p, nNode.p)) {
                            flag2 = true;
                            break;
                        }
                    }}
                    if (!(flag1 || flag2)) {
                        frontier.push(nNode);
                        
                    }


                }

            }
//            System.out.println("Frontier");
//            for (Node x : frontier) {
//                x.printPuzzle();
//                System.out.println();
//            }
//            System.out.println("explored");
//            for (Node y : explored) {
//                y.printPuzzle();
//                System.out.println();
//            }
        }
        
        return null;
        
    }

}

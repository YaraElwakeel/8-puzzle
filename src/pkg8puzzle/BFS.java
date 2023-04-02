/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import java.util.*;

/**
 *
 * @author yarae
 */
public class BFS {

    Queue<Node> frontier = new LinkedList<>();
    Set<Node> explored = new HashSet<>();
    boolean flag1 = false;
    boolean flag2 = false;
    int numexpanded=0;
    ArrayList<Node> path = new ArrayList<>();
    Node state = null;

    public ArrayList<Node> BFS(Node iNode) {
        iNode.setdepth(0);
        frontier.add(iNode);
        //loop on frontier 
        //dequeue a node from frontier and add it to explored list
        while (!frontier.isEmpty()) {
            state = frontier.remove();
            explored.add(state);

            //if this node is the goal state 
            //get the path of the goal by retrieving it's parents and return this path
            if (state.myGoal()) {
                path.add(state);
                while (state.p != iNode.p) {
                    path.add(state.parent);
                    state = state.parent;
                }               
                return path;
             //goal is not found 
             //get children of this node  and loop over it 
            } else {
                state.ExpandNode();
                numexpanded++;
               
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
                    //if it doesn't exist in frontier and explored add it to the frontier queue and set it's depth 
                    if (!(flag1 || flag2)) {
                        nNode.setdepth(state.getdepth()+1);
                        frontier.add(nNode); 
                    }


                }

            }
            
            
            
            //to check how the algorithm is working  
            
            
            
            
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

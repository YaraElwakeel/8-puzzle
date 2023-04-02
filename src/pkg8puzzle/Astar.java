/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author yarae
 */
public class Astar {

    Comparator<Node> manhattanSorter = Comparator.comparing(Node::getmanhattan);
    Comparator<Node> euclideanSorter = Comparator.comparing(Node::geteuclidean);
    PriorityQueue<Node> frontier;
    String htype;
    Set<Node> explored = new HashSet<>();
    boolean flag1 = false;
    boolean flag2 = false;
    int expanded = 0;
    ArrayList<Node> path = new ArrayList<>();
    Node state = null;

    public Astar(String heuristic) {
        this.htype = heuristic;
        if (null != heuristic) switch (heuristic) {
            case "manhattan":
                this.frontier = new PriorityQueue<>(manhattanSorter);
                break;
            case "Euclidean":
                this.frontier = new PriorityQueue<>(euclideanSorter);
                break;
        }

    }

    public ArrayList<Node> Astar(Node iNode) {
        iNode.setdepth(0);
        frontier.add(iNode);
        //loop on frontier 
        //remove the node with the smaller cost from frontier and adds it to explored list
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
                //get children of this node and loop over it 
            } else {
                state.ExpandNode();
                expanded++;
                for (Node nNode : state.children) {
                    
                    flag1 = false;
                    flag2 = false;
                    
                    for (Node explored1 : explored) {
                        if (Arrays.deepEquals(explored1.p, nNode.p)) {
                            flag1 = true;
                            break;
                        }
                    }
                    
                    if (!flag1) {
                        //if this node exists in frontier and has bigger cost 
                        //decrease this cost by the cost of the current node
                        for (Node x : frontier) {
                            if (Arrays.deepEquals(x.p, nNode.p)) {
                                flag2 = true;
                                if (null != htype) switch (htype) {
                                    case "manhattan":
                                        if (x.manhattan_cost > nNode.manhattan_cost) {
                                            frontier.remove(x);
                                            nNode.setdepth(state.getdepth()+1);
                                            frontier.add(nNode);
                                        }   break;
                                    case "Euclidean":
                                        if (x.Euclidean_cost > nNode.Euclidean_cost) {
                                            frontier.remove(x);
                                            nNode.setdepth(state.getdepth()+1);
                                            frontier.add(nNode);
                                        }   break;
                                }
                                break;
                            }
                        }
                    }
                    //if it doesn't exist in frontier and explored add it to the frontier queue and set it's depth 
                    if (!(flag1 || flag2)) {
                        nNode.setdepth(state.getdepth()+1);
                        frontier.add(nNode);

                    }
                }

            }
//            System.out.println("Frontier");
//            for (Node x : frontier) {
//                x.printPuzzle();
//                System.out.println(x.manhattan_cost);
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

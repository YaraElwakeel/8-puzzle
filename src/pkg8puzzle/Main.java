/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yarae
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main j = new Main();
        int intialstate[][] = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 8, 7}
        };

        Node iNode = new Node(intialstate, "no move");

        while (true) {
            System.out.println("1:BFS");
            System.out.println("2:DFS");
            System.out.println("3:A*");
            System.out.println("4:EXIT");
            ArrayList<Node> result = null;

            System.out.println("Enter your choice:");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    BFS b = new BFS();
                    long start = System.nanoTime();
                    result = b.BFS(iNode);
                    long end = System.nanoTime();
                    if (result != null) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("BFS Path :");
                        j.printpath(result);
                        System.out.println("path cost : "+ (result.size()-1));
                        System.out.println("Nodes explored :" + b.explored.size());
                        System.out.println("Nodes Expanded :" + b.numexpanded);
                        System.out.println("Running time :" + (float) (end - start) + "nanoseconds");
                        System.out.println("-------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("no path");
                    }
                    break;

                case 2:
                    DFS d = new DFS();
                    result = d.DFS(iNode);
                    if (result != null) {
                        System.out.println("DFS Path :");
                        j.printpath(result);
                    } else {
                        System.out.print("something wrong");
                    }
                    break;

                case 3:
                    Astar A = new Astar("manhattan");
                    Astar A2 = new Astar("Euclidean");
                    long start1 = System.nanoTime();
                    result = A.Astar(iNode);
                    long end1 = System.nanoTime();
                    long start2 = System.nanoTime();
                    ArrayList<Node> result1 = A2.Astar(iNode);
                    long end2 = System.nanoTime();
                    if (result != null) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Mantattan path :");
                        for (int i = result.size() - 1; i >= 0; i--) {
                            result.get(i).printPuzzle();
                            System.out.println("move : " + result.get(i).move);
                            System.out.println("Manhattan cost : " + result.get(i).getmanhattan());
                             System.out.println("Search depth : "+result.get(i).getdepth());
                            System.out.println();
                        }
                        System.out.println("path cost : "+ (result.size()-1));
                        System.out.println("Nodes explored : " + A.explored.size());
                        System.out.println("Nodes Expanded : " + A.expanded);
                        System.out.println("Running time :" + (float) (end1 - start1)  + " nanoseconds");
                        System.out.println("-------------------------------------------------------------------------------------");

                    }
                    if (result1 != null) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("Euclidean path :");
                        for (int i = result1.size() - 1; i >= 0; i--) {
                            result1.get(i).printPuzzle();
                            System.out.println("move : " + result1.get(i).move);
                            System.out.println("Euclidean cost : " + result1.get(i).geteuclidean()); 
                            System.out.println("Search depth : "+result1.get(i).getdepth());
                            System.out.println();
                        }
                        System.out.println("path cost : "+ (result1.size()-1));
                        System.out.println("Nodes explored :" + A2.explored.size());
                        System.out.println("Nodes Expanded :" + A2.expanded);
                       
                        System.out.println("Running time :" + (float) (end2 - start2) + " nanoseconds");
                        System.out.println("-------------------------------------------------------------------------------------");

                    } else {
                        System.out.print("something wrong");
                    }
                    break;
                case 4:
                    return;
            }
        }

    }

    public void printpath(ArrayList<Node> result) {
        for (int i = result.size() - 1; i >= 0; i--) {
            result.get(i).printPuzzle();
            System.out.println(result.get(i).move);
            System.out.println("Search depth : "+result.get(i).getdepth());
            System.out.println();
            
        }
    }
}

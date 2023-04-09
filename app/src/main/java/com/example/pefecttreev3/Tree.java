package com.example.pefecttreev3;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

public class Tree
{
    //variables
    private TreeNode root;
    private final Queue<TreeNode> queue = new LinkedList<>();
    private int i = 0;
    private int depth = 0;
    private int size;
    private int count = 0;
    private int publicKey;
    private String publicOp;
    //set a new count variable for the different nodes
    private int newCount = 0;
    private int tempCount = 0;
    //boolean for search method
    private static boolean found = false;

    //constructor
    public Tree()
    {
        root = null;
    }//end constructor Tree

    public TreeNode getRoot()
    {
        return root;
    }//end getRoot

    public int getValue(TreeNode node)
    {
        return node.value;
    }

    //create root
    public void addRoot(int x)
    {
        if(queue.isEmpty())
        {
            root = new TreeNode(x);
            queue.add(root);
            count = x;
            depth++;
        }//end if
        else
        {
            System.out.println("\nThis tree already has a root");
        }//end else
    }//end addRoot

    //add row
    public void addRow(int key, String operation)
    {
        publicKey = key;
        publicOp = operation;
        //variable to hold where i was at the start
        int tempi = i;

        while(!queue.isEmpty())
        {
            //create a variable size and set it to the size of queue
            size = queue.size();
            i++;
            //if i is greater than the height of the tree STOP
            if(i > depth)
            {
                //I apologize in advance for this
                //I truly tried to figure out a way to get this to work without the break
                break;
            }//end if
            else
            {
                //create a left and right child
                for(int j = 0; j < size; j++)
                {
                    count = modifyNum(count, operation);
                    //remove the current node from the queue
                    TreeNode node = queue.remove();
                    //add left child
                    node.left = new TreeNode(count);
                    count = modifyNum(count, operation);
                    //add right child
                    node.right = new TreeNode(count);


                    //add the children to the queue
                    queue.add(node.left);
                    queue.add(node.right);
                }//end for loop
            }//end else
        }//end while loop

        //sets i back to increase by 1 from where it was at the start of the call
        i = tempi + 1;
        //increases the depth by 1
        depth++;
    }//end addRow

    //needed to create a new addRow method because the first one modifies the root tree but never replaces the root tree with the new nodes
    //So this one has the same idea as the delete method, where it recreates the root and then reassigns the tree
    public void addRowV2(TreeNode temp, int key, String operation, int start)
    {
        publicKey = key;
        publicOp = operation;

        tempCount = modifyNum(start, operation);
        int newDepth = depth;
        int newi = 0;

        Queue<TreeNode> newQueue = new LinkedList<>();
        if(newDepth <= 0)
        {
            TreeNode newRoot = new TreeNode(start);
            root = newRoot;
            i = newi + 1;
            root = newRoot;
            depth = newDepth + 1;
        }//end if
        else
        {
            TreeNode newRoot = new TreeNode(temp.value);
            newQueue.add(newRoot);
            while(!newQueue.isEmpty())
            {
                int newSize = newQueue.size();
                newi++;
                if(newi > newDepth)
                {
                    break;
                }//end inner if
                else
                {
                    for(int j = 0; j < newSize; j++)
                    {
                        TreeNode node = newQueue.remove();
                        node.left = new TreeNode(tempCount);
                        tempCount = modifyNum(tempCount, operation);
                        node.right = new TreeNode(tempCount);
                        tempCount = modifyNum(tempCount, operation);

                        newQueue.add(node.left);
                        newQueue.add(node.right);
                    }//end for loop
                }//end else
            }//end while loop

            i = newi + 1;
            root = newRoot;
            depth = newDepth + 1;
        }//end else
    }//end addRowV2

    //The only way my brain could figure out how to delete a row
    //was to create a new node with one less row, then set root to row
    public void deleteRow(TreeNode temp)
    {
        newCount = modifyNum(root.value, publicOp);
        //set a depth with a value 2 less than the original depth
        int newDepth = depth - 2;

        if(newDepth > 0)
        {
            Queue<TreeNode> newQueue = new LinkedList<>();
            //create a new i variable
            int newi = 0;

            //crate a new root node and add it to the queue
            TreeNode newRoot = new TreeNode(temp.value);
            newQueue.add(newRoot);

            //while queue isn't empty fill the tree
            while(!newQueue.isEmpty())
            {
                //create a new size variable to hold the new queue size
                int newSize = newQueue.size();
                //increase new i by 1
                newi++;
                //if the newi is greater than the newdepth break
                if(newi > newDepth)
                {
                    //forgive me
                    //I tried to get rid of it, I really did
                    break;
                }//end if
                else
                {
                    //for when j is less than new size crate new nodes for the left and right branches
                    for(int j = 0; j < newSize; j++)
                    {
                        //set a current node to the first one in the queue and remove it from the queue
                        TreeNode node = newQueue.remove();
                        node.left = new TreeNode(newCount);
                        newCount = modifyNum(newCount, publicOp);
                        node.right = new TreeNode(newCount);
                        newCount = modifyNum(newCount, publicOp);

                        //add node left and node right to queue
                        newQueue.add(node.left);
                        newQueue.add(node.right);
                    }//end for loop
                }//end else
            }//end while loop

            //set the original root to the newly created one
            root = newRoot;
            depth = newDepth + 1;
        }
        else if(newDepth == 0)
        {
            TreeNode newRoot = new TreeNode(root.value);
            root = newRoot;
            depth = newDepth + 1;
        }//end else if
        else
        {
            depth = newDepth + 1;
            root = null;
        }//end else
    }//end deleteRow

    //takes the results from the searchNode and puts it into printed sentences
    public void searchNodeTest(TreeNode node, int x)
    {
        //if found
        if(searchNode(node, x) == true)
        {
            System.out.println("Value was found within the tree");
        }//end if
        else
        {
            System.out.println("Value was not found within the tree");
        }//end else
    }//end searchNodeTest

    public boolean searchNode(TreeNode node, int x)
    {
        found = false;
        //if the tree is empty print out empty
        if(root == null)
        {
            System.out.println("Tree is empty");
        }//end if
        else
        {
            //if the value is that node return true
            if(node.value == x)
            {
                found = true;
            }//end inner if
            //if not found search left node
            if(!found && node.left != null)
            {
                searchNode(node.left, x);
            }//end second inner if
            //if not found search right node
            if(!found && node.right != null)
            {
                searchNode(node.right, x);
            }//end third inner if
        }//end else

        //return found var
        return found;
    }//end searchNode

    //print out tree (goes left, root, then right)
    public void printInOrder(TreeNode node)
    {
        //if the node is null return nothing
        if(node == null)
        {
            return;
        }//end if
        //recursion to go the furthest left as possible
        printInOrder(node.left);
        //print out the value
        System.out.print(node.value + " ");
        //recursion to go the furthest right on the same level and do the same thing
        printInOrder(node.right);
    }//end printInOrder

    //Not going to lie I had to find a way that took the print out of the root and take it in and make it a variable
    //This code is fully from stack overflow https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
    public String getTreeValues(TreeNode node)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        printInOrder(root);
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }//end getTreeValues

//    public static int getCol(int h)
//    {
//        if(h == 1)
//            return 1;
//        return getCol(h - 1) + getCol(h - 1) + 1;
//    }
//
//    public void printTree(int[][]M, TreeNode root, int col, int row, int height)
//    {
//        if(root == null)
//            return;
//        M[row][col] = root.value;
//        printTree(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
//        printTree(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height -1);
//    }
//
//    public String TreePrinter(Tree tree)
//    {
//        String output = "";
//        int h = depth;
//        int col = getCol(h);
//        int[][] M = new int[h][col];
//        for(int i = 0; i < h; i++)
//        {
//            for(int j = 0; j < col; j++)
//            {
//                M[i][j] = -1;
//            }
//        }
//        printTree(M, root, col / 2, 0, h);
//        System.out.print(root.value);
//
//        for(int i = 0; i < h; i++)
//        {
//            for(int j = 0; j < col; j++)
//            {
//                if(M[i][j] == -1)
//                {
//                    output += "   ";
//                }
//                else
//                {
//                    output += M[i][j] + " ";
//                }
//            }
//            output += "\n";
//        }
//        return output;
//    }

    static final int COUNT = 13;
    String output = "";

    private void print2DUtil(TreeNode base, int space)
    {
        if(base == null)
        {
            return;
        }//end if
        space += COUNT;
        print2DUtil(base.right, space);

        output += "\n";
        for(int i = COUNT; i <= space; i++)
        {
            output += " ";
        }//end for
        output += base.value + "\n";
        print2DUtil(base.left, space);
    }//end print2DUtil

    public String print2D(TreeNode base)
    {
        output = "";
        print2DUtil(base, 0);
        return output;
    }//end print2D

    private static class TreeNode
    {
        //creates variables
        public int value;
        public TreeNode left;
        public TreeNode right;

        //constructor
        public TreeNode(int x)
        {
            value = x;
            left = null;
            right = null;
        }//end constructor TreeNode
    }//end class TreeNode

    private int modifyNum(int x, String operation)
    {
        int output;
        if(operation.equals("+"))
        {
            output = x + publicKey;
        }//end if
        else if(operation.equals("/"))
        {
            output = x / publicKey;
        }//end first else if
        else if(operation.equals("*"))
        {
            output = x * publicKey;
        }//end second else if
        else
        {
            output = x - publicKey;
        }//end else
        return output;
    }//end modifyNum
}//end Tree class

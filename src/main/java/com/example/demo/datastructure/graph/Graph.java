package com.example.demo.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 无权无向图
 *
 * @author yangjinyu
 * @time 2021/6/22 10:55
 */
public class Graph {
    private final static int MAX_VERTS = 20;
    private Vertex[] verts;//用来存储顶点的数组
    private int[][] adjMatrix;//用邻接矩阵来存储 边,数组元素0表示没有边界，1表示有边界
    private int size;//顶点个数

    /**
     * 顶点
     */
    private static class Vertex {
        public char label;
        public boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            this.wasVisited = false;
        }
    }

    public Graph() {
        //初始化顶点个数为0
        this.size = 0;
        this.verts = new Vertex[MAX_VERTS];
        this.adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        //初始化邻接矩阵所有元素都为0，即所有顶点都没有边
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                this.adjMatrix[i][j] = 0;
            }
        }
    }

    //注意用邻接矩阵表示边，是对称的，两部分都要赋值
    public void addVertex(char lab) {
        verts[size++] = new Vertex(lab);
    }

    //注意用邻接矩阵表示边，是对称的，两部分都要赋值
    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    /**
     * 深度优先搜索算法:
     * 1、用peek()方法检查栈顶的顶点
     * 2、用getAdjUnvisitedVertex()方法找到当前栈顶点邻接且未被访问的顶点
     * 3、第二步方法返回值不等于-1则找到下一个未访问的邻接顶点，访问这个顶点，并入栈
     * 如果第二步方法返回值等于 -1，则没有找到，出栈
     */
    public void depthFirstSearch() {
        if (size == 0) {
            return;
        }
        System.out.println("DFS");
        Stack<Integer> stack = new Stack<>();
        System.out.println(verts[0].label);
        verts[0].wasVisited = true;
        stack.push(0);
        while (!stack.isEmpty()) {
            int index = findNotVisited(stack.peek());// 使用stack.peek()，这步很关键
            if (index != -1) {
                System.out.println(verts[index].label);
                verts[index].wasVisited = true;
                stack.push(index);
            } else {
                stack.pop();
            }
        }

        // 遍历完毕，重置所有标记位wasVisited=false
        for (int i = 0; i < size; i++) {
            verts[i].wasVisited = false;
        }
    }


    /**
     * 广度优先搜索算法：
     * 1、用remove()方法检查栈顶的顶点
     * 2、试图找到这个顶点还未访问的邻节点
     * 3、 如果没有找到，该顶点出列
     * 4、 如果找到这样的顶点，访问这个顶点，并把它放入队列中
     */
    public void breadthFirstSearch() {
        if(size == 0){
            return;
        }
        System.out.println("BFS");
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(verts[0].label);
        verts[0].wasVisited = true;
        queue.add(0);
        while(!queue.isEmpty()){
            int index = findNotVisited(queue.peek());
            if(index != -1){
                System.out.println(verts[index].label);
                verts[index].wasVisited = true;
                queue.add(index);
            }else {
                queue.remove();
            }
        }

        // 遍历完毕，重置所有标记位wasVisited=false
        for (int i = 0; i < size; i++) {
            verts[i].wasVisited = false;
        }
    }

    private int findNotVisited(int index) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[index][i] == 1 && !verts[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    /**
     *          A —— B —— D
     *         / \    \
     *        F   C    E
     *      /
     *     G
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(0, 5);
        graph.addEdge(5, 6);
        graph.depthFirstSearch();
        graph.breadthFirstSearch();
    }
}
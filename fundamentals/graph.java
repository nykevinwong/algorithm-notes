// (1) Adjacent Matrix Represention of undirected, unweighted Graph with bfs, dfs, mst and topological sort
// (2) Adjecent List Representation of undirected, unweighted Graph with bfs, dfs, mst and topological sort

import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Stack;

class Vertex
{
    String label;
    boolean visisted;
    public Vertex(String label)
    {
        this.label = label;
    }
}

class Graph
{
    public int N;
    public Vertex vertexList[];
    public int nVertex;
    public int adjMatrix[][];

    public Graph(int N)
    {
        this.N = N;
        vertexList = new Vertex[N];
        nVertex=  0;
        adjMatrix = new int[N][N];

        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                adjMatrix[i][j]= 0;
    }

    public void AddVertex(String label)
    {
        vertexList[nVertex++] = new Vertex(label);
    }

    public void AddEdge(char src, char dest)
    {
        int s = src-'A';
        int d = dest -'A';
        adjMatrix[s][d] = 1;
        adjMatrix[d][s] = 1;
    }

    public int getAdjacentVertex(int cur)
    {
        for(int i=0;i< this.N; i++)
            if(cur != i && adjMatrix[cur][i]==1 && vertexList[i].visisted==false)
                return i;
        return -1;
    }

    public void displayVertex(int v)
    {
        System.out.print(this.vertexList[v].label);
    }

    public void reset()
    {
        for(int i=0;i<nVertex;i++)
        vertexList[i].visisted = false;
    }

    public void bfs()
    {
        System.out.println("\nbfs:");
        Queue<Integer> q = new LinkedList<Integer>();
        vertexList[0].visisted = true;
        q.add(0);
        displayVertex(0);


        while(q.size() > 0)
        {
            int cur = q.remove();
            int nextAdj;
            while( (nextAdj= getAdjacentVertex(cur)) != -1 )
            {
                displayVertex(nextAdj);
                q.add(nextAdj);
                vertexList[nextAdj].visisted = true;
            }
        }

        reset();
    }

    public void dfs()
    {
        System.out.println("\ndfs:");
        Stack<Integer> s = new Stack<Integer>();
        vertexList[0].visisted = true;
        displayVertex(0);
        s.push(0);

        while(s.size() > 0)
        {
           int cur = s.peek();
           int nextAdj=getAdjacentVertex(cur);

           if(nextAdj==-1)
               s.pop();
           else
           {
            vertexList[nextAdj].visisted = true;
            displayVertex(nextAdj);
            s.push(nextAdj);
           }
        }

        reset();
    }

    public void mst()
    {
        System.out.println("\nmst:");
        Stack<Integer> s = new Stack<Integer>();
        vertexList[0].visisted = true;
        s.push(0);

        while(s.size() > 0)
        {
           int cur = s.peek();
           int nextAdj=getAdjacentVertex(cur);

           if(nextAdj==-1)
               s.pop();
           else
           {
            vertexList[nextAdj].visisted = true;
            displayVertex(cur);
            displayVertex(nextAdj);
            System.out.print(' ');
            s.push(nextAdj);
           }
        }

        reset();

    }
}

class BFSApp
{
    public static void main(String args[])
    {
        Graph g = new Graph(20);
        g.AddVertex("A");
        g.AddVertex("B");
        g.AddVertex("C");
        g.AddVertex("D");
        g.AddVertex("E");
        g.AddVertex("F");

        g.AddEdge('A','B');
        g.AddEdge('B','C');
        g.AddEdge('A','D');
        g.AddEdge('D','E');
        g.AddEdge('A','F');

        g.bfs();
        g.dfs();

        Graph g2 = new Graph(20);
        g2.AddVertex("A");
        g2.AddVertex("B");
        g2.AddVertex("C");
        g2.AddVertex("D");
        g2.AddVertex("E");
        g2.AddVertex("F");

        // we should have more than N-1 edges for non-MST graph
        g2.AddEdge('A','B');
        g2.AddEdge('B','C');
        g2.AddEdge('A','D');
        g2.AddEdge('D','E');
        g2.AddEdge('A','F');
        g2.AddEdge('A','C');
        g2.AddEdge('F','E');
        g2.mst();
    }
}

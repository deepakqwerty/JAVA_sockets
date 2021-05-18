// Made by Deepak Joshi
// Roll No - 1810110060


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server
{
    public int v;
    private ArrayList<Integer>[] adjList;
    public static int[][] m = new int[40][40];
    public static int[] kl = new int[6];

    public Server(int vertices)
    {
        v = vertices;
        initAdjList();
    }
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    public void addEdge(int u, int v)
    {
        adjList[u].add(v);
    }
    public void printAllPaths(int s, int d,int y,int g)
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        printAllPathsUtil(s, d,  y, g, isVisited, pathList);
    }
    private void printAllPathsUtil(Integer u, Integer d,Integer ui, Integer hj, boolean[] isVisited,
                                   List<Integer> localPathList)
    {
        int ir,kr,mr,gr,tr,lr;
        if (u.equals(d))
        {
            lr=0;
            for (mr = 0; mr < localPathList.size()-1; mr++)
            {
                gr = localPathList.get(mr);
                tr = localPathList.get(mr + 1);
                for (ir = 0; ir < hj; ir++)
                {
                    for (kr = 0; kr < hj; kr++)
                    {
                        if (m[ir][kr] == 1 && ir == gr && kr == tr)
                        {
                            lr = lr + m[ir][kr];
                        }
                    }
                }
            }
            if(lr==ui)
            {
                System.out.println(" Yes there is a path ");
                System.out.print(" Path cost is = ");
                System.out.println(ui);
                System.out.print(" Path is = ");
                System.out.println(localPathList);
                kl[0]=1;

            }
        }
        isVisited[u] = true;
        for (Integer iy : adjList[u]) {
            if (!isVisited[iy]) {
                localPathList.add(iy);
                printAllPathsUtil(iy, d, ui, hj, isVisited, localPathList);
                localPathList.remove(iy);
            }
        }
        isVisited[u] = false;
    }
    public static void main(String[] args) throws Exception
    {
        int it,j,n,or,sr,dr;
        ServerSocket ss=new ServerSocket(6666);
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream doubt=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str="";
        int ig;
        ig=0;

        while(ig<1)
        {
            str=din.readUTF();
            System.out.println("client says: "+str);
            ig=ig+1;
        }

        int ty;

        n=Integer.parseInt(String.valueOf(str.charAt(0)));
        System.out.println(n);
        System.out.println(str.charAt(n*n+n+2));
        System.out.println(str.charAt(n*n+n+4));
        System.out.println(str.charAt(n*n+n+6));
        or= Integer.parseInt(String.valueOf(str.charAt(n*n+n+2)));
        sr= Integer.parseInt(String.valueOf(str.charAt(n*n+n+4)));
        dr= Integer.parseInt(String.valueOf(str.charAt(n*n+n+6)));
        String[] phones = str.split(" ");
        Server g = new Server(n);
        it=1;
        ty=1;
        for(it=1;it<=n;it++)
        {
            for (j = 0; j < n; j++)
            {
                m[it - 1][j] = Integer.parseInt(String.valueOf(phones[it].charAt(j)));

            }
            ty = ty + 1;

        }

        for(it=0;it<n;it++)
        {
            for(j=0;j<n;j++)
            {
                System.out.print(m[it][j]);
            }
            System.out.println(" ");
        }

        for(it=0;it<n;it++)
        {
            for(j=0;j<n;j++)
            {
                if(m[it][j]==1)
                {
                    g.addEdge(it, j);
                }
            }
        }
        int yj;
        System.out.println(" Following is path from " + sr + " to " + dr + " with the desired cost "+ or);
        g.printAllPaths(sr, dr, or, n);
        if(kl[0]==1)
        {
            doubt.writeUTF(" YES there is a path ");
            doubt.flush();
        }
        else {
            doubt.writeUTF(" NO there is no path ");
            doubt.flush();
        }

        din.close();
        s.close();
        ss.close();
    }
}



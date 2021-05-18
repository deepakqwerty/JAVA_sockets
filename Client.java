// Made by Deepak Joshi
// Roll No - 1810110060


import java.net.*;
import java.io.*;
public class Client
{
    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("localhost", 6666);
        DataOutputStream doubt = new DataOutputStream(s.getOutputStream());
        DataInputStream din=new DataInputStream(s.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" Enter input in the following format ");
        System.out.println(" first number of nodes then space matrix with each row after a space ");
        System.out.println(" then the cost of path to find after a space  ");
        System.out.println(" then node to start after a space and ");
        System.out.println(" then final node after a space ");
        System.out.println(" Example ");
        System.out.println("3 010 101 000 2 0 2");
        System.out.println(" After that press enter to see output ");
        String str = "", str2 = "";
        int ig;
        ig=0;
        while (ig<1) {
            str = br.readLine();
            doubt.writeUTF(str);
            doubt.flush();
            ig = ig + 1;
        }
        str=din.readUTF();
        System.out.println(" Server says: "+str);
        doubt.close();
        s.close();
    }
}


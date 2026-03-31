import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int[] leftChild;
    static int[] rightChild;
    static StringBuilder sb1 = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();

    static StringBuilder sb3 = new StringBuilder();

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        leftChild = new int[N];
        rightChild = new int[N];
        Arrays.fill(leftChild, -1);
        Arrays.fill(rightChild, -1);

        for(int i = 0; i < N; i++){
            char[] chArr = br.readLine().toCharArray();
            int parent = chArr[0] - 65;
            char left = chArr[2];
            char right = chArr[4];

            if(left != '.'){
                leftChild[parent] = left - 65;
            }

            if(right != '.'){
                rightChild[parent] = right - 65;
            }
        }

        post(0);
        mid(0);
        rear(0);
        // System.out.println(Arrays.toString(leftChild));
        //         System.out.println(Arrays.toString(rightChild));
        System.out.println(sb1.toString());
                System.out.println(sb2.toString());

                System.out.println(sb3.toString());


    }

    static void post(int start){
        sb1.append((char)(start + 65));
        if(leftChild[start] != -1){
            post(leftChild[start]);
        }

        if(rightChild[start] != -1){
            post(rightChild[start]);
        }
    }
    static void mid(int start){
        
        if(leftChild[start] != -1){
            mid(leftChild[start]);
        }
        sb2.append((char)(start + 65));
        if(rightChild[start] != -1){
            mid(rightChild[start]);
        }
    }
    static void rear(int start){
        
        if(leftChild[start] != -1){
            rear(leftChild[start]);
        }

        if(rightChild[start] != -1){
            rear(rightChild[start]);
        }
        sb3.append((char)(start + 65));
    }
}
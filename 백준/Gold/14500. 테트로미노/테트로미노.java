import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[][] grid;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        
        grid = new int[N][M];
        for(int row = 0; row < N; row++){
            String[] s1 = br.readLine().split(" ");
            for(int col = 0; col < M; col++){
                grid[row][col] = Integer.parseInt(s1[col]);
            }
        }
        
        
        f1();
        f2();
        f3();
        f4();
        f5();
        f6();
        f7();
        f8();
        f9();
        f10();
        f11();
        f12();
        f13();
        f14();
        f15();
        f16();
        f17();
        f18();
        f19();
        
        System.out.println(answer);
        
    }
    public static void cp(int total){
        answer = Math.max(answer, total);
    }
    public static void f1(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0; col < M - 1; col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row][col + 1] + grid[row + 1][col + 1];
                cp(total);
            }
        }
    }
    public static void f2(){
        for(int row = 0; row < N - 3; row++){
            for(int col = 0 ; col < M ;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 2][col] + grid[row + 3][col];
                cp(total);
            }
        }
    }
    public static void f3(){
        for(int row = 0; row < N; row++){
            for(int col = 0 ; col < M - 3;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row][col + 2] + grid[row][col + 3];
                cp(total);
            }
        
        }
    }
    public static void f4(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 0 ; col < M - 1;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 2][col] + grid[row + 2][col + 1];
                cp(total);
            }
        }
    }
    public static void f5(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 1 ; col < M ;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 2][col] + grid[row + 2][col - 1];
                cp(total);
            }
        }
        
    }
    public static void f6(){
        for(int row = 1; row < N; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row][col + 2] + grid[row - 1][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f7(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row][col + 2] + grid[row + 1][col + 2];
                cp(total);
            }
        }
    }
    public static void f8(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 1][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f9(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row][col + 1] + grid[row][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f10(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 0 ; col < M - 1;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 2][col] + grid[row][col + 1];
                cp(total);
            }
        }
        
    }
    public static void f11(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 0 ; col < M - 1;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 2][col + 1];
                cp(total);
            }
        }
        
    }
    public static void f12(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 0 ; col < M - 1;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 2][col + 1];
                cp(total);
            }
        }
        
    }
    public static void f13(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 1 ; col < M;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 1][col - 1] + grid[row + 2][col - 1];
                cp(total);
            }
        }
        
    }
    public static void f14(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 1][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f15(){
        for(int row = 1; row < N; row++){
            for(int col = 0 ; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row - 1][col + 1] + grid[row - 1][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f16(){
        for(int row = 0; row < N - 1; row++){
            for(int col = 0; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row + 1][col + 1] + grid[row][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f17(){
        for(int row = 1; row < N - 1; row++){
            for(int col = 0; col < M - 1;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row - 1][col + 1] + grid[row + 1][col + 1];
                cp(total);
            }
        }
        
    }
    public static void f18(){
        for(int row = 1; row < N; row++){
            for(int col = 0; col < M - 2;col++){
                int total = grid[row][col] + grid[row][col + 1] + grid[row - 1][col + 1] + grid[row][col + 2];
                cp(total);
            }
        }
        
    }
    public static void f19(){
        for(int row = 0; row < N - 2; row++){
            for(int col = 0; col < M - 1;col++){
                int total = grid[row][col] + grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 2][col];
                cp(total);
            }
        }
    }
    
}
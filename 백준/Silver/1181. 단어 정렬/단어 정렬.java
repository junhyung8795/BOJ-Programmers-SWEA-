import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N ; i++){
            set.add(br.readLine());
        }
        String[] array = set.toArray(new String[set.size()]);
        Arrays.sort(array, (a,b) -> {if (a.length() != b.length()) return a.length() - b.length(); return a.compareTo(b);});
        for (int i = 0; i < array.length ; i++){
            bw.write(array[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
package P1;

import java.io.*;
import java.util.*;

public class MagicSquare {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        boolean judge;
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        judge=isLegalMagicSquare(".\\src\\P1\\1.txt");
        System.out.println(judge);
        judge=isLegalMagicSquare(".\\src\\P1\\2.txt");
        System.out.println(judge);
        judge=isLegalMagicSquare(".\\src\\P1\\3.txt");
        System.out.println(judge);
        judge=isLegalMagicSquare(".\\src\\P1\\4.txt");
        System.out.println(judge);
        judge=isLegalMagicSquare(".\\src\\P1\\5.txt");
        System.out.println(judge);
        
        judge=generateMagicSquare(n);
        if (judge) judge=isLegalMagicSquare(".\\src\\P1\\6.txt");
        System.out.println(judge);
        
        input.close();
    }
    public static boolean isLegalMagicSquare(String pathname) {
        int N=0;
        int n=0;
        String[] s=new String[] {};
        int[][] num=new int[1005][1005];
        int[] sum1=new int[1005];
        int[] sum2=new int[1005];
        String line="";
        int sum3=0,sum4=0;
        try {
            File filename = new File (pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            //br.close();
            while ((line=br.readLine()) != null) {
                s=line.split("\t");
                if (N==0) n=s.length;
                if (s.length!=n) return false;
                for (int i=0;i<s.length;i++) {
                    for (int j=0;j<s[i].length();j++) 
                        if (s[i].charAt(j)==' ' || s[i].charAt(j)=='.') return false;
                    num[N][i]=Integer.valueOf(s[i]);
                    if (num[N][i]<0) return false;
                }
                N=N+1;
            }
            br.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        if (N!=n) return false;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                sum1[i]+=num[i][j];
                sum2[j]+=num[i][j];
                if (i==j) sum3+=num[i][j];
                if (i+j==N-1) sum4+=num[i][j];
            }
        }
        if (sum3!=sum4) return false;
        for (int i=0;i<N;i++) {
            if (sum1[i]!=sum3) return false;
            if (sum2[i]!=sum3) return false;
        }
        return true;
    }
    
    public static boolean generateMagicSquare(int n) {
        if (n % 2 == 0) return false;
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0) row++;
            else {
                if (row == 0) row = n - 1;
                else row--;
                if (col == (n - 1)) col = 0;
                else col++;
            }
        }
        try {
            File writeName = new File(".\\src\\P1\\6.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)) {
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) out.write(magic[i][j] + "\t");
                    out.write("\n");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
}

package P1;

import java.io.*;
import java.util.*;

public class MagicSquare {
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        boolean judge;
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        try{
            judge=isLegalMagicSquare(".\\src\\P1\\1.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(false);
        }
        
        try {
            judge=isLegalMagicSquare(".\\src\\P1\\2.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(false);
        }
        
        try {
            judge=isLegalMagicSquare(".\\src\\P1\\3.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(false);
        }
        
        try {
            judge=isLegalMagicSquare(".\\src\\P1\\4.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(false);
        }
        
        try {
            judge=isLegalMagicSquare(".\\src\\P1\\5.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(false);
        }
        
        try{
            judge=generateMagicSquare(n);
            judge=isLegalMagicSquare(".\\src\\P1\\6.txt");
            System.out.println(judge);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        input.close();
    }
    public static boolean isLegalMagicSquare(String pathname) throws Exception{
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
                for (int i=0;i<s.length;i++) {
                    for (int j=0;j<s[i].length();j++) {
                        if (s[i].charAt(j)==' ') {
                            throw new Exception("Separator Error!");
                        }
                        if (s[i].charAt(j)=='.') {
                            throw new Exception("Error: Decimal!");
                        }
                    }
                    num[N][i]=Integer.valueOf(s[i]);
                    if (num[N][i]<0) {
                        throw new Exception("Error: Negatuve Number!");
                    }
                }
                if (s.length!=n) {
                    throw new Exception("Not a Matrix!");
                }
                N=N+1;
            }
            br.close();
        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        if (N!=n) {
            throw new Exception("Not a Matrix!");
        }
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                sum1[i]+=num[i][j];
                sum2[j]+=num[i][j];
                if (i==j) sum3+=num[i][j];
                if (i+j==N-1) sum4+=num[i][j];
            }
        }
        if (sum3!=sum4) {
            throw new Exception("Numerical Error!");
        }
        for (int i=0;i<N;i++) {
            if (sum1[i]!=sum3 || sum2[i]!=sum3) {
                throw new Exception("Numerical Error!");
            }
        }
        return true;
    }
    
    public static boolean generateMagicSquare(int n) throws Exception{
        
        //判断输入是否符合要求
        if (n % 2 == 0) {
            throw new Exception("Please enter an odd number!");
        }
        
        
        //构造幻方
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0) row++;   //若已填入数字，向下移动一行继续填写
            else {
                if (row == 0) row = n - 1;   //若行超出，则循环
                else row--;
                if (col == (n - 1)) col = 0;   //若列超出，则循环
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

package ex04;


import java.util.Scanner;

public class Program {
    final static int MAX_UNIQUE_BMP_CODE = 65535;
    final static int MAX_IN_THE_TOP_CHARS = 10;
    public static void main(String[] args){
        Scanner myScanner= new Scanner(System.in);
        String currentStr="";
        if(myScanner.hasNext()){
            currentStr=myScanner.nextLine();
        } else {
            System.exit(-1);
        }
        char[] charStr=currentStr.toCharArray();
        short[] code= new short[MAX_UNIQUE_BMP_CODE];
        for(int i=0;i<currentStr.length();i++){
            code[(int)charStr[i]]++;
        }
        myScanner.close();
        int[][] map=saveUniqueCode(code);
        sortLineInMatrix(map);
        int[][] mapTop10=createTop(map);
        printResult(mapTop10);
    }
    public static int[][] createTop(int[][] map){
        if(map.length<MAX_IN_THE_TOP_CHARS)  return map;
        int[][] newMap = new int[MAX_IN_THE_TOP_CHARS][2];
        for(int i=0;i<MAX_IN_THE_TOP_CHARS;i++){
            newMap[i][0]=map[i][0];
            newMap[i][1]=map[i][1];
        }
        return newMap;
    }
    public static int[][] saveUniqueCode(short[] code){
        int numUniqueElem=countNumOfUniqueChar(code);
        System.out.print("NUM of unique code: "+numUniqueElem+"\n");
        int [][] map = new int[numUniqueElem][2];
        int iteratorMap=0;
        for(int i=0;i<code.length;i++){
            if(code[i]!=0) {
                map[iteratorMap][0]=i;
                map[iteratorMap][1]=code[i];
                iteratorMap++;
            }
        }
        return map;
    }

    public static int countNumOfUniqueChar(short[] code){
        int result=0;
        for(int i=0;i<code.length;i++){
            if(code[i]!=0) result++;
        }
        return result;
    }

    public static void sortLineInMatrix(int [][] map){
        int code=0;
        int numOfChar=0;
        for(int i=0;i<map.length-1;i++){
            for(int j=i+1;j < map.length;j++){
                if(map[i][1]<map[j][1]){
                    code=map[i][0];
                    numOfChar=map[i][1];
                    map[i][0]=map[j][0];
                    map[i][1]=map[j][1];
                    map[j][0]=code;
                    map[j][1]=numOfChar;
                }
            }
        }
    }
    public static void printResult(int[][] map){
        int max=map[0][1];
        int[] arrayOfHigh=new int[map.length];
        for(int i=0;i<arrayOfHigh.length;i++) {
            arrayOfHigh[i] = (int)(map[i][1] * 10 / max);
        }
        for(int i=10;i>=0;i--){
            for(int j=0;j < map.length && arrayOfHigh[j]>=i ;j++){
                if(arrayOfHigh[j]==i){
                    System.out.printf("%4d",map[j][1]);
                } else {
                    System.out.print("   #");
                }
            }
            System.out.print('\n');
        }
        for(int i=0;i<map.length;i++){
            System.out.printf("%4c",(char)map[i][0]);
        }
    }
}

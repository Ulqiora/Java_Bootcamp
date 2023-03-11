package ex03;
import java.util.Scanner;
import java.lang.String;

public class Program {
    static int MAX_NUMBER_OF_WEEKS=18;
    static int MAX_NUMBER_OF_TESTS=5;
    public static void main(String[] args){
        String lineWeek;
        short numLastWeek=0;
        long marks=0;

        Scanner myScanner=new Scanner(System.in);
        for(int i=0;i<MAX_NUMBER_OF_WEEKS;i++){
            if(myScanner.hasNext()) {
                lineWeek = myScanner.next();
                if (lineWeek.equals("42")) break;
            }
            if(myScanner.hasNextShort()){
                numLastWeek=myScanner.nextShort();
                marks+=(getLowestMarksByWeek(myScanner)*Math.pow(10,numLastWeek-1));
            } else {
                System.exit(-1);
            }
        }
        myScanner.close();
        printStatistic(marks,numLastWeek);
    }
    static long getLowestMarksByWeek(Scanner myScanner){
        int minimum=10, temp;
        for(int j=0;j<MAX_NUMBER_OF_TESTS;j++){
            if(!myScanner.hasNextInt()){
                System.exit(-1);
            }
            temp = myScanner.nextInt();
            if(temp<minimum) minimum=temp;
        }
        return minimum;
    }

    public static void printStatistic(long marks,int numLastWeek){
        for(int i=1;i<=numLastWeek;i++){
            System.out.print("Week "+i+' ');
            int minimumOfWeek=(int)(marks%10);
            marks/=10;
            for(int j=0;j<minimumOfWeek;j++){
                System.out.print('=');
            }
            System.out.print(">\n");
        }
    }
}

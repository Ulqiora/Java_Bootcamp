package ex01;
import java.lang.String;
import java.util.Scanner;
public class Program {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int value = in.nextInt();
        if(!isCorrect(value)){
            System.out.print("Illegal Argument");
            System.exit(-1);
        }
        System.out.print(isPrimeNumber(value)+" "+numOfIteration(value));
        in.close();
    }

    public static boolean isPrimeNumber(int value){
        for(int i=2;i<=Math.sqrt(value);i++){
            if((value % i) == 0) {
                return false;
            }
        }
        return true;
    }
    public static int numOfIteration(int value){
        int i;
        for(i = 2 ;i <= (int)Math.sqrt(value); i++){
            if((value % i) == 0) break;
        }
        return i-1;
    }
    public static boolean isCorrect(int value){
        if (value > 1) return true;
        return false;
    }
}

package ex02;


import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner myScanner= new Scanner(System.in);
        int value = myScanner.nextInt(), numOfRequest=0;
        while(value != 42){
            value = calcSumOfDigit(value);
            if(isPrimeNumber(value)) numOfRequest++;
            value = myScanner.nextInt();
        }
        System.out.print("Count of coffee-request – " + numOfRequest);
        myScanner.close();
    }

    public static boolean isPrimeNumber(int value){
        for(int i=2;i<=Math.sqrt(value);i++){
            if((value % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public static int calcSumOfDigit(int value){
        int sum = 0;
        while(value != 0){
            sum += value % 10;
            value /= 10;
        }
        return  sum;
    }
}

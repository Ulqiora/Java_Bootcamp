package edu.school21.numbers;

public class NumberWorker {
    static class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String message) {
            super(message);
        }
    }
    public int digitsSum(int number) {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public boolean isPrime(int number){
        if(number<2) throw new IllegalNumberException("Incorrect number!!");
        for(int i=2;i<=Math.sqrt(number);i++){
            if((number % i) == 0)  return false;
        }
        return true;
    }
}

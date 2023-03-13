package ex02;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
public class GenerateRandomArray {
    private static final Random random = new Random();
    static public int @NotNull [] NewArray(int size, int lower, int upper){
        int topCase=upper-lower;
        int[] array = new int[size];
        for(int i=0;i<size;i++){
            array[i]=random.nextInt(topCase)+lower;
        }
        return array;
    }
}

package ex02;

import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner myScanner= new Scanner(System.in);
        try{
            checkArgs(args);
            System.out.println(FileManager.getInstance().getCurrentPath().toString());
            while(commandWait(myScanner)){}
            myScanner.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
            myScanner.close();
            System.exit(-1);
        }
    }
    private static void checkArgs(String[] args){
        if(args.length!=1) throw new IllegalArgumentException("Number of arguments is incorrect");
        if(!args[0].contains("--current-folder=")) throw new IllegalArgumentException("Argument is incorrect");
        FileManager.getInstance().setCurrentPath(args[0].substring("--current-folder=".length()));
    }

    private static boolean commandWait(Scanner myScanner){
        try {
            String command = myScanner.nextLine();
            String[] commandWithArg = command.split("\\s+");
            if (commandWithArg.length == 1 && "exit".equals(commandWithArg[0])) {
                return false;
            } else if (commandWithArg.length == 1 && "ls".equals(commandWithArg[0])) {
                FileManager.getInstance().commandLS();
            } else if (commandWithArg.length == 2 && "cd".equals(commandWithArg[0])) {
                FileManager.getInstance().commandCD(commandWithArg[1]);
            } else if (commandWithArg.length == 3 && "mv".equals(commandWithArg[0])) {
                FileManager.commandMV(commandWithArg[1], commandWithArg[2]);
            } else {
                System.out.println("UNKNOWN COMMAND");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return true;
    }
}

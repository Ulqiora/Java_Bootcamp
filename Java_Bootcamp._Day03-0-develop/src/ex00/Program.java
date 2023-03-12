package ex00;

public class Program {
    public static void main(String[] args){
        try{
            checkArgs(args);
            int count = Integer.parseInt(args[0].substring("--count=".length()));
            Egg egg = new Egg(count);
            Hen hen = new Hen(count);
            egg.start();
            hen.start();
            for (int i = 0; i < count; i++) System.out.println("Human");
            egg.join();
            hen.join();
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private static void checkArgs(String[] args){
        if(args.length != 1 && (!args[0].startsWith("--count=")))
            throw new IllegalArgumentException("Incorrect argument!");
    }
}

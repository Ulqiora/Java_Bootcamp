package ex01;

public class Program {
    public static void main(String[] args){
        try{
            checkArgs(args);
            int count=Integer.parseInt(args[0].substring("--count=".length()));
            if(count < 1) throw new IllegalArgumentException("Incorrect argument!");
            DataQueue dq=new DataQueue(4);
            MessageGenerator egg=new MessageGenerator(count,"Egg",dq);
            MessageGenerator hen=new MessageGenerator(count,"Hen",dq);
            PrinterConsumer pc=new PrinterConsumer(dq);
            Thread thEgg=new Thread(egg);
            Thread thHen=new Thread(hen);
            Thread thPC=new Thread(pc);
            thEgg.start();
            Thread.sleep(100);
            thHen.start();
            Thread.sleep(1000);
            thPC.start();
            thHen.join();
            thEgg.join();
            while(!dq.isEmpty());
            pc.stop();
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private static void checkArgs(String[] args){
        if(args.length!=1&& (!args[0].startsWith("--count=")))
            throw new IllegalArgumentException("Incorrect argument!");
    }
}

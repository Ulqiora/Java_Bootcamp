package ex05;

public class Program {
    public static void main(String[] args) {
        try {
            Menu menu =new Menu();
            menu.start();
        }catch (Exception e){
            System.err.println(e);
            System.exit(-1);
        }
    }
}

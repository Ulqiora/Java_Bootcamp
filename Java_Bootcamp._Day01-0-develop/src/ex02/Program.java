package ex02;

public class Program {
    public static void main(String[] args) {
        User jcraster = new User("Andrey", 1000);
        User adough = new User("Vlad", 1000);
        User bfile = new User("Irina", 1000);
        User wsulu = new User("Nikita", 1000);
        User dpoliwhi = new User("Evgeniy", 1000);
        User phawkgir = new User("Mihail", 1000);
        UsersList data = new UserArrayList();
        data.addUser(jcraster);
        data.addUser(adough);
        data.addUser(bfile);
        data.addUser(wsulu);
        data.addUser(dpoliwhi);
        data.addUser(phawkgir);
        try {
            System.out.println(data.getSize());
            System.out.println(data.getUser(5));
            System.out.println(data.getUserID(5));
            System.out.println(data.getUserID(3));
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }
}

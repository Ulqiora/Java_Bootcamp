package ex00;

public class Program {
    public static void main(String[] args) {
        User jcraster = new User("Andrey", 10000);
        User adough = new User("Vlad", 7900);
        System.out.println(jcraster.toString());
        System.out.println(adough.toString());
        Transaction firstTransaction = new Transaction(jcraster, adough, Transaction.TransferCategory.Debits, 1000);
        Transaction secondTransaction = new Transaction(adough, jcraster, Transaction.TransferCategory.Credit, 1000);
        System.out.println(jcraster.toString());
        System.out.println(adough.toString());
        System.out.println(firstTransaction.toString());
        System.out.println(secondTransaction.toString());
    }
}

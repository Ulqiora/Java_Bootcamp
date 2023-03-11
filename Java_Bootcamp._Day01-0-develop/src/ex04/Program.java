package ex04;

public class Program {
    public static void main(String[] args) {
        try {
            User jcraster = new User("Andrey", 10000);
            User adough = new User("Vlad", 7900);
            TransactionsService ts = new TransactionsService();
            ts.addUser(jcraster);
            ts.addUser(adough);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 1000);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 100);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 200);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 400);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 600);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 800);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 300);
            ts.exeTransactions(adough.getIdentifier(), jcraster.getIdentifier(), 200);
            ts.DeleteTransactionByUser(jcraster.getIdentifier(),ts.getUsersList().getUserID(jcraster.getIdentifier()).getTransactionsList().getTransaction(0).getIdentifier());
            ts.DeleteTransactionByUser(jcraster.getIdentifier(),ts.getUsersList().getUserID(jcraster.getIdentifier()).getTransactionsList().getTransaction(0).getIdentifier());
            ts.DeleteTransactionByUser(jcraster.getIdentifier(),ts.getUsersList().getUserID(jcraster.getIdentifier()).getTransactionsList().getTransaction(0).getIdentifier());
            ts.DeleteTransactionByUser(jcraster.getIdentifier(),ts.getUsersList().getUserID(jcraster.getIdentifier()).getTransactionsList().getTransaction(0).getIdentifier());
            TransactionsLinkedList tll=ts.GetInvalidTransactions();
            for(int i=0;i<tll.getSize();i++){
                System.out.println(tll.getTransaction(i));
            }
        }catch (Exception e){
            System.err.println(e);
            System.exit(-1);
        }
    }
}

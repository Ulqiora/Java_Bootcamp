package ex03;

public class Program {
    public static void main(String[] args) {
        User jcraster = new User("Andrey", 10000);
        User adough = new User("Vlad", 7900);
        Transaction transaction1 = new Transaction(jcraster, adough, Transaction.TransferCategory.Debits, 1000);
        Transaction transaction2 = new Transaction(adough, jcraster, Transaction.TransferCategory.Credit, 1000);
        Transaction transaction3 = new Transaction(jcraster, adough, Transaction.TransferCategory.Debits, 1000);
        Transaction transaction4 = new Transaction(adough, jcraster, Transaction.TransferCategory.Credit, 1000);
        TransactionsList dataTrans = new TransactionsLinkedList();
        try {
            dataTrans.addTransaction(transaction1);
            dataTrans.addTransaction(transaction2);
            dataTrans.addTransaction(transaction3);
            dataTrans.addTransaction(transaction4);
            System.out.println(dataTrans.getTransaction(transaction1.getIdentifier()).toString());

//            System.out.println(dataTrans.getTransaction(transaction4.getIdentifier()).toString());
//            dataTrans.deleteTransaction(transaction1.getIdentifier().toString());
//            dataTrans.deleteTransaction(transaction2.getIdentifier().toString());
//            dataTrans.deleteTransaction(transaction3.getIdentifier().toString());
//            dataTrans.deleteTransaction(transaction4.getIdentifier().toString());
            Transaction[] res = dataTrans.toArray();
            for (Transaction i : res) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}

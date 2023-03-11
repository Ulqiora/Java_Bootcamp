package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static String START_MENU="1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user\n" +
            "5. DEV – remove a transfer by ID\n" +
            "6. DEV – check transfer validity\n" +
            "7. Finish execution\n";

    private TransactionsService ts = new  TransactionsService();
    public void start(){
        Scanner myScanner = new Scanner(System.in);
        int command;
        boolean cycle=true;
        while (cycle){
            System.out.print(START_MENU);
            if(!myScanner.hasNextInt()) continue;
            command=myScanner.nextInt();
            switch (command){
                case 1 -> AddUserConsole(myScanner);
                case 2 -> UserBalanceConsole(myScanner);
                case 3 -> TransferConsole(myScanner);
                case 4 -> AllUserTransactionsConsole(myScanner);
                case 5 -> Remove(myScanner);
                case 6 -> TransferVaidityConsole(myScanner);
                case 7 -> cycle=false;
            }
            System.out.println("---------------------------------------");
        }
        myScanner.close();
    }

    private void TransferVaidityConsole(Scanner myScanner) {
        try {
            TransactionsLinkedList tll = ts.GetInvalidTransactions();
            if(tll.getSize()==0){
                System.out.println("Invalid transfer is not founded");
                return;
            }
            System.out.println("Check results:");
            for(int i=0;i<tll.getSize();i++){
                Transaction tr=tll.getTransaction(i);
                User rec=tr.getRecipient(),sen=tr.getSender();
                System.out.println( sen.getName()+"(id="+sen.getIdentifier()
                                    +") has an unacknowledged transfer id = "+tr.getIdentifier()+" from "+
                                    rec.getName()+"(id="+rec.getIdentifier()
                                    + ") for " + tr.getAmount()*(tr.getType()==Transaction.TransferCategory.Debits?1:(-1)));
            }
        }catch (Exception e){

        }
    }

    private void Remove(Scanner myScanner) {
        System.out.println("Enter a user ID and a transfer ID");
        if (!myScanner.hasNextInt()) {
            System.out.println("ERRORRRRRRRRRRRR!");
            return;
        }
        int id=myScanner.nextInt();
        try{
            String temp = myScanner.next();
            UUID uuid=UUID.fromString(temp);
            Transaction tr=ts.getUsersList().getUserID(id).getTransaction(uuid);
            System.out.println(   "Transfer To "+tr.getSender().getName()+"(id="+tr.getSender().getIdentifier()+") "+
                                tr.getAmount()*(tr.getType()==Transaction.TransferCategory.Debits?1:(-1)) + " removed");
            ts.DeleteTransactionByUser(id,uuid);
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private void AllUserTransactionsConsole(Scanner myScanner) {
        System.out.println("Enter a user ID");
        if (!myScanner.hasNextInt()) {
            System.out.println("ERRORRRRRRRRRRRR!");
            return;
        }
        Integer id = myScanner.nextInt();
        try{
            User user=ts.getUsersList().getUserID(id);
            for(int i=0;i<user.getTransactionsList().getSize();i++){
                Transaction tr=user.getTransactionsList().getTransaction(i);
                System.out.print("To "+tr.getSender().getName()+"(id="+tr.getSender().getIdentifier()+") ");
                System.out.println(tr.getAmount()*(tr.getType()==Transaction.TransferCategory.Debits?1:(-1)) +" with id = "+tr.getIdentifier().toString());
            }
        } catch (Exception e){
            System.err.println("User is not founded");
        }
    }

    private void TransferConsole(Scanner myScanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int recipientId,senderId,amount;
        if (!myScanner.hasNextInt()) {
            System.err.println("Incorrect input!");
            return;
        }
        senderId = myScanner.nextInt();
        if (!myScanner.hasNextInt()) {
            System.err.println("Incorrect input!");
            return;
        }
        recipientId = myScanner.nextInt();
        if (!myScanner.hasNextInt()) {
            System.err.println("Incorrect input!");
            return;
        }
        amount = myScanner.nextInt();
        try {
            ts.exeTransactions(recipientId,senderId,amount);
            System.out.println("The transfer is completed");
        } catch (Exception e){
            System.err.println("The transfer is not completed");
        }
    }

    private void UserBalanceConsole(Scanner myScanner) {
        System.out.println("Enter a user ID");
        if (!myScanner.hasNextInt()) {
            System.out.println("ERRORRRRRRRRRRRR!");
            return;
        }
        Integer id = myScanner.nextInt();
        try {
            User user=ts.getUsersList().getUserID(id);
            System.out.println(user.getName()+" - "+user.getBalance());
        } catch (Exception e){
            System.err.println("User is not founded!");
        }
    }

    private void AddUserConsole(Scanner myScanner) {
        while(true) {
            System.out.println("Enter a user name and a balance");
            String name = myScanner.next();
            if (!myScanner.hasNextInt()) {
                System.out.println("ERRORRRRRRRRRRRR!");
                continue;
            }
            int balance = myScanner.nextInt();
            User user = new User(name, balance);
            ts.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
            break;
        }
    }
}

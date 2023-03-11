package ex04;

import java.util.Objects;
import java.util.UUID;

public class TransactionsService {
    public UsersList getUsersList() {
        return usersList;
    }

    private UsersList usersList = new UserArrayList();
    public void addUser(User user){
        usersList.addUser(user);
    }
    public Integer getUserBalance(Integer id) throws UserNotFoundException {
        return usersList.getUserID(id).getBalance();
    }
    public void exeTransactions(Integer recipientID, Integer senderID, int amount) throws Exception {
        if(Objects.equals(recipientID, senderID)) throw new Exception("recipientID equal senderID");
        User recipient = usersList.getUserID(recipientID);
        User sender = usersList.getUserID(senderID);
        Transaction debit = new Transaction(recipient, sender, Transaction.TransferCategory.Debits, amount);
        Transaction credit = new Transaction(sender, recipient, Transaction.TransferCategory.Credit, amount);
        debit.setIdentifier(credit.getIdentifier());
        recipient.addTransaction(debit);
        sender.addTransaction(credit);
    }
    public Transaction[] GetTransactionsList(Integer id) throws UserNotFoundException {
        return usersList.getUserID(id).getTransactionsList().toArray();
    }
    public void DeleteTransactionByUser(Integer userID, UUID transactionID) throws UserNotFoundException, TransactionNotFoundException {
        usersList.getUserID(userID).getTransactionsList().deleteTransaction(transactionID.toString());
    }
    public TransactionsLinkedList GetInvalidTransactions() throws UserNotFoundException, TransactionNotFoundException {
        TransactionsLinkedList tll= new TransactionsLinkedList();
        for(int i=0;i<usersList.getSize();i++){
            for(int j=0;j<usersList.getUser(i).getTransactionsList().getSize();j++){
                CompareTransactionList(i,usersList.getUser(i).getTransactionsList().getTransaction(j),tll);
            }
        }
        return tll;
    }
    private void CompareTransactionList(int userIndex,Transaction transaction, TransactionsLinkedList ttl) throws TransactionNotFoundException, UserNotFoundException {
        for(int i=0;i<usersList.getSize();i++){
            if(i==userIndex) continue;
            try {
                usersList.getUser(i).getTransaction(transaction.getIdentifier());
                return;
            } catch (Exception e){
            }
        }
        ttl.addTransaction(transaction);
    }

}

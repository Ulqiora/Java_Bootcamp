package ex04;

import java.util.UUID;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    private final TransactionsList transactionsList;

    public User(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
        this.identifier = UserIdsGenerator.getInstance().generateId();
        transactionsList = new TransactionsLinkedList();
        try {
            if (balance < 0) throw new IllegalArgumentException("Illegal argument");
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }

    public void addTransaction(Transaction tr) {
        transactionsList.addTransaction(tr);
    }

    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        return transactionsList.getTransaction(id);
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

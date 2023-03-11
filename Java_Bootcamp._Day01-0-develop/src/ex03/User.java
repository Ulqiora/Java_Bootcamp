package ex03;

import java.util.UUID;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    private TransactionsList translist;

    public User(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
        this.identifier = UserIdsGenerator.getInstance().generateId();
        translist = new TransactionsLinkedList();
        try {
            if (balance < 0) throw new IllegalArgumentException("Illegal argument");
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }

    public void addTransaction(Transaction tr) {
        translist.addTransaction(tr);
    }

    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        return translist.getTransaction(id);
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

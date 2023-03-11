package ex00;

public class User {

    private Integer identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.name = name;
        this.balance = balance < 0 ? 0 : balance;
        this.identifier = hashCode();
        try {
            if (balance < 0) throw new IllegalArgumentException("Balance can't be less than 0!");
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
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

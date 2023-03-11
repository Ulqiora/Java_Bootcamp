package ex04;

import java.util.UUID;



public class Transaction {
    enum TransferCategory {
        Debits, Credit
    }
    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory type;
    private Integer amount;

    public Transaction(User recipient, User sender, TransferCategory type, Integer amount) {
        this.recipient = recipient;
        this.sender = sender;
        this.type = type;
        this.amount = amount;
        this.identifier = UUID.randomUUID();
        try {
            if (recipient.getBalance() < 0 || sender.getBalance() < 0)
                throw new IllegalArgumentException("Illegal argument");
            else if(type == TransferCategory.Debits&&(sender.getBalance()<amount))
                throw new IllegalArgumentException("Sender balance more than amount");
            else if(type == TransferCategory.Credit&&(recipient.getBalance()<amount))
                throw new IllegalArgumentException("Recipient balance more than amount");
            Integer resultRec = recipient.getBalance() + ((type == TransferCategory.Debits) ? (1) : (-1)) * amount;
            if (resultRec < 0)
                throw new IllegalArgumentException("Illegal argument");
            recipient.setBalance(resultRec);
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }


    public UUID getIdentifier() {
        return identifier;
    }
    public void setIdentifier(UUID id) { identifier=id; }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public TransferCategory getType() {
        return type;
    }

    public void setType(TransferCategory type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier=" + identifier +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}

package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction addedTransaction);

    void deleteTransaction(String id) throws TransactionNotFoundException;

    Transaction[] toArray();

    Transaction getTransaction(UUID id) throws TransactionNotFoundException;
}

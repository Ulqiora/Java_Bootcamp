package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Integer size = 0;
    private Node head = null;
    private Node tail = null;

    public Integer getSize() {
        return size;
    }

    @Override
    public void addTransaction(Transaction addedTransaction) {
        if (head == null) {
            Node temp = new Node(addedTransaction, null, null);
            tail = head = temp;
        } else {
            Node last = new Node(addedTransaction, null, tail);
            tail.next = last;
            tail = last;
        }
        size++;
    }

    @Override
    public void deleteTransaction(String id) throws TransactionNotFoundException {
        if (head == null)
            throw new TransactionNotFoundException("List is empty!");
        Node cursor = head;
        while ((cursor!=null) && (!cursor.data.getIdentifier().toString().equals(id)))  cursor = cursor.next;
        if (cursor == null) throw new TransactionNotFoundException("Id not found!");
        size--;
        if(head==tail) {
            head = tail = null;
        } else if(cursor==head){
            head=head.next;
            head.prev=null;
        } else if(cursor==tail){
            tail=tail.prev;
            tail.next=null;
        }else {
            cursor.next.prev=cursor.prev;
            cursor.prev.next=cursor.next;
        }
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node cursor = head;
        for (int i = 0; i < size; i++) {
            array[i] = cursor.data;
            cursor = cursor.next;
        }
        return array;
    }

    @Override
    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        TransactionsLinkedList.Node cursor = head;
        while (cursor != null) {
            if (cursor.data.getIdentifier() == id) return cursor.data;
        }
        throw new TransactionNotFoundException("Not Found");
    }

    class Node {
        Transaction data;
        Node next;
        Node prev;

        Node(Transaction data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}

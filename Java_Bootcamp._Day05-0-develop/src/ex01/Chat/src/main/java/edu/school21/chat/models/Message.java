package edu.school21.chat.models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime localTime;

    public Message(long aLong, String string, LocalDateTime toLocalDateTime) {

    }

    @Override
    public String toString() {
        return "Message{\n" +
                "id=" + id +
                ",\n author=" + author +
                ",\n room=" + room +
                ",\n text='" + text + '\'' +
                ",\n localTime=" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(localTime)+
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(localTime, message.localTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, localTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }

    public Message(long id, User author, Chatroom room, String text, LocalDateTime localTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.localTime = localTime;
    }
}

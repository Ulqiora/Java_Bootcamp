package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> rooms;
    private List<Chatroom> chatMember;

    public User(Long id, String string, String string1) {
        this.id=id;
        this.login=string;
        this.password=string1;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRooms(List<Chatroom> rooms) {
        this.rooms = rooms;
    }

    public void setChatMember(List<Chatroom> chatMember) {
        this.chatMember = chatMember;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public List<Chatroom> getChatMember() {
        return chatMember;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rooms=" + rooms +
                ", chatMember=" + chatMember +
                '}';
    }

    public User(long id, String login, String password, List<Chatroom> rooms, List<Chatroom> chatMember) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rooms = rooms;
        this.chatMember = chatMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(rooms, user.rooms) && Objects.equals(chatMember, user.chatMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, rooms, chatMember);
    }
}

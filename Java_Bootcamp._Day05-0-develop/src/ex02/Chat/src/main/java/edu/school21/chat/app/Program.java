package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        JdbcDataSource dataSource = new JdbcDataSource();
        updateData("schema.sql", dataSource);
        updateData("data.sql", dataSource);
        User creator = new User(3L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(4L, "room", creator, new ArrayList<>());
        Message message = new Message(0, creator, room, "Hello, It is a new message!", LocalDateTime.now());

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        if(messagesRepository.save(message)) System.exit(-1);
        System.out.println(message.getId());
    }

    private static void updateData(String file, JdbcDataSource dataSource) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            InputStream input = Program.class.getClassLoader().getResourceAsStream(file);
            Scanner scanner = new Scanner(input).useDelimiter(";");

            while (scanner.hasNext()) {
                st.executeUpdate(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
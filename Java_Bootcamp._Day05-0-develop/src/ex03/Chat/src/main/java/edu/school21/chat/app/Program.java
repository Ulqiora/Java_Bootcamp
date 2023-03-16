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
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        Message messageIncome=messagesRepository.findById(1L).get();
        System.out.println(messageIncome);
        messageIncome.setText("Message is edited!");
        if(!messagesRepository.update(messageIncome)) System.exit(-1);
        System.out.println(messagesRepository.findById(1L).get());
    }

    private static void updateData(String file, JdbcDataSource dataSource) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            InputStream input = Program.class.getClassLoader().getResourceAsStream(file);
            assert input != null;
            Scanner scanner = new Scanner(input).useDelimiter(";");

            while (scanner.hasNext()) {
                st.executeUpdate(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
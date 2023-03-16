package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<User> findAll(int page, int size) {
        String allQuery = "'SELECT\n" +
                "\tu1.*,\n" +
                "\tchat.chatroom.*,\n" +
                "\tchat.message.room\n" +
                "FROM chat.user AS u1\n" +
                "JOIN chat.message ON chat.message.author=u1.id\n" +
                "JOIN chat.chatroom ON chat.chatroom.id=chat.message.room\n" +
                "JOIN chat.user AS u2 ON chat.chatroom.owner=u2.id\n" +
                "ORDER BY 1'";
        List<User> users = new ArrayList<>();
        int offset = page * size;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(allQuery)) {
            System.out.println(st.getMaxRows());
            System.out.println("fdsafdsafsadfasdf");
            st.setLong(1, size);
            System.out.println("fdsafdsafsadfasdf");
            st.setLong(2, offset);
            ResultSet rs = st.executeQuery();
            System.out.println("fdsafdsafsadfasdf");
            while(rs.next()) {
                final long userId, chatId, usedChatId;
                User user;
                Chatroom chat;
                System.out.println("line");
                userId = rs.getLong(1);
                System.out.println(userId);
                user=new User(userId, rs.getString(2), rs.getString(3),
                        new ArrayList<>(), new ArrayList<>());
                if(users.stream().noneMatch(u ->userId==u.getId())){
                    users.add(user);
                } else {
                    User finalUser = user;
                    user = users.stream().filter(u -> userId== finalUser.getId()).collect(Collectors.toList()).get(0);
                }
                chatId = rs.getLong(4);

                if (chatId != 0 && user.getRooms().stream()
                        .noneMatch(c -> chatId==c.getId())) {
                    chat = new Chatroom(chatId, rs.getString(5),
                            new User(user.getId(), user.getLogin(), user.getPassword()), null);
                    user.getRooms().add(chat);
                }
                usedChatId = rs.getLong(6);

                if (usedChatId != 0 && user.getChatMember().stream()
                        .noneMatch(c -> usedChatId==c.getId())) {
                    chat = new Chatroom(usedChatId, rs.getString(7),
                            new User(rs.getLong(8), rs.getString(9),
                                    rs.getString(10)), null);
                    user.getChatMember().add(chat);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
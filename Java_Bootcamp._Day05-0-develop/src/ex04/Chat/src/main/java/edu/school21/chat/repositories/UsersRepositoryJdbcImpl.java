package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
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
        List<User> usersResult = new ArrayList<>();
        List<User> usersAll = new ArrayList<>();
        int offset = page * size;
        String allQuery = "SELECT\n" +
                "u1.*,\n" +
                "\tchat.message.*,\n" +
                "\tchat.chatroom.*,\n" +
                "\tu2.*\n" +
                "FROM (SELECT * FROM chat.user WHERE id>"+offset+" LIMIT "+size+") u1\n" +
                "JOIN chat.message ON chat.message.author=u1.id\n" +
                "JOIN chat.chatroom ON chat.chatroom.id=chat.message.room\n" +
                "JOIN chat.user AS u2 ON chat.chatroom.owner=u2.id\n" +
                "ORDER BY u1.id";
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(allQuery);
            User user = null;
            while(rs.next()) {
                final long userId, userCreatorId,roomID;
                Chatroom chat;
                userId = rs.getLong(1);
                if(usersResult.stream().noneMatch(u ->userId==u.getId())){
                    if(usersAll.stream().noneMatch(u ->userId==u.getId())){
                        user=new User(userId, rs.getString(2), rs.getString(3),
                                new ArrayList<>(), new ArrayList<>());
                        usersAll.add(user);
                        usersResult.add(user);
                    } else {
                        user=usersAll.stream().filter(u -> (userId == u.getId())).collect(Collectors.toList()).get(0);
                        usersResult.add(user);
                    }
                } else {
                    User finalUser = user;
                    user = usersResult.stream().filter(u -> (userId == u.getId())).collect(Collectors.toList()).get(0);
                }


                roomID = rs.getLong(9);
                if (roomID != 0 && user.getChatMember().stream()
                        .noneMatch(c -> (roomID==c.getId()))) {
                    chat = new Chatroom(rs.getLong(9), rs.getString(10),user, null);
                    user.getChatMember().add(chat);
                }


                userCreatorId = rs.getLong(12);
                if (userCreatorId != 0) {
                    if(usersResult.stream().anyMatch(i -> userCreatorId==i.getId())) {
                        User creator = usersResult.stream().filter(u -> (userCreatorId == u.getId())).collect(Collectors.toList()).get(0);
                        if(creator.getRooms().stream().noneMatch(i -> roomID==i.getId())){
                            chat = new Chatroom(roomID, rs.getString(10), creator, null);
                            creator.getRooms().add(chat);
                        }
                    } else if(usersAll.stream().anyMatch(i -> userCreatorId==i.getId())){
                        User creator = usersAll.stream().filter(u -> (userId == u.getId())).collect(Collectors.toList()).get(0);
                        chat = new Chatroom(roomID, rs.getString(10), creator, null);
                        creator.getRooms().add(chat);
                    } else {
                        User creator = new User(rs.getLong(12),rs.getString(13),rs.getString(14));
                        usersAll.add(creator);
                        chat = new Chatroom(roomID, rs.getString(10), creator, null);
                        creator.getRooms().add(chat);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usersResult;
    }
}
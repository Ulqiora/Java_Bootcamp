package edu.school21.chat.repositories;

import edu.school21.chat.models.*;
import edu.school21.chat.exceptions.NotSavesSubEntityException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String mQuery = "SELECT * FROM chat.message WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(mQuery);

            if (!rs.next()) {
                return Optional.empty();
            }
            Long userId = rs.getLong(2);
            Long roomId = rs.getLong(3);
            User user = findUser(userId);
            Chatroom room = findChat(roomId);
            return Optional.of(new Message(rs.getLong(1), user, room,
                    rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Message message) {
        String mQuery=  "INSERT INTO chat.\"message\" (author, room, text, ldatetime) VALUES (" +
                        '\''+ message.getAuthor().getId()+ "'," +
                        '\''+ message.getRoom().getId()+"',"+
                        '\''+ message.getText()+"',"+
                        '\''+ Timestamp.valueOf(message.getLocalTime())+"'"+
                        ") RETURNING id";
//        System.out.println(mQuery);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            if(findUser(message.getAuthor().getId())==null)
                throw new NotSavesSubEntityException("The user not found in the database!");
            if(findChat(message.getRoom().getId())==null)
                throw new NotSavesSubEntityException("The Chatroom not found in the database!");
            ResultSet rs = st.executeQuery(mQuery);
            if (!rs.next())
                throw new NotSavesSubEntityException("The request has not been fulfilled");
            message.setId(rs.getLong(1));
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Message message) {
        String mQuery=  "UPDATE chat.\"message\" SET "+
                        "author = '"+message.getAuthor().getId()+"',"+
                "room = '"+message.getRoom().getId()+"',"+
                "text = '"+message.getText()+"',"+
                "lDateTime = '"+Timestamp.valueOf(message.getLocalTime())+
                "' RETURNING id";
//        System.out.println(mQuery);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            if(!findById(message.getId()).isPresent())
                throw new NotSavesSubEntityException("The id of message not found in the database!");
            if(findUser(message.getAuthor().getId())==null)
                throw new NotSavesSubEntityException("The User not found in the database!");
            if(findChat(message.getRoom().getId())==null)
                throw new NotSavesSubEntityException("The Chatroom not found in the database!");
            ResultSet rs = st.executeQuery(mQuery);
            if (!rs.next())
                throw new NotSavesSubEntityException("The request has not been fulfilled");
            message.setId(rs.getLong(1));
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    private User findUser(Long id) throws SQLException {
        String uQuery = "SELECT * FROM chat.user WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(uQuery);
            if (!rs.next()) {
                return null;
            }
            return new User(id, rs.getString(2), rs.getString(3));
        }
    }

    private Chatroom findChat(Long id) throws SQLException {
        String cQuery = "SELECT * FROM chat.chatroom WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(cQuery);
            if (!rs.next()) {
                return null;
            }
            return new Chatroom(id, rs.getString(2));
        }
    }
}
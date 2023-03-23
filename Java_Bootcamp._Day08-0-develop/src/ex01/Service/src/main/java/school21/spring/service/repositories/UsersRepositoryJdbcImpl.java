package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        String mQuery = "SELECT * FROM models.user WHERE id = " + id;
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(mQuery);
            if (!rs.next()) return Optional.empty();
            return Optional.of(new User(rs.getLong(1),rs.getString(2)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement= connection.createStatement()) {
            String alQuery = "SELECT * FROM models.user";
            ResultSet rs = statement.executeQuery(alQuery);

            while (rs.next()) {
                users.add(new User(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate("INSERT INTO models.user (id, email) VALUES ("
                    + entity.getId() + ", '"
                    + entity.getEmail() + "');");

            if (result == 0) {
                System.err.println("User wasn't saved with id: " + entity.getId());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        String upQuery = "UPDATE models.user SET email = '"+entity.getEmail()+"' WHERE id = "+entity.getId();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(upQuery);
            if (result == 0) System.err.println("User wasn't updated with id: " + entity.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String dlQuery = "DELETE FROM models.user WHERE id = ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(dlQuery + id)) {
            int result = statement.executeUpdate();
            if (result == 0)  System.err.println("User not found with id: " + id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String mQuery = "SELECT * FROM models.user WHERE email = " + email;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(mQuery);
            if (!rs.next()) return Optional.empty();
            return Optional.of(new User(rs.getLong(1),rs.getString(2)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}

package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        createTable(context);
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemp = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemp.findAll());
        for (int id = 1; id <= 5; id++) {
            usersRepositoryJdbc.save(new User((long) id, "user" + id + "@Jdbc.ru"));
            usersRepositoryJdbcTemp.save(new User((long) id, "user" + id + "@Jdbc.ru"));
        }
        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemp.findAll());
    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user "
                    + "(id integer not null, email varchar(50) not null);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

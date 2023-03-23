package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("school21.spring.service");
        UsersService usersService = context.getBean("UsersServiceImpl", UsersService.class);
        UsersRepository usersJdbcTemplate = context.getBean("UsersRepositoryJdbcTemplateImpl", UsersRepository.class);
        UsersRepository usersJdbc = context.getBean("UsersRepositoryJdbcImpl", UsersRepository.class);
        createTable(context);

        for (int i = 0; i < 10; i++) {
            System.out.println("User" + (i + 1) + " is added.");
            System.out.println("Password: " + usersService.signUp("user" + (i + 1) + "@school21.ru"));
        }

        System.out.println("\n------------------------------------");
        System.out.println("Find all: \n" + usersJdbcTemplate.findAll());
        System.out.println(usersJdbc.findAll());
        System.out.println("\n------------------------------------");

        System.out.println("Find by Id: \n" + usersJdbcTemplate.findById(1L));
        System.out.println(usersJdbc.findById(1L));
        System.out.println("\n------------------------------------");

        System.out.println("Find by non-exists email: \n" + usersJdbcTemplate.findByEmail("01234@school21.ru"));
        System.out.println(usersJdbc.findByEmail("01234@school21.ru"));
        System.out.println("\n------------------------------------");

        System.out.println("Find by real email: \n" + usersJdbcTemplate.findByEmail("user1@school21.ru"));
        System.out.println(usersJdbc.findByEmail("user2@school21.ru"));
        System.out.println("\n------------------------------------");

    }
    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user "
                    + "(id integer, email varchar(50) not null, password varchar(50));");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

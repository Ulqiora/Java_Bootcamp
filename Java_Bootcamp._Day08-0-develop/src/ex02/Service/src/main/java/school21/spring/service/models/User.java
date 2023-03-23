package school21.spring.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;

    public User(Long id, String email, String password) {
        this.id=id;
        this.email = email;
        this.password = password;
    }
}
package ex05;

public class UserIdsGenerator {
    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();
    private static Integer lastId = 0;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }

    public Integer generateId() {
        lastId++;
        return lastId;
    }
}

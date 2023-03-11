package ex02;

public interface UsersList {
    void addUser(User addedUser);

    User getUserID(Integer id) throws UserNotFoundException;

    User getUser(Integer index) throws UserNotFoundException;

    int getSize();
}

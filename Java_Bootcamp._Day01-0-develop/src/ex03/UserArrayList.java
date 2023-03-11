package ex03;

public class UserArrayList implements UsersList {
    Integer capacity = 10;
    Integer size = 0;
    User[] array = new User[capacity];

    public UserArrayList() {
    }

    void reallocArray() {
        capacity *= 2;
        User[] newArray = new User[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void addUser(User addedUser) {
        if (capacity == size) {
            reallocArray();
        }
        array[size] = addedUser;
        size++;
    }

    @Override
    public User getUserID(Integer id) throws UserNotFoundException {
        for (User user_i : array) {
            if (user_i.getIdentifier() == id) {
                return user_i;
            }
        }
        throw new UserNotFoundException("id " + id.toString() + "can't be found");
    }

    @Override
    public User getUser(Integer index) throws UserNotFoundException {
        if (index >= size || index < 0) throw new UserNotFoundException("Out of range!");
        return array[index];
    }

    @Override
    public int getSize() {
        return size;
    }
}

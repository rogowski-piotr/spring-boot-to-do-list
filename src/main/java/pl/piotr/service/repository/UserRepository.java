package pl.piotr.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.piotr.service.data.DataStorage;
import pl.piotr.service.entity.User;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User, String> {

    private DataStorage dataStorage;

    @Autowired
    UserRepository(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Optional<User> find(String email) {
        return dataStorage.findUser(email);
    }

    @Override
    public List<User> findAll() {
        return dataStorage.getAllUsers();
    }

    @Override
    public void create(User entity) {
        dataStorage.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Operation not supported");
    }

}

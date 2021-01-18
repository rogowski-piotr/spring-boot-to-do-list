package pl.piotr.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> find(String email) {
        return repository.findById(email);
    }

    @Transactional
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional
    public void update(User user) {
        repository.save(user);
    }

    @Transactional
    public void delete(String email) {
        repository.deleteById(email);
    }

    @Transactional
    public void updatePortrait(String email, InputStream is) {
        repository.findById(email).ifPresent(
                user -> {
                    try {
                        user.setPortrait(is.readAllBytes());
                    } catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                });
    }

}

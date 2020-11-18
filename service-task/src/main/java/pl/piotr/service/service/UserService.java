package pl.piotr.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> find(String email) {
        return repository.findById(email);
    }

    @Transactional
    public User create(User user) {
        return repository.save(user);
    }

}

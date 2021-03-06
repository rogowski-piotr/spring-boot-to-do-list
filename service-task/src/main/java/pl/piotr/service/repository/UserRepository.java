package pl.piotr.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotr.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

package pl.piotr.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.piotr.service.dto.CreateUserRequest;
import pl.piotr.service.entity.User;

@Repository
public class UserEventRepository  {

    private RestTemplate restTemplate;

    @Autowired
    public UserEventRepository(@Value("${todo.tasks.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(User user) {
        restTemplate.delete("/users/{username}", user.getEmail());
    }

    public void create(User user) {
        restTemplate.postForLocation("/users", CreateUserRequest.entityToDtoMapper().apply(user));
    }
}

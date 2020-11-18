package pl.piotr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToDoListApiGateway {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApiGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/users/{login}", "/api/users")
                        .uri("http://localhost:8081"))
                .route("tasks", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/users/{login}/tasks/{id}", "/api/users/{login}/tasks", "/api/users/{login}/tasks/**")
                        .uri("http://localhost:8082"))
                .build();
    }

}

package pl.piotr.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pl.piotr.service.dto.user.CreateUserRequest;
import pl.piotr.service.dto.user.GetUserResponse;
import pl.piotr.service.dto.user.GetUsersResponse;
import pl.piotr.service.dto.user.UpdateUserRequest;
import pl.piotr.service.entity.User;
import pl.piotr.service.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<GetUsersResponse> getUsers() {
        List<User> listAll = service.findAll();
        if (!listAll.isEmpty()) {
            return ResponseEntity.ok(GetUsersResponse.entityToDtoMapper().apply(service.findAll()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("username") String username) {
        Optional<User> user = service.find(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(GetUserResponse.entityToDtoMapper().apply(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request,
                                           UriComponentsBuilder builder) {
        Optional<User> newUser = service.find(request.getEmail());
        if (!newUser.isPresent()) {
            User user = CreateUserRequest
                    .dtoToEntityMapper()
                    .apply(request);
            user = service.create(user);
            return ResponseEntity.created(builder.pathSegment("api", "users")
                    .buildAndExpand(user.getEmail()).toUri()).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{username}")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request,
                                           @PathVariable("username") String username) {
        Optional<User> userOptional = service.find(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(username);
            UpdateUserRequest.dtoToEntityUpdater().apply(user, request);
            service.update(user);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        Optional<User> user = service.find(username);
        if (user.isPresent()) {
            service.delete(user.get().getEmail());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "{username}/portrait", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserPortrait(@PathVariable("username") String email) {
        Optional<User> user = service.find(email);
        return user.map(value -> ResponseEntity.ok(value.getPortrait()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "{username}/portrait", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserPortrait(@PathVariable("username") String email,
                                                   @RequestParam("portrait") MultipartFile portrait) throws IOException {
        Optional<User> user = service.find(email);
        if (user.isPresent()) {
            service.updatePortrait(user.get().getEmail(), portrait.getInputStream());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
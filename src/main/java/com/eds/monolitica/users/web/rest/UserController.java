package com.eds.monolitica.users.web.rest;

import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
        if (detailed) {
            return ResponseEntity.ok().body(userService.listUsersDetailed());
        } else {
            return ResponseEntity.ok().body(userService.listUsers());
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable final Long userId) {
        return ResponseEntity
                .ok()
                .body(userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Resource not found exception for the id: " + userId)));
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody final UserDTO dto) throws URISyntaxException {
        UserDTO userDB = userService.save(dto);
        return ResponseEntity.created(new URI("/v1/users/" + userDB.getId())).body(userDB);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO,
                                          @PathVariable final Long userId) throws URISyntaxException {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid course id, null value");
        }
        if (!Objects.equals(userDTO.getId(), userId)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.userService.save(userDTO));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable final Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}

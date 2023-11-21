package com.eds.monolitica.users.web.rest;

import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserDetailDTO;
import com.eds.monolitica.users.services.IUserDetailService;
import com.eds.monolitica.users.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/user-details")
public class UserDetailController {
    private final IUserDetailService userDetailService;

    public UserDetailController(IUserDetailService userService) {
        this.userDetailService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDetailDTO>> listUsers(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
            return ResponseEntity.ok().body(userDetailService.listDetailUsers());

    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> getUserById(@PathVariable final Long userId) {
        return ResponseEntity
                .ok()
                .body(userDetailService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Resource not found exception for the id: " + userId)));
    }
    @PostMapping
    public ResponseEntity<UserDetailDTO> create(@RequestBody final UserDetailDTO dto) throws URISyntaxException {
        UserDetailDTO userDB = userDetailService.save(dto);
        return ResponseEntity.created(new URI("/v1/users/" + userDB.getId())).body(userDB);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> update(@RequestBody UserDetailDTO userDTO,
                                          @PathVariable final Long userId) throws URISyntaxException {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid course id, null value");
        }
        if (!Objects.equals(userDTO.getId(), userId)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.userDetailService.update(userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable final Long userId) {
        userDetailService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}

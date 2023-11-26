package com.eds.monolitica.users.web.rest;

import com.eds.monolitica.users.dto.UserDetailDTO;
import com.eds.monolitica.users.dto.UserRolDTO;
import com.eds.monolitica.users.services.IUserRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/user-rol")
public class UserRolController {
    private final IUserRolService userRolService;

    public UserRolController(IUserRolService userRolService) {
        this.userRolService = userRolService;
    }
    @GetMapping
    public ResponseEntity<List<UserRolDTO>> listUserRol(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
        return ResponseEntity.ok().body(userRolService.listDetailUserRol());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRolDTO> getUserById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(userRolService.getUserRolById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found exception for the id: " + id)));
    }
    @PostMapping
    public ResponseEntity<UserRolDTO> create(@RequestBody final UserRolDTO dto) throws URISyntaxException {
        UserRolDTO userRolDB = userRolService.save(dto);
        return ResponseEntity.created(new URI("/v1/user-rol/" + userRolDB.getId())).body(userRolDB);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserRolDTO> update(@RequestBody UserRolDTO userDTO,
                                                @PathVariable final Integer id) throws URISyntaxException {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid course id, null value");
        }
        if (!Objects.equals(userDTO.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.userRolService.update(userDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        userRolService.delete(id);
        return ResponseEntity.ok().build();
    }
}

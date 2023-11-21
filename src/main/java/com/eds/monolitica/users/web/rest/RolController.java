package com.eds.monolitica.users.web.rest;

import com.eds.monolitica.users.dto.RolDTO;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserForRolDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eds.monolitica.users.services.IRolService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/rols")
public class RolController {
    private final IRolService rolService;

    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }
    @GetMapping
    public ResponseEntity<List<RolDTO>> listRols(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
        if (detailed) {
            return ResponseEntity.ok().body(rolService.listRolDetailed());
        } else {
            return ResponseEntity.ok().body(rolService.listUsers());
        }
    }
    @GetMapping("/{rolId}")
    public ResponseEntity<RolDTO> getRolById(@PathVariable final Integer rolId) {
        return ResponseEntity
                .ok()
                .body(rolService.getRolById(rolId).orElseThrow(() -> new IllegalArgumentException("Resource not found exception for the id: " + rolId)));
    }
    @GetMapping("/{rolId}/users")
    public ResponseEntity<List<UserForRolDTO>> listUsersByRolId(@PathVariable final Integer rolId) {
        return ResponseEntity.ok().body(rolService.listUsersByRolId(rolId));
    }
    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody final RolDTO dto) throws URISyntaxException {
            RolDTO rolDB = rolService.save(dto);
        return ResponseEntity.created(new URI("/v1/rols/" + rolDB.getId())).body(rolDB);
    }

    @PutMapping("/{rolId}")
    public ResponseEntity<RolDTO> update(@RequestBody RolDTO rolDTO,
                                          @PathVariable final Integer rolId) throws URISyntaxException {
        if (rolDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid course id, null value");
        }
        if (!Objects.equals(rolDTO.getId(), rolId)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.rolService.save(rolDTO));

    }

    @DeleteMapping("/{rolId}")
    public ResponseEntity<Void> delete(@PathVariable final Integer rolId) {
        rolService.delete(rolId);
        return ResponseEntity.noContent().build();
    }
}

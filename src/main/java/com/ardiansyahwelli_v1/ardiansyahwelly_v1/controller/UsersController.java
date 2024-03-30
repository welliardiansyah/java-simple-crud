package com.ardiansyahwelli_v1.ardiansyahwelly_v1.controller;

import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    UsersService usersService;
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUsers(@RequestBody UsersModel usersModel) {
        return usersService.createUsers(usersModel);
    }

    @PutMapping()
    public ResponseEntity<Object> updateData(@RequestBody UsersModel usersModel) {
        return usersService.updateUsers(usersModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUsers(@PathVariable("id") UUID id) {
        return usersService.deleteUsers(id);
    }

    @GetMapping("{id}")
    public Object getDetailsUsers(@PathVariable("id") UUID id) {
        return usersService.getUsers(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getLisstingUsers() {
        return usersService.getLissting();
    }
}

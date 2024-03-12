package com.ardiansyahwelli_v1.ardiansyahwelly_v1.controller;

import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/")
    public String createUsers(@RequestBody UsersModel usersModel) {
        String saveData = usersService.createUsers(usersModel);
        return saveData;
    }

    @PutMapping()
    public String updateData(@RequestBody UsersModel usersModel) {
        usersService.updateUsers(usersModel);
        return "Update users successfully!...";
    }

    @DeleteMapping("{id}")
    public String deleteUsers(@PathVariable("id") UUID id) {
        try {
            return usersService.deleteUsers(id);
        } catch (IllegalArgumentException e) {
            return "Invalid UUID format";
        }
    }

    @GetMapping("{id}")
    public Object getDetailsUsers(@PathVariable("id") UUID id) {
        return usersService.getUsers(id);
    }

    @GetMapping()
    public List<UsersModel> getLisstingUsers() {
        return usersService.getLissting();
    }
}

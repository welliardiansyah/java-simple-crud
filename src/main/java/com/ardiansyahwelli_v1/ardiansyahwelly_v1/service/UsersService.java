package com.ardiansyahwelli_v1.ardiansyahwelly_v1.service;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UsersService {
    public ResponseEntity<Object> createUsers(UsersModel usersModel);
    public ResponseEntity<Object> updateUsers(UsersModel usersModel);
    public ResponseEntity<Object> deleteUsers(UUID id);
    Object getUsers(UUID id);
    public ResponseEntity<Object> getLissting();
}

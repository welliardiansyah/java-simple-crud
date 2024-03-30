package com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.impl;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.exception.NotFoundException;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.repository.UsersRepository;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.response.ResponseHandler;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public ResponseEntity<Object> createUsers(UsersModel usersModel) {
        try {
            UsersModel createData = usersRepository.save(usersModel);
            if (createData != null) {
                // Create Object Data
                Map<String, Object> users = new HashMap<>();
                users.put("id", createData.getId());
                users.put("name", createData.getName());
                users.put("email", createData.getEmail());
                users.put("address", createData.getAddress());
                users.put("created_at", createData.getCreatedAt());

                return ResponseHandler.successResponseBuilder(
                        "Created users successfully!",
                        HttpStatus.OK,
                        users
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Created users cannot be successfully!.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Not found data!." + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> updateUsers(UsersModel usersModel) {
       try {
           UsersModel updateData = usersRepository.save(usersModel);
           if (updateData != null) {
               // Create Object Data
               Map<String, Object> users = new HashMap<>();
               users.put("id", updateData.getId());
               users.put("name", updateData.getName());
               users.put("email", updateData.getEmail());
               users.put("address", updateData.getAddress());
               users.put("created_at", updateData.getCreatedAt());
               users.put("updated_at", updateData.getUpdatedAt());

               return ResponseHandler.successResponseBuilder(
                       "Updated users successfully!.",
                       HttpStatus.OK,
                       users
               );
           } else {
               return ResponseHandler.errorResponseBuilder(
                       "Updated users cannot be successfully!.",
                       HttpStatus.OK
               );
           }
       } catch (DataAccessException e) {
           throw new NotFoundException("Not found data!." + e.getMessage());
       }
    }

    @Override
    public ResponseEntity<Object> deleteUsers(UUID id) {
        try {
            Optional<UsersModel> existingUser = usersRepository.findById(id);
            if (existingUser.isPresent()) {
                UsersModel existing = existingUser.get();
                existing.setIs_deleted(true);
                UsersModel deleteData = usersRepository.save(existing);
                return ResponseHandler.successResponseBuilder(
                        "Deleted users account successfully!.",
                        HttpStatus.OK,
                        deleteData.toString()
                );
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Deleted users cannot be successfully!.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Not found data!." + e.getMessage());
        }
    }

    @Override
    public Object getUsers(UUID id) {
        try {
            Optional<UsersModel> userOptional = usersRepository.findById(id);
            if (userOptional.isPresent()) {
                UsersModel user = userOptional.get();

                if (user.isIs_deleted(true)) {
                    return ResponseHandler.errorResponseBuilder(
                            "Users has been deleted",
                            HttpStatus.BAD_REQUEST
                    );
                } else {
                    return ResponseHandler.successResponseBuilder(
                            "Get details users successfully!.",
                            HttpStatus.OK,
                            user
                    );
                }
            } else {
                return ResponseHandler.errorResponseBuilder(
                        "Users canoot be found!.",
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (DataAccessException e) {
            throw new NotFoundException("Not found data!." + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getLissting() {
       try {
           List<UsersModel> userOptional = usersRepository.findAll();
           LOGGER.warn("Retrieved {} users from the database", userOptional);
           return ResponseHandler.successResponseBuilder(
                   "Get listting users successfully!.",
                   HttpStatus.OK,
                   userOptional
                   );
       } catch (DataAccessException e) {
       }
        return null;
    }
}

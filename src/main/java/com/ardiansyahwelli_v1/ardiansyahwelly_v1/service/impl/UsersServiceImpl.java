package com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.impl;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.repository.UsersRepository;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.service.UsersService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String createUsers(UsersModel usersModel) {
        try {
            UsersModel createData = usersRepository.save(usersModel);
            if (createData != null) {
                return "Create new users successfully!..." + createData;
            } else {
                return "Create new users failed!.";
            }
        } catch (DataAccessException e) {
            return "Create new users failed!.: " + e.getMessage();
        }
    }

    @Override
    public String updateUsers(UsersModel usersModel) {
       try {
           UsersModel updateData = usersRepository.save(usersModel);
           if (updateData != null) {
               return "Update successfully!..." + updateData;
           } else {
               return "Update failed!." + updateData;
           }
       } catch (DataAccessException e) {
           return "Update failed!.: " + e.getMessage();
       }
    }

    @Override
    public String deleteUsers(UUID id) {
        try {
            Optional<UsersModel> existingUser = usersRepository.findById(id);
            if (existingUser.isPresent()) {
                UsersModel existing = existingUser.get();
                existing.setIs_deleted(true);
                usersRepository.save(existing);
                return "Soft delete successful";
            } else {
                return "User with ID " + id + " not found";
            }
        } catch (DataAccessException e) {
            return "Delete users failed: " + e.getMessage();
        }
    }

    @Override
    public Object getUsers(UUID id) {
        try {
            return usersRepository.findById(id);
        } catch (DataAccessException e) {
            return "Delete users failed: " + e.getMessage();
        }
    }

    @Override
    public List<UsersModel> getLissting() {
        return usersRepository.findAll();
    }
}

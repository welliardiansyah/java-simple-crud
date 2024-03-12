package com.ardiansyahwelli_v1.ardiansyahwelly_v1.service;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import java.util.List;
import java.util.UUID;

public interface UsersService {
    public String createUsers(UsersModel usersModel);
    public String updateUsers(UsersModel usersModel);
    public String deleteUsers(UUID id);
    Object getUsers(UUID id);
    public List<UsersModel> getLissting();
}

package com.ardiansyahwelli_v1.ardiansyahwelly_v1.repository;
import com.ardiansyahwelli_v1.ardiansyahwelly_v1.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersModel, UUID> {
}

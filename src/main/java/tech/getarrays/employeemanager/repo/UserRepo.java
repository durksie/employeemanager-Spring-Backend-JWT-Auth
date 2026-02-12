package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    //additional methods
    Optional<UserEntity>findByusername(String username);
    Boolean existsByusername(String username);
}

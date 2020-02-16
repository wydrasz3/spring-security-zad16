package pl.sda.zad16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.zad16.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

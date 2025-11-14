package ro.andreialionte.magazinonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andreialionte.magazinonline.model.Auth;
import ro.andreialionte.magazinonline.model.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByEmail(String email);

}

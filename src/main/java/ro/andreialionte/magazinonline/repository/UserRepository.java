package ro.andreialionte.magazinonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andreialionte.magazinonline.model.Auth;
import ro.andreialionte.magazinonline.model.User;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAuth(Auth auth);
}

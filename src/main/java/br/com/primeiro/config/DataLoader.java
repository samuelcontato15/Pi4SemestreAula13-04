package br.com.primeiro.config;

import br.com.primeiro.model.User;
import br.com.primeiro.model.UserRole;
import br.com.primeiro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByLogin("João").isEmpty()) {
            User admin = User.builder().login("João").password(passwordEncoder.encode("admin123")).role(UserRole.ADMIN).build();
            userRepository.save(admin);
        }

        if (userRepository.findByLogin("Felipe").isEmpty()) {
            User user = User.builder().login("Felipe").password(passwordEncoder.encode("user123")).role(UserRole.ADMIN).build();
            userRepository.save(user);
        }
    }


}

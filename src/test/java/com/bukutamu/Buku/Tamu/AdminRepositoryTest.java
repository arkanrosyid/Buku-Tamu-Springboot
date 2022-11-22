package com.bukutamu.Buku.Tamu;

// import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.annotation.Rollback;

import com.bukutamu.models.Admin;
import com.bukutamu.repositories.AdminRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository repo;

    // @Autowired
    // private TestEntityManager entityManager;

    private static int workload = 12;

    @Test
    public void testCreateAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin@mail.com");
        admin.setPassword(hashPassword("root1234"));
        admin.setName("admin");

        repo.save(admin);
        // Admin existAdmin = entityManager.find(Admin.class, saveAdmin.getId());

        // assertThat(existAdmin.getEmail()).isEqualTo(admin.getEmail());

    }

    @Test
    public void testFindUserByEmail() {
        String email = "admin2@mail.com";
        Admin admin = repo.findByEmail(email);

        // assertThat(admin).isNotNull();

        if (admin == null) {
            System.out.println(admin);
        }
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    // public static boolean checkPassword(String password_plaintext, String
    // stored_hash) {
    // boolean password_verified = false;

    // if (null == stored_hash || !stored_hash.startsWith("$2a$"))
    // throw new java.lang.IllegalArgumentException("Invalid hash provided for
    // comparison");

    // password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

    // return (password_verified);
    // }

}

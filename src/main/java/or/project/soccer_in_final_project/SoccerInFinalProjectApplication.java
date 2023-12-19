package or.project.soccer_in_final_project;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.config.WebJWTConfig;
import or.project.soccer_in_final_project.entities.Role;
import or.project.soccer_in_final_project.error.WebException;
import or.project.soccer_in_final_project.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(WebJWTConfig.class)
public class SoccerInFinalProjectApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {

        //catch all my exceptions
        try {
            SpringApplication.run(SoccerInFinalProjectApplication.class, args);
        } catch (WebException b) {
            //send to log server
        }
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //check the size
        if (roleRepository.findAll().size() == 0) {
            //add roles to role repo:
            //insert
            roleRepository.save(new Role(1L, "ROLE_USER"));
        }
    }

}

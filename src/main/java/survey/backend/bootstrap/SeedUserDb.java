package survey.backend.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import survey.backend.model.User;
import survey.backend.repository.UserRepository;

@Component
public class SeedUserDb implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(SeedSurveyDb.class);

    private final UserRepository userRepository;

    public SeedUserDb(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

       if(userRepository.findAll().size() == 0){

            userRepository.save(
                    User.builder()
                            .username("Spock")
                            .password("$2a$10$jXpENQlTEQ5ecn90ugTyxOAotZOox7o3Kyign2NT//RZZCokPN5h2")
                            .email("sos@starfleet.org")
                            .enabled(true)
                            .build()
            );
        }

        LOGGER.info("UserDb size is: " + userRepository.findAll().size());
    }
}

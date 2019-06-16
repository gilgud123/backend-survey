package survey.backend.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import survey.backend.model.Film;
import survey.backend.model.Series;
import survey.backend.model.Ship;
import survey.backend.model.Survey;
import survey.backend.repository.SurveyRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class SeedSurveyDb implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(SeedSurveyDb.class);

    private SurveyRepository surveyRepository;

    public SeedSurveyDb(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(surveyRepository.findAll().size() == 0){

            surveyRepository.save(
                    Survey.builder()
                            .series(Series.NEXT_GENERATION)
                            .films(Arrays.asList(Film.FIRST_CONTACT, Film.GENERATIONS))
                            .character("Data")
                            .actor("Brad Pitt")
                            .ship(Ship.BORD_CUBE)
                            .idea("Have all Star Trek leading captains stranded on an uninhabited planet and fight for survival against each other.")
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            surveyRepository.save(
                    Survey.builder()
                            .series(Series.ORIGINAL)
                            .films(Arrays.asList(Film.THE_SEARCH_FOR_SPOCK, Film.STAR_TREK_2009, Film.GENERATIONS))
                            .character("Spock")
                            .actor("Angelina Jolie")
                            .ship(Ship.KLINGON_BIRD_OF_PREY)
                            .idea("Have a new original series reload with female versions of Kirk, Spock and McCoy.")
                            .createdAt(LocalDateTime.now())
                            .build()
            );
        }
        LOGGER.info("SurveyDb size is: " + surveyRepository.findAll().size());

    }
}

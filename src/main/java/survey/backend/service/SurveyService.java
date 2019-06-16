package survey.backend.service;

import org.springframework.stereotype.Service;
import survey.backend.command.SurveyCommand;
import survey.backend.model.Film;
import survey.backend.model.Series;
import survey.backend.model.Ship;
import survey.backend.model.Survey;
import survey.backend.repository.SurveyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<SurveyCommand> getSurveysByMonth(int month){
        return surveyRepository.findAll().stream()
                .filter(s -> month == s.getCreatedAt().getMonth().getValue())
                .map(this::convertSurveyToCommand)
                .collect(Collectors.toList());
    }

    public Survey saveSurvey(SurveyCommand command){

        List<Film> films = command.getFilms().stream().map(Film::valueOf).collect(Collectors.toList());

        return surveyRepository.save(Survey.builder()
                    .series(Series.valueOf(command.getSeries()))
                    .films(films)
                    .character(command.getCharacter())
                    .ship(Ship.valueOf(command.getShip()))
                    .actor(command.getActor())
                    .idea(command.getIdea())
                    .createdAt(LocalDateTime.now())
                    .build());

    }

    private SurveyCommand convertSurveyToCommand(Survey survey){

        return SurveyCommand.builder()
                .series(survey.getSeries().name())
                .films(survey.getFilms().stream().map(Enum::name).collect(Collectors.toList()))
                .character(survey.getCharacter())
                .actor(survey.getActor())
                .idea(survey.getIdea())
                .build();
    }
}

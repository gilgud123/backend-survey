package survey.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.command.SurveyCommand;
import survey.backend.model.Survey;
import survey.backend.service.SurveyService;

import java.util.List;

@RestController
public class SurveyController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    private SurveyService service;

    public SurveyController(SurveyService service) {
        this.service = service;
    }

    public ResponseEntity<List<SurveyCommand>> getSurveysByMonth(int month) {
        try {
            return new ResponseEntity<>(service.getSurveysByMonth(month), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Survey> saveSurvey(SurveyCommand command){
        try{
            return new ResponseEntity<>(service.saveSurvey(command), HttpStatus.CREATED);
        }catch (Exception ex){
            LOGGER.info(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

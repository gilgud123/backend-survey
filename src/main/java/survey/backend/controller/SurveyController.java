package survey.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import survey.backend.command.SurveyCommand;
import survey.backend.model.Survey;
import survey.backend.service.SurveyService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class SurveyController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    private SurveyService service;

    public SurveyController(SurveyService service) {
        this.service = service;
    }

    @GetMapping(path = "/surveys/{month}")
    public ResponseEntity<List<SurveyCommand>> getSurveysByMonth(int month) {
        try {
            return new ResponseEntity<>(service.getSurveysByMonth(month), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/survey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> saveSurvey(@RequestBody @Validated SurveyCommand command){
        Survey survey = service.saveSurvey(command);
        Resource<Survey> resource = new Resource<>(survey);
        resource.add(linkTo(methodOn(SurveyController.class).saveSurvey(command)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

}

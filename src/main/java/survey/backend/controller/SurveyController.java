package survey.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import survey.backend.command.SurveyCommand;
import survey.backend.model.Survey;
import survey.backend.service.SurveyService;

import java.time.Month;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class SurveyController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    private final SurveyService service;

    public SurveyController(SurveyService service) {
        this.service = service;
    }

    @GetMapping(path = "/surveys/{month}")
    public ResponseEntity<Resources<SurveyCommand>> getSurveysByMonth(@PathVariable(name = "month") int month) {
        List<SurveyCommand> commands = service.getSurveysByMonth(month);
        Resources<SurveyCommand> resource = new Resources<>(commands);
        resource.add(linkTo(methodOn(SurveyController.class).getSurveysByMonth(month)).withSelfRel());
        LOGGER.info("Surveys for the month of {}: {}.", commands.size(), Month.of(month).name());
        return ResponseEntity.ok(resource);
    }

    @PostMapping(path = "/survey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> saveSurvey(@RequestBody @Validated SurveyCommand command){
        Survey survey = service.saveSurvey(command);
        Resource<Survey> resource = new Resource<>(survey);
        resource.add(linkTo(methodOn(SurveyController.class).saveSurvey(command)).withSelfRel());
        LOGGER.info("Survey posted to the database.");
        return ResponseEntity.ok(resource);
    }

}

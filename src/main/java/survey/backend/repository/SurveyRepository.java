package survey.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import survey.backend.model.Survey;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "surveys", path = "surveys")
public interface SurveyRepository extends MongoRepository<Survey, String > {

    List<Survey> findAllByCreatedAt(LocalDateTime createdAt);
}

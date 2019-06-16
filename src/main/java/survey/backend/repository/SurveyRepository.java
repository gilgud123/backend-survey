package survey.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import survey.backend.model.Survey;

@RepositoryRestResource(collectionResourceRel = "surveys", path = "surveys")
public interface SurveyRepository extends MongoRepository<Survey, String > {

}

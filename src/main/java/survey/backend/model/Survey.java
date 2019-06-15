package survey.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Data
@Builder
@Document(collection = "surveys")
public class Survey {

    @Id
    private String id;

    private Series series;

    private List<Film> films;

    private String character;

    private Ship ship;

    private String actor;

    private String idea;

    private LocalDateTime createdAt;
}

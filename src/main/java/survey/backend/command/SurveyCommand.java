package survey.backend.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize(as = SurveyCommand.class)
@JsonDeserialize(as = SurveyCommand.class)
public class SurveyCommand {

    private String series;
    private List<String> films;
    private String character;
    private String ship;
    private String actor;
    private String idea;

}

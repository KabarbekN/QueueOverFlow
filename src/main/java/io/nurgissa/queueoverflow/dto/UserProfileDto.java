package io.nurgissa.queueoverflow.dto;


import io.nurgissa.queueoverflow.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
    private UserDto userDto;
    private Integer numberOfQuestions;
    private Integer numberOfAnswers;
    private Integer numberOfComments;
}

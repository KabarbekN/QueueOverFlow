package io.nurgissa.queueoverflow.dto.vote;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {
    private Integer value;
    private String createdTime;
}

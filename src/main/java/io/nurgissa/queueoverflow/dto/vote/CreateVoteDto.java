package io.nurgissa.queueoverflow.dto.vote;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateVoteDto {
    private Integer value;
    private Long attributeId;
}

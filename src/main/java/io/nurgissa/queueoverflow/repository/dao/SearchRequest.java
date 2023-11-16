package io.nurgissa.queueoverflow.repository.dao;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchRequest {
    private String username;
    private String email;
}

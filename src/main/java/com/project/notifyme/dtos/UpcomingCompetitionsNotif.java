package com.project.notifyme.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UpcomingCompetitionsNotif {

    @JsonProperty
    private List<Long> competitionsIds;
}

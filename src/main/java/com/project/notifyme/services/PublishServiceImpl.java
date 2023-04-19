package com.project.notifyme.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.notifyme.dtos.UpcomingCompetitionsNotif;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService{

    private final JdbcTemplate jdbcTemplate;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${running-wishes.topic-name}")
    private String topicName;

    @Override
//    @Scheduled(cron = "${running-wishes.publish-upcomingCompetitions.cron}")
    public UpcomingCompetitionsNotif sendUpcomingCompetitions() {
        List<Long> competitionIds = queryForCompetitions();
        UpcomingCompetitionsNotif bodyToPublish = new UpcomingCompetitionsNotif(competitionIds);
        this.kafkaTemplate.send(topicName, bodyToPublish);

        return bodyToPublish;
    }

    // Get competitions that will start tomorrow
    private List<Long> queryForCompetitions() {
        LocalDate tomorrow = getCurrentDate().plusDays(1);
        List<Map<String, Object>> resultRows = jdbcTemplate.queryForList("SELECT c.competition_id "
              + "FROM competitions c "
              + "WHERE c.start_date = ?", tomorrow);
        List<Long> competitionIds = new ArrayList<>();

        for (Map row : resultRows) {
            competitionIds.add((Long) row.get("competition_id"));
        }

        return competitionIds;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}

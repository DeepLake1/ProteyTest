package com.example.restservice.util;

import com.example.restservice.model.StatusType;
import com.example.restservice.model.User;
import com.example.restservice.repository.CrudUserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusChangingUtil {

    final CrudUserRepository crudUserRepository;

    public StatusChangingUtil(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

//    @Scheduled(fixedRate = 30000L)
    public void updateUsersStatus() {
        long updateRateInMinutes = 5L;
        List<User> users = crudUserRepository.findAll();
        List<User> usersWithUpdatedStatus = users.stream()
                .filter(user -> {
                    if (user.getStatus().getStatusType() != StatusType.ONLINE) return false;
                    assert user.getStatus().getLastTimeStatusChanged() != null;
                    return user.getStatus().getLastTimeStatusChanged().until(LocalDateTime.now(), ChronoUnit.MINUTES) > updateRateInMinutes;
                })
                .peek(user -> user.getStatus().setStatusType(StatusType.AWAY))
                .collect(Collectors.toList());
        crudUserRepository.saveAll(usersWithUpdatedStatus);
    }

}

package com.seaweed.hm.scheduler;

import com.satreci.atm.attend.service.AttendService;
import com.satreci.atm.flex.service.FlexAPIService;
import com.satreci.atm.statistics.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class AttendManagerScheduler {

    @Autowired
    private FlexAPIService flexAPIService;

    @Autowired
    private StatisticsService statisticsService;


    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 9) // 9분마다
    public void scheduleSetAccessToken() throws Exception{
        flexAPIService.setAccessToken();
    }

    @Async
    @Scheduled(cron = "0 0 6 1,2,3,4,5,6,7 * ?") // 매달 1~7일 오전 6시에
    public void scheduleSaveAttendStatistics() throws Exception{
        YearMonth thisMonth = YearMonth.now();
        statisticsService.saveMonthlyAttendStatistics(thisMonth.minus(1, ChronoUnit.MONTHS));
    }

}

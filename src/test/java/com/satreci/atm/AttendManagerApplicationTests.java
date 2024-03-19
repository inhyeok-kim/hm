package com.satreci.atm;

import com.satreci.atm.attend.service.AttendService;
import com.seaweed.hm.common.mail.JavaMailService;
import com.satreci.atm.flex.service.FlexAPIService;
import com.satreci.atm.flex.service.FlexService;
import com.satreci.atm.statistics.dto.AttendStatistics;
import com.satreci.atm.statistics.repository.AttendStatisticsRepository;
import com.satreci.atm.statistics.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.time.YearMonth;
import java.util.List;


@SpringBootTest(properties = "spring.profiles.active:local")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
class AttendManagerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private FlexAPIService flexApiService;

	@Autowired
	private FlexService flexservice;

	@Autowired
	private JavaMailService javaMailService;

	@Autowired
	private AttendService attendService;

	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private AttendStatisticsRepository attendStatisticsRepository;

	@Test
	void testTemp(){
		System.out.println(attendStatisticsRepository.findAllDto().toString());
	}


	@Test
	void testAttendStatisticsReport() throws Exception {
		/**
		 * 1. 사번과 기간(월)을 받는다.
		 * 2. 해당 사용자가 부문장인지 확인
		 * 3. 해당 부문의 하위 부서들에 소속된 사번 목록을 확인
		 * 4. 해당 사번 목록과 기간으로 근태 통계 조회
		 *
		 * input : 사번
		 * output : 통계 데이터
		**/
		String paramEmpNum = "200402003";
		String paramStartMonth = "2024-01";
		String paramEndMonth = "2024-02";
		YearMonth start = YearMonth.of(Integer.parseInt(paramStartMonth.split("-")[0]), Integer.parseInt(paramStartMonth.split("-")[1]));
		YearMonth end = YearMonth.of(Integer.parseInt(paramStartMonth.split("-")[0]), Integer.parseInt(paramStartMonth.split("-")[1]));
		List<AttendStatistics> result = statisticsService.getMonthlyAttendStatistics(paramEmpNum, start,end);

		Assert.notEmpty(result,"운영관리부문 1월 근태 특이사항 기록은 비어있지 않습니다.");
	}

}

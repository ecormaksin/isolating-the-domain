package example.domain.model.payroll;

import example.domain.model.attendance.*;
import example.domain.model.contract.HourlyWage;
import example.domain.model.contract.WageCondition;
import example.domain.type.time.ClockTime;
import example.domain.type.time.Minute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WageTest {
    @DisplayName("作業時間と時給で賃金計算が行えること")
    @ParameterizedTest
    @CsvSource({
            // 通常
            "09:00, 10:00, 0, 0, 1000, 1000",
            // 深夜
            "23:00, 24:00, 0, 0, 1000, 1350",
            // 深夜（早朝）
            "01:00, 03:00, 0, 0, 1000, 2700",
            // 通常＋深夜＋休憩
            "20:00, 24:00, 30, 60, 1000, 2850",
            // 通常10時間（超過2時間）
            "09:00, 19:00, 0, 0, 1000, 10500",
            // 通常13時間＋深夜1時間（超過6時間）
            "8:00, 0:00, 60, 60, 1000, 15850"
    })
    void wage(String begin, String end, int breakMinute, int midnightBreakMinute, int hourlyWage, int expected) {
        Attendance attendance = new Attendance(
                new WorkDay("2018-11-04"),
                new WorkTimeRecord(new WorkTimeRange(new WorkStartTime(new ClockTime(begin)), new WorkEndTime(new ClockTime(end))), new NormalBreakTime(new Minute(breakMinute)), new MidnightBreakTime(new Minute(midnightBreakMinute)))
        );
        WageCondition wageCondition = new WageCondition(new HourlyWage(hourlyWage));
        Attendances attendances = new Attendances(Collections.singletonList(attendance));

        Wage wage = Wage.from(attendances.summarize(), wageCondition);

        assertEquals(expected, wage.value.intValue());
    }
}

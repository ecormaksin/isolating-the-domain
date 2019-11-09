package example.domain.model.timerecord.timefact;

import example.domain.type.datetime.QuarterRoundDateTime;
import example.domain.type.time.Minute;
import example.domain.type.time.QuarterHour;

/**
 * 勤務の開始と終了
 */
public class WorkRange {

    StartDateTime startDateTime;
    EndDateTime endDateTime;

    @Deprecated
    WorkRange() {
    }

    public WorkRange(StartDateTime startDateTime, EndDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public QuarterHour quarterHour() {
        Minute duration = QuarterRoundDateTime.between(startDateTime.normalized(), endDateTime.normalized());
        return new QuarterHour(duration);
    }

    public StartDateTime start() {
        return startDateTime;
    }

    public EndDateTime end() {
        return endDateTime;
    }

    public String endTimeText() {
        return endDateTime.endTimeTextWith(startDateTime);
    }
}

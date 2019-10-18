package example.domain.model.timerecord;

import example.domain.type.date.Date;
import example.domain.type.date.DayOfWeek;

/**
 * 勤務終了日付
 */
public class EndDate {

    Date value;

    @Deprecated
    public EndDate() {
    }

    public EndDate(Date date) {
        value = date;
    }

    public EndDate(String value) {
        this(new Date(value));
    }

    public EndDate(WorkDate workDate, EndTime endTime) {
        // TODO: endTimeを元に算出する必要がある。
        value = workDate.value;
    }

    public Date value() {
        return value;
    }

    public int dayOfMonth() {
        return value.dayOfMonth();
    }

    public DayOfWeek dayOfWeek() {
        return value.dayOfWeek();
    }

    public boolean hasSameValue(EndDate other) {
        return value.hasSameValue(other.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Date toDate() {
        return value;
    }
}
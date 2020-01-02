package example.domain.type.date;

import java.util.List;

/**
 * 週
 */
public class Week {
    Dates dates;

    public Week(Dates dates) {
        this.dates = dates;
    }

    public static Week from(List<Date> list) {
        return new Week(new Dates(list));
    }

    public boolean contains(Date date) {
        for (Date d: dates.list) {
            if (date.hasSameValue(d)) {
                return true;
            }
        }
        return false;
    }

    public Date saturday() {
        for (Date d: dates.list) {
            if (d.dayOfWeek() == DayOfWeek.土) {
                return d;
            }
        }
        throw new IllegalStateException("週の情報が不正です。");
    }
}

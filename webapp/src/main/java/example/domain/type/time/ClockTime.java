package example.domain.type.time;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 時刻を時分単位で表す
 */
public class ClockTime {

    LocalTime value;

    @Deprecated
    ClockTime() {
    }

    public ClockTime(String value) {
        this(LocalTime.parse(value, DateTimeFormatter.ofPattern("H:m")));
    }

    public ClockTime(LocalTime value) {
        this.value = value;
    }

    public ClockTime(Integer hour, Integer minute) {
        this(LocalTime.of(hour, minute));
    }

    public static ClockTime later(ClockTime a, ClockTime b) {
        return a.isAfter(b) ? a : b;
    }

    public static ClockTime faster(ClockTime a, ClockTime b) {
        return a.isBefore(b) ? a : b;
    }

    @Override
    public String toString() {
        return DateTimeFormatter.ofPattern("HH:mm").format(value);
    }

    public QuarterRoundClockTime quarterRoundDown() {
        int minute = value.getMinute();
        if (minute < 15) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(0)));
        }
        if (minute < 30) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(15)));
        }
        if (minute < 45) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(30)));
        }
        return new QuarterRoundClockTime(new ClockTime(value.withMinute(45)));
    }

    public QuarterRoundClockTime quarterRoundUp() {
        int minute = value.getMinute();
        if (minute == 0) {
            return new QuarterRoundClockTime(new ClockTime(value));
        }
        if (minute <= 15) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(15)));
        }
        if (minute <= 30) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(30)));
        }
        if (minute <= 45) {
            return new QuarterRoundClockTime(new ClockTime(value.withMinute(45)));
        }
        return new QuarterRoundClockTime(new ClockTime(value.plusHours(1).withMinute(0)));
    }

    public boolean isAfter(ClockTime other) {
        return value.isAfter(other.value);
    }

    public boolean isBefore(ClockTime other) {
        return value.isBefore(other.value);
    }

    public Minute betweenMinute(ClockTime other) {
        Duration duration = Duration.between(this.value, other.value);
        return new Minute((int) duration.toMinutes());
    }

    public boolean sameTime(ClockTime other) {
        return this.value.equals(other.value);
    }

    public LocalTime value() {
        return value;
    }
}

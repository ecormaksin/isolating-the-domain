package example.domain.model.contract;

import example.domain.model.wage.HourlyWage;
import example.domain.model.wage.WageCondition;
import example.domain.type.date.Date;

/**
 * 契約給与
 */
public class ContractWage {
    ContractStartingDate effectiveDate;
    WageCondition wageCondition;

    public ContractWage(ContractStartingDate effectiveDate, WageCondition wageCondition) {
        this.effectiveDate = effectiveDate;
        this.wageCondition = wageCondition;
    }

    public WageCondition wageCondition() {
        return wageCondition;
    }

    public HourlyWage hourlyWage() {
        return wageCondition.baseHourlyWage();
    }

    public ContractStartingDate effectiveDate() {
        return effectiveDate;
    }

    public boolean availableAt(Date date) {
        return effectiveDate.value().hasSameValue(date) || date.isAfter(effectiveDate.value());
    }
}

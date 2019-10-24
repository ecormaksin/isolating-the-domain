package example.domain.model.timerecord.evaluation;

import example.domain.model.employee.EmployeeNumber;

/**
 * 勤務実績
 */
public class TimeRecord {

    EmployeeNumber employeeNumber;
    ActualWorkDateTime actualWorkDateTime;

    @Deprecated
    TimeRecord() {
    }

    public TimeRecord(EmployeeNumber employeeNumber, ActualWorkDateTime actualWorkDateTime) {
        this.employeeNumber = employeeNumber;
        this.actualWorkDateTime = actualWorkDateTime;
    }

    public WorkDate workDate() {
        return actualWorkDateTime.workDate();
    }

    public ActualWorkDateTime actualWorkDateTime() {
        return actualWorkDateTime;
    }

    public EmployeeNumber employeeNumber() {
        return employeeNumber;
    }

    public boolean isWorkedAt(WorkDate other) {
        return this.actualWorkDateTime.workDate().hasSameValue(other);
    }
}

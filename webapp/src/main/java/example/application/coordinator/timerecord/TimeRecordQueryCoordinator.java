package example.application.coordinator.timerecord;

import example.application.service.attendance.AttendanceQueryService;
import example.application.service.employee.EmployeeQueryService;
import example.application.service.timerecord.TimeRecordQueryService;
import example.domain.model.employee.Employee;
import example.domain.model.employee.EmployeeNumber;
import example.domain.model.timerecord.*;
import example.domain.model.timerecord.breaktime.DaytimeBreakTime;
import example.domain.model.timerecord.breaktime.NightBreakTime;
import example.domain.type.time.InputTime;
import example.domain.type.time.Minute;
import org.springframework.stereotype.Service;

@Service
public class TimeRecordQueryCoordinator {

    EmployeeQueryService employeeQueryService;
    TimeRecordQueryService timeRecordQueryService;
    AttendanceQueryService attendanceQueryService;

    public TimeRecordQueryCoordinator(EmployeeQueryService employeeQueryService, TimeRecordQueryService timeRecordQueryService, AttendanceQueryService attendanceQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.timeRecordQueryService = timeRecordQueryService;
        this.attendanceQueryService = attendanceQueryService;
    }

    public TimeRecord timeRecord(EmployeeNumber employeeNumber, WorkDate workDate) {
        Employee employee = employeeQueryService.choose(employeeNumber);
        if (attendanceQueryService.attendanceStatus(employee, workDate).isWork()) {
            return timeRecordQueryService.timeRecord(employeeNumber, workDate);
        }

        return standardTimeRecord(employeeNumber, workDate);
    }

    // TODO 雇用契約から取得する #117
    public TimeRecord standardTimeRecord(EmployeeNumber employeeNumber, WorkDate workDate) {
        InputTime startTime = new InputTime(9, 30);
        InputTime endTime = new InputTime(18, 0);
        return new TimeRecord(employeeNumber,
                new ActualWorkDateTime(
                        new WorkRange(StartDateTime.from(workDate, startTime), EndDateTime.from(workDate, endTime)),
                        new DaytimeBreakTime(new Minute(60)),
                        new NightBreakTime(new Minute(0))
                ));
    }

    public TimeRecord defaultTimeRecord(WorkDate workDate) {
        Employee employee = employeeQueryService.contractingEmployees().list().get(0);
        return standardTimeRecord(employee.employeeNumber(), workDate);
    }
}

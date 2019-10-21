package example._experimental.spa.controller.attendance;

import example._experimental.spa.view.attendance.AttendanceListView;
import example.application.service.attendance.AttendanceQueryService;
import example.application.service.employee.EmployeeQueryService;
import example.application.service.timerecord.TimeRecordRecordService;
import example.domain.model.attendance.Attendance;
import example.domain.model.attendance.WorkMonth;
import example.domain.model.employee.Employee;
import example.domain.model.employee.EmployeeNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances/{employeeNumber}/{yearMonth}")
public class AttendanceAPI {

    EmployeeQueryService employeeQueryService;
    TimeRecordRecordService timeRecordRecordService;
    AttendanceQueryService attendanceQueryService;

    public AttendanceAPI(EmployeeQueryService employeeQueryService, TimeRecordRecordService timeRecordRecordService, AttendanceQueryService attendanceQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.timeRecordRecordService = timeRecordRecordService;
        this.attendanceQueryService = attendanceQueryService;
    }

    @GetMapping
    AttendanceListView get(@PathVariable("employeeNumber") EmployeeNumber employeeNumber,
                           @PathVariable("yearMonth") WorkMonth workMonth) {
        Employee employee = employeeQueryService.choose(employeeNumber);
        Attendance attendance = attendanceQueryService.findAttendance(employee, workMonth);
        return new AttendanceListView(employee, attendance);
    }
}

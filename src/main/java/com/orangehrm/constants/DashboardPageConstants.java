package com.orangehrm.constants;

import com.sun.jna.Structure;

import java.util.List;

public class DashboardPageConstants {

    public static final String PAGE_TITLE = "OrangeHRM";

    public static final String PAGE_URL_CONTAINS = "/dashboard/index";

    public static final int DASHBOARD_WIDGES_COUNT = 7;

    public static final List<String> DASHBOARD_WIDGES_HEADERS = List.of("Time at Work","My Actions", "Quick Launch","Buzz Latest Posts","Employees on Leave Today", "Employee Distribution by Sub Unit", "Employee Distribution by Location");

    public static final List<String> TIME_AT_WORK_ATTENDANCE_STATE = List.of("Punched Out", "Punched In");
    public static final List<String>  TIME_AT_WORK_ATTENDANCE_DETAILS = List.of("Punched Out: ", "Punched In: ");
    public static final String PUNCH_URL_CONTAINS = "attendance/punch";
    public static final String ASSIGN_LEAVE_URL = "/leave/assignLeave";
    public static final String LEAVE_LIST_URL = "/leave/viewLeaveList";
    public static final String TIMESHEETS_URL = "/time/viewEmployeeTimesheet";
    public static final String APPLY_LEAVE_URL = "/leave/applyLeave";
    public static final String MY_TIMESHEET_URL = "/time/viewMyTimesheet";
    public static String My_LEAVE_URL = "/leave/viewMyLeaveList";
}

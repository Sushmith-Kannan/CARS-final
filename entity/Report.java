package entity;

import java.time.LocalDate;

public class Report {

    private int reportID;
    private int incidentID;
    private String reportingOfficer;
    private LocalDate reportDate;
    private String reportDetails;
    private String status;

    public Report() {}

    // Updated constructor to use LocalDate
    public Report(int reportID, int incidentID, String reportingOfficer, LocalDate reportDate, String reportDetails, String status) {
        this.reportID = reportID;
        this.incidentID = incidentID;
        this.reportingOfficer = reportingOfficer;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getReportingOfficer() {
        return reportingOfficer;
    }

    public void setReportingOfficer(String reportingOfficer) {
        this.reportingOfficer = reportingOfficer;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report ID: " + reportID + ", Incident ID: " + incidentID + ", Officer: " + reportingOfficer +
                ", Date: " + reportDate + ", Details: " + reportDetails + ", Status: " + status;
    }
}

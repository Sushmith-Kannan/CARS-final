package dao;

import entity.Incident;
import entity.Report;
import java.util.Collection;

public interface ICrimeAnalysisService {

    boolean createIncident(Incident incident);
    boolean updateIncidentStatus(String status, int incidentId);
    Collection<Incident> getIncidentsInDateRange(String startDate, String endDate);
    Collection<Incident> searchIncidents(String criteria);
    Report generateIncidentReport(Incident incident);
}

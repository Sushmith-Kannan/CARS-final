package dao;

import entity.Incident;
import entity.Report;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static Connection connection;

    // Constructor to initialize the database connection
    public CrimeAnalysisServiceImpl() {
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
        }
    }

    @Override
    public boolean createIncident(Incident incident) {
        String query = "INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID, OfficerID) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, incident.getIncidentType());
            pstmt.setDate(2, Date.valueOf(incident.getIncidentDate()));
            pstmt.setString(3, incident.getLocation());
            pstmt.setString(4, incident.getDescription());
            pstmt.setString(5, incident.getStatus());
            pstmt.setInt(6, incident.getVictimID());
            pstmt.setInt(7, incident.getSuspectID());
            pstmt.setInt(8, incident.getOfficerID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateIncidentStatus(String status, int incidentId) {
        String query = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, incidentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Collection<Incident> getIncidentsInDateRange(String startDate, String endDate) {
        String query = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        Collection<Incident> incidents = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Incident incident = new Incident(
                        rs.getInt("IncidentID"),
                        rs.getString("IncidentType"),
                        rs.getDate("IncidentDate").toString(),
                        rs.getString("Location"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("VictimID"),
                        rs.getInt("SuspectID"),
                        rs.getInt("OfficerID")
                );
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Collection<Incident> searchIncidents(String criteria) {
        String query = "SELECT * FROM Incidents WHERE IncidentType LIKE ?";
        Collection<Incident> incidents = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + criteria + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Incident incident = new Incident(
                        rs.getInt("IncidentID"),
                        rs.getString("IncidentType"),
                        rs.getDate("IncidentDate").toString(),
                        rs.getString("Location"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("VictimID"),
                        rs.getInt("SuspectID"),
                        rs.getInt("OfficerID")
                );
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Report generateIncidentReport(Incident incident) {
        String query = "INSERT INTO Reports (IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status) VALUES (?, ?, ?, ?, ?)";
        Report report = null;
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, incident.getIncidentID());
            pstmt.setInt(2, incident.getOfficerID());
            pstmt.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            pstmt.setString(4, "Generated report for incident ID: " + incident.getIncidentID());
            pstmt.setString(5, "Finalized");
            if (pstmt.executeUpdate() > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    int reportID = keys.getInt(1);
                    report = new Report(reportID, incident.getIncidentID(), "Officer " + incident.getOfficerID(), java.time.LocalDate.now(), "Generated report for incident ID: " + incident.getIncidentID(), "Finalized");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}

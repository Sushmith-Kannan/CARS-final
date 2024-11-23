package main;

import dao.CrimeAnalysisServiceImpl;
import entity.Incident;
import entity.Report;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        // Create instance of CrimeAnalysisServiceImpl
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\nCrime Analysis and Reporting System");
                System.out.println("1. Create Incident");
                System.out.println("2. Update Incident Status");
                System.out.println("3. Get Incidents in Date Range");
                System.out.println("4. Search Incidents");
                System.out.println("5. Generate Incident Report");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Create an incident
                        System.out.print("Enter Incident Type: ");
                        String incidentType = scanner.next();
                        System.out.print("Enter Incident Date (yyyy-MM-dd): ");
                        String incidentDate = scanner.next();
                        System.out.print("Enter Location: ");
                        String location = scanner.next();
                        System.out.print("Enter Description: ");
                        String description = scanner.next();
                        System.out.print("Enter Status: ");
                        String status = scanner.next();
                        System.out.print("Enter Victim ID: ");
                        int victimID = scanner.nextInt();
                        System.out.print("Enter Suspect ID: ");
                        int suspectID = scanner.nextInt();
                        System.out.print("Enter Officer ID: ");
                        int officerID = scanner.nextInt();

                        Incident newIncident = new Incident(0, incidentType, incidentDate, location, description, status, victimID, suspectID, officerID);
                        boolean isCreated = service.createIncident(newIncident);
                        if (isCreated) {
                            System.out.println("Incident created successfully.");
                        } else {
                            System.out.println("Failed to create incident.");
                        }
                        break;

                    case 2:
                        // Update the status of an incident
                        System.out.print("Enter Incident ID: ");
                        int incidentIdToUpdate = scanner.nextInt();
                        System.out.print("Enter new status: ");
                        String newStatus = scanner.next();

                        boolean isUpdated = service.updateIncidentStatus(newStatus, incidentIdToUpdate);
                        if (isUpdated) {
                            System.out.println("Incident status updated successfully.");
                        } else {
                            System.out.println("Incident ID not found. Please try again with a valid ID.");
                        }
                        break;

                    case 3:
                        // Get incidents in a date range
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDate = scanner.next();
                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDate = scanner.next();
                        Collection<Incident> incidents = service.getIncidentsInDateRange(startDate, endDate);
                        if (!incidents.isEmpty()) {
                            for (Incident incident : incidents) {
                                System.out.println("Incident ID: " + incident.getIncidentID() + ", Type: " + incident.getIncidentType() +
                                        ", Date: " + incident.getIncidentDate() + ", Status: " + incident.getStatus());
                            }
                        } else {
                            System.out.println("No incidents found in the specified date range.");
                        }
                        break;

                    case 4:
                        // Search incidents by type
                        System.out.print("Enter Incident Type: ");
                        String searchType = scanner.next();
                        Collection<Incident> searchResults = service.searchIncidents(searchType);
                        if (!searchResults.isEmpty()) {
                            for (Incident incident : searchResults) {
                                System.out.println("Incident ID: " + incident.getIncidentID() + ", Type: " + incident.getIncidentType() +
                                        ", Date: " + incident.getIncidentDate() + ", Status: " + incident.getStatus());
                            }
                        } else {
                            System.out.println("No incidents found with the specified type.");
                        }
                        break;

                    case 5:
                        // Generate incident report
                        System.out.print("Enter Incident ID to generate report: ");
                        int incidentIdToReport = scanner.nextInt();
                        Collection<Incident> reportIncidents = service.searchIncidents("ANY_TYPE"); // Just a placeholder search
                        Incident reportIncident = null;
                        for (Incident inc : reportIncidents) {
                            if (inc.getIncidentID() == incidentIdToReport) {
                                reportIncident = inc;
                                break;
                            }
                        }
                        if (reportIncident != null) {
                            Report report = service.generateIncidentReport(reportIncident);
                            System.out.println("Generated Report: ");
                            System.out.println("Incident ID: " + report.getIncidentID());
                            System.out.println("Report Details: " + report.getReportDetails());
                            System.out.println("Report Date: " + report.getReportDate());
                        } else {
                            System.out.println("Incident not found for report generation.");
                        }
                        break;

                    case 6:
                        // Exit the application
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

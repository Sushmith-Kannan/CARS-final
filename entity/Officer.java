package entity;

public class Officer {
    private int officerID;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String officerRank;
    private String contactInfo;
    private int agencyID;

    // Default Constructor
    public Officer() {
    }

    // Parameterized Constructor
    public Officer(int officerID, String firstName, String lastName, String badgeNumber, String officerRank, String contactInfo, int agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.officerRank = officerRank;
        this.contactInfo = contactInfo;
        this.agencyID = agencyID;
    }

    // Getters and Setters
    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getOfficerRank() {
        return officerRank;
    }

    public void setOfficerRank(String officerRank) {
        this.officerRank = officerRank;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }
}

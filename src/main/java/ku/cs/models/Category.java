package ku.cs.models;

public class Category {
    private String learning;
    private String traffic;
    private String building;
    private String facilities;
    private String person;

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Category(String learning, String traffic, String building, String facilities, String person) {
        this.learning = learning;
        this.traffic = traffic;
        this.building = building;
        this.facilities = facilities;
        this.person = person;
    }
}

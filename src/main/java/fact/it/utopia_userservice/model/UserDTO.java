package fact.it.utopia_userservice.model;

import java.util.List;

public class UserDTO {

    private int userID;
    private String name;
    private int interestID;
    private int birthyear;
    private int score;
    private List<Integer> stationsVisited;

    /*CONSTRUCTORS*/
    public UserDTO() {
    }

    public UserDTO(String name, int interestID, int birthyear) {
        this.name = name;
        this.interestID = interestID;
        this.birthyear = birthyear;
    }

    /*GETTER AND SETTERS*/
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterestID() {
        return interestID;
    }

    public void setInterestID(int interestID) {
        this.interestID = interestID;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getStationsVisited() {
        return stationsVisited;
    }

    public void setStationsVisited(List<Integer> stationsVisited) {
        this.stationsVisited = stationsVisited;
    }

    public void addStationVisited(int stationID){
        stationsVisited.add(stationID);
    }

}

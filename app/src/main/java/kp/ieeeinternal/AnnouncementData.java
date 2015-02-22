package kp.ieeeinternal;



/**
 * Created by Krishna Kalubandi on 22-02-2015.
 */
public class AnnouncementData {
   private String personOne,personTwo;
   private String time;
   private String venue;


    public void setPersonOne(String personOne) {
        this.personOne = personOne;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setPersonTwo(String personTwo) {
        this.personTwo = personTwo;
    }

    public String getPersonOne() {
        return personOne;
    }

    public String getPersonTwo() {
        return personTwo;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }
}

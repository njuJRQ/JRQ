package njurestaurant.njutakeout.entity.job;

import javax.persistence.Embeddable;

@Embeddable
public class EducationExperience {
    private String school;
    private String time;
    private String phase;

    public EducationExperience() {
    }

    public EducationExperience(String school, String time, String phase) {
        this.school = school;
        this.time = time;
        this.phase = phase;
    }

    public String getSchool() {
        return school;
    }

    public void setSchoole(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}

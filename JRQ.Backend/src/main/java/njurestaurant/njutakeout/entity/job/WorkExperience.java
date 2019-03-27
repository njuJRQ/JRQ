package njurestaurant.njutakeout.entity.job;

import javax.persistence.Embeddable;
@Embeddable
public class WorkExperience {
    private String enterprise;
    private String time;
    private String position;
    private String results;
    private String introduction;

    public WorkExperience() {
    }

    public WorkExperience(String enterprise, String time, String position, String results, String introduction) {
        this.enterprise = enterprise;
        this.time = time;
        this.position = position;
        this.results = results;
        this.introduction = introduction;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

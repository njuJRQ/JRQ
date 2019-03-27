package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.entity.job.EducationExperience;

public class EducationExperienceItem {
    private String school;
    private String time;
    private String phase;

    public EducationExperienceItem() {
    }
    public EducationExperienceItem(EducationExperience educationExperience){
        this.school=educationExperience.getSchool();
        this.time=educationExperience.getTime();
        this.phase=educationExperience.getPhase();
    }

    public EducationExperienceItem(String school, String time, String phase) {
        this.school = school;
        this.time = time;
        this.phase = phase;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
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

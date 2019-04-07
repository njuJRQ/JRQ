package njurestaurant.njutakeout.parameters.job;

public class JobCardParameters {
    private String id;
    private String expectPosition;
    private String expectWage;
    private String degree;
    private String introduction;
    private boolean isFresh;
    private String enterprise;
    private String advantage;
    private String city;

    public JobCardParameters() {
    }

    public JobCardParameters(String id, String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, String enterprise, String advantage, String city) {
        this.id = id;
        this.expectPosition = expectPosition;
        this.expectWage = expectWage;
        this.degree = degree;
        this.introduction = introduction;
        this.isFresh = isFresh;
        this.enterprise = enterprise;
        this.advantage = advantage;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpectPosition() {
        return expectPosition;
    }

    public void setExpectPosition(String expectPosition) {
        this.expectPosition = expectPosition;
    }

    public String getExpectWage() {
        return expectWage;
    }

    public void setExpectWage(String expectWage) {
        this.expectWage = expectWage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setFresh(boolean fresh) {
        isFresh = fresh;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package njurestaurant.njutakeout.response.resume;

import njurestaurant.njutakeout.response.Response;

public class ResumeResponce extends Response {
    private ResumeItem resumeItem;
    public ResumeResponce(){
    }
    public ResumeResponce(ResumeItem resumeItem){
        this.resumeItem=resumeItem;
    }

    public ResumeItem getResumeItem() {
        return resumeItem;
    }
    public void setResumeItem(ResumeItem resumeItem){
        this.resumeItem=resumeItem;
    }

}

package njurestaurant.njutakeout.response.resume;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class ResumeListResponce extends Response {
    private List<ResumeItem> resumeItem;

    public ResumeListResponce(){
    }
    public ResumeListResponce(List<ResumeItem> resumeItem){
        this.resumeItem=resumeItem;
    }

    public List<ResumeItem> getResumeItem() {
        return resumeItem;
    }
    public void setResumeItem(List<ResumeItem> resumeItem) {
        this.resumeItem=resumeItem;
    }
}

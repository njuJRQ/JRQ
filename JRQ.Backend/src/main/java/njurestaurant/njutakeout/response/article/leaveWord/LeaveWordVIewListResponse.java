package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class LeaveWordVIewListResponse extends Response {
    private List<LeaveWordViewItem> leaveWordViewItems;

    public LeaveWordVIewListResponse() {
    }

    public LeaveWordVIewListResponse(List<LeaveWordViewItem> leaveWordViewItems) {
        this.leaveWordViewItems = leaveWordViewItems;
    }

    public List<LeaveWordViewItem> getLeaveWordViewItems() {
        return leaveWordViewItems;
    }

    public void setLeaveWordViewItems(List<LeaveWordViewItem> leaveWordViewItems) {
        this.leaveWordViewItems = leaveWordViewItems;
    }
}

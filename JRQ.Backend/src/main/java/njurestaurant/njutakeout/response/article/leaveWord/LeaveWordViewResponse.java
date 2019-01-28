package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.response.Response;

public class LeaveWordViewResponse extends Response {
    private LeaveWordViewItem leaveWordViewItem;

    public LeaveWordViewResponse() {
    }

    public LeaveWordViewResponse(LeaveWordViewItem leaveWordViewItem) {
        this.leaveWordViewItem = leaveWordViewItem;
    }

    public LeaveWordViewItem getLeaveWordViewItem() {
        return leaveWordViewItem;
    }

    public void setLeaveWordViewItem(LeaveWordViewItem leaveWordViewItem) {
        this.leaveWordViewItem = leaveWordViewItem;
    }
}

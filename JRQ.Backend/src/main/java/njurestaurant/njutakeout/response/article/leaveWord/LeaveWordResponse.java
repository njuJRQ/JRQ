package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.response.Response;

public class LeaveWordResponse extends Response {
    private LeaveWordItem leaveWordItem;

    public LeaveWordResponse() {
    }

    public LeaveWordResponse(LeaveWordItem leaveWordItem) {
        this.leaveWordItem = leaveWordItem;
    }

    public LeaveWordItem getLeaveWordItem() {
        return leaveWordItem;
    }

    public void setLeaveWordItem(LeaveWordItem leaveWordItem) {
        this.leaveWordItem = leaveWordItem;
    }
}

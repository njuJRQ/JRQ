package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class LeaveWordListResponse extends Response {
    private List<LeaveWordItem> leaveWordItems;

    public LeaveWordListResponse() {
    }

    public LeaveWordListResponse(List<LeaveWordItem> leaveWordItems) {
        this.leaveWordItems = leaveWordItems;
    }

    public List<LeaveWordItem> getLeaveWordItems() {
        return leaveWordItems;
    }

    public void setLeaveWordItems(List<LeaveWordItem> leaveWordItems) {
        this.leaveWordItems = leaveWordItems;
    }
}

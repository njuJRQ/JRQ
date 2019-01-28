package njurestaurant.njutakeout.response.article.record;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class RecordListResponse extends Response {
    private List<RecordItem> recordItems;

    public RecordListResponse() {
    }

    public RecordListResponse(List<RecordItem> recordItems) {
        this.recordItems = recordItems;
    }

    public List<RecordItem> getRecordItems() {
        return recordItems;
    }

    public void setRecordItems(List<RecordItem> recordItems) {
        this.recordItems = recordItems;
    }
}

package njurestaurant.njutakeout.response.article.record;


import njurestaurant.njutakeout.entity.article.Record;

public class RecordItem {
    private String id; //日志编号
    private String content; //日志内容

    public RecordItem(Record record) {
        this.id = record.getId();
        this.content = record.getContent();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

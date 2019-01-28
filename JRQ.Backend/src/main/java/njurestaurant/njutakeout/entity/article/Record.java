package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="record")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Record {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id; //日志编号

    @Column(length = 600)
    private String content; //日志内容

    public Record() {
    }

    public Record(String content) {
        this.content = content;
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

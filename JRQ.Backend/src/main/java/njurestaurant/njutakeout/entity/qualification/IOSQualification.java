package njurestaurant.njutakeout.entity.qualification;

import javax.persistence.*;

@Entity
@Table
public class IOSQualification {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition="bit(1) default false",nullable=false)
    private boolean status; //ios是否通过审核

    public IOSQualification(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

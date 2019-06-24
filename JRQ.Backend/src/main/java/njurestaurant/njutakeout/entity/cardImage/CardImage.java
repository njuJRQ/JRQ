package njurestaurant.njutakeout.entity.cardImage;

import com.sun.xml.internal.fastinfoset.util.StringIntMap;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "card_image", schema = "njutakeout", catalog = "")
public class CardImage {
    private String imagePath;
    private String id;

    public CardImage(String id , String imagePath){
        this.id=id;
        this.imagePath=imagePath;
    }
    public CardImage(){}



    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardImage that = (CardImage) o;
        return Objects.equals(imagePath, that.imagePath) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imagePath, id);
    }
}

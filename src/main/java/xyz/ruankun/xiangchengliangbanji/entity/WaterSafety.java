package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "water_safety")
public class WaterSafety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WaterSafetyType type;

    @Column(name = "location", nullable = false, columnDefinition = "POINT")
    private String location;

    @Column(name = "address")
    private String address;

    @Column(name = "length")
    private Float length;

    @Column(name = "height")
    private Float height;

    @Column(name = "material")
    private String material;

    @Column(name = "construction_year")
    private Integer constructionYear;

    @Column(name = "responsible_department")
    private String responsibleDepartment;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "if_delete", nullable = false)
    private YesNoEnum ifDelete = YesNoEnum.否;;

    @Column(name = "modify_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime = new Date();

    @Column(name = "create_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "extra_json", nullable = true, columnDefinition = "text")
    private String extraJson;

}
enum WaterSafetyType {
    DI_FANG("堤防"),
    HU_AN("护岸");

    private String value;

    WaterSafetyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}


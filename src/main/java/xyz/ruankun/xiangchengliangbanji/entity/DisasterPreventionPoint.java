package xyz.ruankun.xiangchengliangbanji.entity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "disaster_prevention_points")
public class DisasterPreventionPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DisasterPreventionPointType type;

    @Column(columnDefinition = "POINT")
    private String location;

    private String address;

    private Integer capacity;

    @Column(name = "responsible_person")
    private String responsiblePerson;

    @Column(name = "responsible_person_contact")
    private String responsiblePersonContact;

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
enum DisasterPreventionPointType {
    SHELTER("避难所"),
    CAUTION_ZONE("警戒区"),
    HIGH_RISK_AREA("易发地区");

    private String value;

    DisasterPreventionPointType(String value) {
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
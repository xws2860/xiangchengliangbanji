package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "water_resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterResources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "water_systems_id")
    private Integer waterSystemsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Column(name = "resource_volume")
    private Float resourceVolume;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_usage")
    private ResourceUsage resourceUsage;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_quality")
    private ResourceQuality resourceQuality;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_status")
    private ResourceStatus resourceStatus;

    @Column(name = "extraction_rate")
    private Float extractionRate;

    @Column(name = "data_year")
    @Temporal(TemporalType.DATE)
    private Date dataYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "if_delete", nullable = false)
    private YesNoEnum ifDelete = YesNoEnum.是;

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

enum ResourceType {
    地表水,
    地下水,
    再生水
}

enum ResourceUsage {
    农业,
    工业,
    生活,
    生态
}

enum ResourceQuality {
    优,
    良,
    中,
    差
}

enum ResourceStatus {
    充足,
    紧张,
    枯竭
}

package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "water_systems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaterSystems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "water_type", nullable = false)
    private WaterType waterType;

    @Column(name = "river_code")
    private String riverCode;

    @Column(name = "river_name", nullable = false)
    private String riverName;

    @Column(name = "river_alias")
    private String riverAlias;

    @Column(name = "total_length")
    private Float totalLength;

    @Column(name = "segment_length")
    private Float segmentLength;

    @Column(name = "basin_area")
    private Float basinArea;

    @Column(name = "estuary_flow")
    private Float estuaryFlow;

    @Column(name = "affiliated_basin")
    private String affiliatedBasin;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank")
    private Bank bank;

    @Column(name = "survey_code")
    private String surveyCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_level")
    private BranchLevel branchLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "cross_type")
    private CrossType crossType;

    @Enumerated(EnumType.STRING)
    @Column(name = "river_level")
    private RiverLevel riverLevel;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "end_location")
    private String endLocation;

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

enum Bank {
    左岸,
    右岸

}
enum BranchLevel {
    一级,
    二级,
    三级,
    四级,
    五级
}

enum CrossType {
    无跨区,
    跨省,
    跨市,
    跨县
}


enum RiverLevel {
    省级,
    市级,
    乡镇级,
    村级
}
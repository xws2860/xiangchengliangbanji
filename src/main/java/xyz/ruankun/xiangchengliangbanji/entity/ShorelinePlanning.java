package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shoreline_planning")
@Accessors(chain = true)
public class ShorelinePlanning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "planning_name", nullable = false)
    private String planningName;

    @JoinColumn(name = "water_systems_id")
    private Integer waterSystemsId;

    @Column(name = "shoreline_type", nullable = false)
    private String shorelineType;

    @Column(name = "planning_start_point", nullable = false)
    private String planningStartPoint;

    @Column(name = "planning_end_point", nullable = false)
    private String planningEndPoint;

    @Column(name = "planning_length")
    private Float planningLength;

    @Column(name = "start_pile_number")
    private String startPileNumber;

    @Column(name = "end_pile_number")
    private String endPileNumber;

    @Column(name = "planning_description")
    private String planningDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

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

enum ApprovalStatus {
    未审批,
    已审批,
    驳回
}
package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "health_evaluation")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "water_systems_id")
    private Integer waterSystemsId;

    @Column(name = "evaluation_standard", nullable = false)
    private String evaluationStandard;

    @Column(name = "river_chief", nullable = false)
    private String riverChief;

    @Column(name = "health_score", nullable = false)
    private Double healthScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status", nullable = false)
    private HealthStatus healthStatus;

    @Column(name = "evaluation_conclusion")
    private String evaluationConclusion;

    @Column(name = "attachment")
    private String attachment;

    @Column(name = "evaluation_date")
    private LocalDateTime evaluationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "if_delete", nullable = false)
    private YesNoEnum ifDelete = YesNoEnum.否;

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

enum HealthStatus {
    非常健康,
    健康,
    亚健康,
    不健康,
    劣态
}

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
@Table(name = "relocation")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "affected_area", nullable = false)
    private String affectedArea;

    @Column(name = "affected_population", nullable = false)
    private Integer affectedPopulation;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "relocation_type", nullable = false)
    private String relocationType;

    @Column(name = "relocation_reason", nullable = false)
    private String relocationReason;

    @Column(name = "relocation_method", nullable = false)
    private String relocationMethod;

    @Column(name = "relocation_budget")
    private Double relocationBudget;

    @Column(name = "relocation_status", nullable = false)
    private String relocationStatus;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_phone")
    private String contactPhone;

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


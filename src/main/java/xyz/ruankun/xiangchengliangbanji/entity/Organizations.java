package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "organizations")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private String level;

    @Column(name = "parent_id")
    private int parentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "if_delete", nullable = false)
    private YesNoEnum ifDelete = YesNoEnum.否;;

    @Column(name = "modify_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime = new Date();

    @Column(name = "create_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

}


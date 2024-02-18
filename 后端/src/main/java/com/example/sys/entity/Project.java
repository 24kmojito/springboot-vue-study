package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-04-04
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Project  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    private Integer companyId;

    private Integer loanId;

    private String projectName;

    private String projectIntroduction;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String projectStartTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String projectEndTime;

    private BigDecimal projectCost;

    private String projectComment;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 项目其他备注
     */
    private String projectRemark;

    private Integer institutionId;

    /**
     * 0:未结束 1:已结束
     */
    private Integer projectState;

    private Integer deletedState;
}

package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class Applyloan {

    private static final long serialVersionUID = 1L;

    @TableId(value = "applyloan_id", type = IdType.AUTO)
    private Integer applyloanId;

    private Integer companyId;

    private String companyName;

    private Integer institutionId;

    private String applyloanIntroduction;

    private String loanPeriod;

    private String loanAmount;

    private String loanRemark;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0:未通过 1:已通过
     */
    private Integer applyloanState;

    private Integer deletedState;


}

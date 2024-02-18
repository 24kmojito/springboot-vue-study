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
public class Company {

    private static final long serialVersionUID = 1L;

    @TableId(value = "company_id", type = IdType.AUTO)
    private Integer companyId;

    private String companyName;

    private String companyInitialYear;

    private String companyEmail;

    private String companyPwd;

    /**
     * 近三年企业年报
     */
    private String companyAnnalsannual;

    /**
     * 最近三个月企业财务报表
     */
    private String companyFinancialStatements;

    /**
     * 近六个月企业对公账单
     */
    private String companyBankReconciliation;

    private String companyQualification;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0：未审核 1：已审核
     */
    private Integer companyCheckState;

    /**
     * 0：未拉黑 1：已拉黑
     */
    private Integer companyBlockState;

    private Integer deletedState;


}

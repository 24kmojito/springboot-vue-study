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
public class Loan {

    private static final long serialVersionUID = 1L;

    @TableId(value = "loan_id", type = IdType.AUTO)
    private Integer loanId;

    private Integer companyId;

    private String companyName;

//    @NotNull(message = "金融机构ID不能为空")
    private Integer institutionId;

    private String loanIntroduction;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String loanPeriod;

    private String loanAmmount;

    private String loanInterest;

    private String loanBankReconciliation;

    private String loanRecord;

    private String loanComment;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String loanRemark;

    /**
     * 0:未结束 1:已结束
     */
    private Integer loanState;


    private Integer deletedState;
}

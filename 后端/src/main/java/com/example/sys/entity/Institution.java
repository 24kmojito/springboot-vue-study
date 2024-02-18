package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "institution_id", type = IdType.AUTO)
    private Integer institutionId;

    private String institutionEmail;

    private String institutionPwd;

    private String institutionName;

    private String institutionIntroduction;

    private String institutionCertificate;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0：未审核 1：已审核
     */
    private Integer institutionCheckState;

    /**
     * 0：未拉黑 1：已拉黑
     */
    private Integer institutionBlockState;

    private Integer deletedState;


}

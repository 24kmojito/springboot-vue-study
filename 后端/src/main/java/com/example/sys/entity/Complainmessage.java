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
public class Complainmessage {

    private static final long serialVersionUID = 1L;

    @TableId(value = "complain_message_id", type = IdType.AUTO)
    private Integer complainMessageId;

    private Integer companyId;

//    private Integer institutionId;

    private String complainText;

    private String complainInstitutionName;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0：未审核 1:已审核
     */
    private Integer complainState;

    private Integer deletedState;


}

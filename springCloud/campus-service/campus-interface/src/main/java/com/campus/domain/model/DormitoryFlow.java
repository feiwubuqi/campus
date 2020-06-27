package com.campus.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengmh@belink.com
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DormitoryFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入住时间
     */
    @TableField("INTO_TIME")
    private String intoTime;

    /**
     * 学号
     */
    @TableField("ACCOUNT_SNO")
    private String accountSno;

    /**
     * 门牌号
     */
    @TableField("HOUSE_NO")
    private String houseNo;

    /**
     * 床位
     */
    @TableField("BED_NO")
    private String bedNo;

    /**
     * 迁出时间
     */
    @TableField("OUT_TIME")
    private String outTime;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private String createTime;

    /**
     * 使用状态 00-未使用，01-正在使用
     */
    @TableField("STATUS")
    private String status;

    /**
     * id
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private String id;


}

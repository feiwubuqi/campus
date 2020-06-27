package com.campus.domain.model;

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
 * @since 2020-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 学号
     */
    @TableId("ACCOUNT_SNO")
    private String accountSno;

    /**
     * 姓名
     */
    @TableField("ACCOUNT_NAME")
    private String accountName;

    /**
     * 密码
     */
    @TableField("ACCOUNT_PWD")
    private String accountPwd;

    /**
     * 学院
     */
    @TableField("ACADEMY")
    private String academy;

    /**
     * 联系方式
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 班级
     */
    @TableField("GRADE")
    private String grade;

    /**
     * 照片地址
     */
    @TableField("IMG_ADDRESS")
    private String imgAddress;

    /**
     * 籍贯
     */
    @TableField("NATIVE_PLACE")
    private String nativePlace;

    /**
     * 0-普通用户，1-管理员
     */
    private String level;


}

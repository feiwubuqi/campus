package com.campus.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("admainInfo")
public class AdmainInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 超级管理员账号
     */
    private String author;

    /**
     * 超级管理员密码
     */
    private String password;


}

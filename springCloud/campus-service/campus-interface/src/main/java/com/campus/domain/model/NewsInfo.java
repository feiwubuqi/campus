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
public class NewsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 作者
     */
    @TableField("AUTHOR")
    private String author;

    /**
     * 引用地址
     */
    @TableField("FROM_ADDRESS")
    private String fromAddress;

    /**
     * 时间
     */
    @TableField("TIME")
    private String time;

    /**
     * 阅读次数
     */
    @TableField("COUNT_NUM")
    private String countNum;

    /**
     * 内容
     */
    @TableField("CONTENT")
    private String content;

    @TableField("IMG_ADDRESS")
    private String imgAddress;

    /**
     * 序列id
     */
    @TableId(value = "SEQUENCE_ID",type = IdType.AUTO)
    private String sequenceId;


}

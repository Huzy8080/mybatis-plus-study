package com.kingdee.zwy.test.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author HUZHAOYANG
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TAtTask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务Id
     */
    @TableId("taskId")
    private String taskId;

    /**
     * 执行任务用户Id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 批量Id
     */
    @TableField("batchId")
    private String batchId;

    @TableField("companyId")
    private Integer companyId;

    /**
     * 国税or地税 (gs ds)
     */
    @TableField("taxOffice")
    private String taxOffice;

    /**
     * 区域拼音
     */
    private String region;

    /**
     * 任务类型
     */
    @TableField("taskAction")
    private String taskAction;

    /**
     * 任务进程
     */
    @TableField("taskProgress")
    private String taskProgress;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 起始期
     */
    @TableField("periodBegin")
    private String periodBegin;

    /**
     * 结束期
     */
    @TableField("periodEnd")
    private String periodEnd;

    /**
     * 税务类型，增值税（10101）企业所得税（10104）
     */
    @TableField("taxType")
    private Integer taxType;

    private String data;

    /**
     * 最后操作结果
     */
    private String message;

    /**
     * 扩展字段
     */
    private String extend;

    /**
     * 起始时间
     */
    @TableField("startDate")
    private LocalDateTime startDate;

    /**
     * 任务执行次数
     */
    @TableField("exeNumber")
    private Integer exeNumber;

    /**
     * 纳税记录
     */
    @TableField("taxRecordId")
    private Integer taxRecordId;

    /**
     * 税号验证手机号码
     */
    @TableField("shPhone")
    private String shPhone;

    /**
     * 实名验证方式：1-新税局手机验证码方式，2-老税局图片验证码
     */
    @TableField("newOldLoginType")
    private Boolean newOldLoginType;

    /**
     * 任务执行节点
     */
    @TableField("taskNode")
    private String taskNode;

    /**
     * 综合申报状态：0-待申报，1-待扣款，2-已完成
     */
    private Boolean zhsb;

    /**
     * 非居民申报状态：0-待申报，1-待扣款，2-已完成
     */
    private Boolean fjmsb;

    /**
     * 任务结束时间
     */
    @TableField("endDate")
    private LocalDateTime endDate;

    /**
     * 手机号码是否插在猫池上：1-Yes在，0-不在
     */
    private Boolean phoneonline;

    /**
     * 任务执行时间
     */
    @TableField("beginDate")
    private LocalDateTime beginDate;


}

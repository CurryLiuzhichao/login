package com.lzc.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzc
 * @since 2022-05-17 11:34:19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("头像")
    private String headUrl;

    @ApiModelProperty("性别   0：男   1：女    2：保密")
    private Integer gender;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("超级管理员   0：否   1：是")
    private Integer superAdmin;

    @ApiModelProperty("状态  0：停用   1：正常")
    private Long status;

    @ApiModelProperty("创建者")
    private Long creator;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("更新者")
    private Long updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateDate;


}

package com.itheima.reggie.entity.mysql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "员工数据模型")
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工id", required = true)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "员工电话号码")
    private String phone;

    @ApiModelProperty(value = "员工性别")
    private String sex;

    @ApiModelProperty(value = "员工身份证")
    private String idNumber;  // 身份证下划线转驼峰

    @ApiModelProperty(value = "员工在职状态")
    private Integer status;

}

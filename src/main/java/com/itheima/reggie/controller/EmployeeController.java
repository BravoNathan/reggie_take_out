package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.mysql.Employee;
import com.itheima.reggie.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Api(tags = "员工管理")
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        /**
         * 1、将页面提交的密码password进行md5加密处理
         * 2、根据页面提交的用户名username查询数据库
         * 3、如果没有查询到则返回登录失败结果
         * 4、密码比对，如果不一致则返回登录失败结果
         * 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
         * 6、登录成功，将员工id存入Session并返回登录成功结果
         */
        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return Result.error(("登录失败"));
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(! emp.getPassword().equals(password)){
            return Result.error("登录失败");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return Result.error("账号已经被禁用");

        }

        //6.登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());

        return  Result.success(emp);
    }


    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户登出")
    public Result<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return Result.success("退出成功");

    }

    /**
     * 新增员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增员工")
    public Result<String> save(HttpServletRequest request ,@RequestBody Employee employee){
        log.info("新增员工，员工信息:{}", employee.toString());

        //设置初始密码：并用md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //获取当前用户id
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return Result.success("新增员工成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @ApiOperation(value = "查询分页")
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}, name ={}",page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤添加
        queryWrapper.like(StringUtils.hasText(name), Employee::getName, name);
        //添加排序的条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);

    }

    /**
     * 根据用户ID取修改状态
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新员工信息")
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee){
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        long id = Thread.currentThread().getId();
        log.info("线程为{}",id);
        employeeService.updateById(employee);
        return Result.success("员工信息修改成功");

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询员工信息")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据ID查询员工信息");
        Employee employee = employeeService.getById(id);
        if( employee == null){
            return Result.error("查无此人");
        }
        return Result.success(employee);
    }

}

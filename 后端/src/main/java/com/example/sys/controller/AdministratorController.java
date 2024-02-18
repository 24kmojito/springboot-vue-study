package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.dto.LoginDto;
import com.example.common.lang.Result;
import com.example.sys.entity.Administrator;
import com.example.sys.service.IAdministratorService;
import com.example.util.JwtUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-04-04
 */
@RestController
//@RequestMapping("/administrator")
public class AdministratorController  {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    IAdministratorService administratorService;

    public Result administratorLogin(LoginDto loginDto, HttpServletResponse response)
    {
        Administrator administrator = administratorService.getOne(new QueryWrapper<Administrator>().eq("administrator_email", loginDto.getUser_email()));
        Assert.notNull(administrator, "用户不存在");
        if(!SecureUtil.md5(administrator.getAdministratorPwd()).equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(administrator.getAdministratorId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("administrator_id", administrator.getAdministratorId())
                .put("administrator_email", administrator.getAdministratorEmail())
                .map());
    }

    @PostMapping("/registerAdministrator")
    public Result registerAdministrator(@Validated @RequestBody Administrator administrator) {
        System.out.println(administrator.toString());
        Administrator temp = null;
        QueryWrapper<Administrator> wrapper = new QueryWrapper<Administrator>();


        if(administratorService.getOne(wrapper.eq("administrator_email",administrator.getAdministratorEmail())) != null) {
            return Result.fail("管理员信息已存在");
        }

        temp = new Administrator();
        System.out.println(administrator);
        BeanUtil.copyProperties(administrator, temp);
        System.out.println(temp);
        administratorService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

}

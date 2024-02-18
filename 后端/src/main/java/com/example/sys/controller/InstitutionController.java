package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.dto.LoginDto;
import com.example.common.lang.Result;
import com.example.sys.entity.Institution;
import com.example.sys.service.IInstitutionService;
import com.example.util.JwtUtils;
import com.example.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
//@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    IInstitutionService institutionService;

    public Result institutionLogin(LoginDto loginDto, HttpServletResponse response)
    {
        Institution institution = institutionService.getOne(new QueryWrapper<Institution>().eq("institution_email", loginDto.getUser_email()).eq("institution_check_state",1));
        Assert.notNull(institution, "用户不存在");
        if(!SecureUtil.md5(institution.getInstitutionPwd()).equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(institution.getInstitutionId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("institution_id", institution.getInstitutionId())
                .put("institution_name", institution.getInstitutionName())
                .put("institution_email", institution.getInstitutionEmail())
                .map());
    }

    @GetMapping("/getInstitutionsWithoutCheck")
    public Result getInstitutionsWithoutCheck(@RequestParam(defaultValue = "1") Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = institutionService.page(page, new QueryWrapper<Institution>()
                .orderByAsc("update_time").eq("institution_check_state", 0));
        return Result.succ(pageData);
    }

    @GetMapping("/getInstitutionsWithCheck")
    public Result getInstitutionsWithCheck(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = institutionService.page(page, new QueryWrapper<Institution>()
                .orderByAsc("update_time").eq("institution_check_state", 1));
        return Result.succ(pageData);
    }

    @GetMapping("/getInstitutionsWithBlock")
    public Result getInstitutionsWithBlock(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = institutionService.page(page, new QueryWrapper<Institution>()
                .orderByAsc("update_time").eq("institution_block_state", 1));
        return Result.succ(pageData);
    }

    @GetMapping("/getInstitutionsWithoutBlock")
    public Result getInstitutionsWithoutBlock(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = institutionService.page(page, new QueryWrapper<Institution>()
                .orderByAsc("update_time").eq("institution_block_state", 0));
        return Result.succ(pageData);
    }

    @GetMapping("/getInstitutionsById")
    public Result getInstitutionsById(@RequestParam(defaultValue = "1")Integer currentPage ,@RequestParam Integer institutionId)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = institutionService.page(page, new QueryWrapper<Institution>()
                .orderByAsc("update_time").eq("institution_block_state", 0).eq("institution_id",institutionId));
        return Result.succ(pageData);
    }

    @PostMapping("/registerInstitution")
    public Result registerInstitution(@Validated @RequestBody Institution institution) {
        System.out.println(institution.toString());
        Institution temp = null;
        QueryWrapper<Institution> wrapper = new QueryWrapper<Institution>();


        if(institutionService.getOne(wrapper.eq("institution_email",institution.getInstitutionEmail())) != null) {
            return Result.fail("机构信息已存在");
        }
        else
        {
            temp = new Institution();
            temp.setInstitutionCheckState(0);
            temp.setInstitutionBlockState(0);
            temp.setDeletedState(0);
        }
        BeanUtil.copyProperties(institution, temp,"institutionCheckState","institutionBlockState","deletedState");
        institutionService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @RequiresAuthentication
    @PostMapping("/updateInstitution")
    public Result updateInstitution(@Validated @RequestBody Institution institution) {
        System.out.println(institution.toString());
        Institution temp = null;
        if(institution.getInstitutionId() != null) {
            temp = institutionService.getById(institution.getInstitutionId());
            Assert.isTrue(temp.getInstitutionId() != ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该机构信息不存在");
        }
        BeanUtil.copyProperties(institution, temp, "institutionId", "companyId","updateTime", "institutionState");
        institutionService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @RequiresAuthentication
    @PostMapping("/checkInstitution")
    public Result checkInstitution(@Validated @RequestBody Institution institution) {
        System.out.println(institution.toString());
        Institution temp = null;
        if(institution.getInstitutionId() != null) {
            temp = institutionService.getById(institution.getInstitutionId());
            temp.setInstitutionCheckState(1);
//            Assert.isTrue(temp.getInstitutionId() != ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该机构信息不存在");
        }
        BeanUtil.copyProperties(institution, temp, "institutionId", "companyId","updateTime", "institutionCheckState");
        institutionService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @RequiresAuthentication
    @PostMapping("/blockInstitution")
    public Result blockInstitution(@Validated @RequestBody Institution institution) {
        System.out.println(institution.toString());
        Institution temp = null;
        if(institution.getInstitutionId() != null) {
            temp = institutionService.getById(institution.getInstitutionId());
            temp.setInstitutionBlockState(1);
//            Assert.isTrue(temp.getInstitutionId() != ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该机构信息不存在");
        }
        BeanUtil.copyProperties(institution, temp, "institutionId", "companyId","updateTime", "institutionBlockState");
        institutionService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }


    @RequiresAuthentication
    @PostMapping("/deleteBlockInstitution")
    public Result deletebBlockInstitution(@Validated @RequestBody Institution institution) {
        System.out.println(institution.toString());
        Institution temp = null;
        if(institution.getInstitutionId() != null) {
            temp = institutionService.getById(institution.getInstitutionId());
            temp.setInstitutionBlockState(0);
//            Assert.isTrue(temp.getInstitutionId() != ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该机构信息不存在");
        }
        BeanUtil.copyProperties(institution, temp, "institutionId", "companyId","updateTime", "institutionBlockState");
        institutionService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }








}

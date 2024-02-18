package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.dto.LoginDto;
import com.example.common.lang.Result;
import com.example.sys.entity.Company;
import com.example.sys.service.ICompanyService;
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
//@RequestMapping("/company")
public class CompanyController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ICompanyService companyService;

    public Result companyLogin(LoginDto loginDto, HttpServletResponse response)
    {
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("company_email", loginDto.getUser_email()).eq("company_check_state",1));
        Assert.notNull(company, "用户不存在");
        if(!SecureUtil.md5(company.getCompanyPwd()).equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(company.getCompanyId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("company_id", company.getCompanyId())
                .put("company_name", company.getCompanyName())
                .put("company_email", company.getCompanyEmail())
                .put("company_initial_year",company.getCompanyInitialYear())
                .put("company_qualification",company.getCompanyQualification())
                .map());
    }

    @GetMapping("/getCompanysWithoutCheck")
    public Result getCompanysWithoutCheck(@RequestParam(defaultValue = "1") Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = companyService.page(page, new QueryWrapper<Company>()
                .orderByAsc("update_time").eq("company_check_state", 0));
        return Result.succ(pageData);
    }

    @GetMapping("/getCompanysWithCheck")
    public Result getCompanysWithCheck(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = companyService.page(page, new QueryWrapper<Company>()
                .orderByAsc("update_time").eq("company_check_state", 1));
        return Result.succ(pageData);
    }

    @GetMapping("/getCompanysWithBlock")
    public Result getCompanysWithBlock(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = companyService.page(page, new QueryWrapper<Company>()
                .orderByAsc("update_time").eq("company_block_state", 1));
        return Result.succ(pageData);
    }

    @GetMapping("/getCompanysWithoutBlock")
    public Result getCompanysWithoutBlock(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = companyService.page(page, new QueryWrapper<Company>()
                .orderByAsc("update_time").eq("company_block_state", 0));
        return Result.succ(pageData);
    }

    @PostMapping("/registerCompany")
    public Result registerCompany(@Validated @RequestBody Company company) {
        System.out.println(company.toString());
        Company temp = null;
        QueryWrapper<Company> wrapper = new QueryWrapper<Company>();


        if(companyService.getOne(wrapper.eq("company_email",company.getCompanyEmail())) != null) {
            return Result.fail("企业信息已存在");
        }
        else
        {
            temp = new Company();
            temp.setCompanyCheckState(0);
            temp.setCompanyBlockState(0);
            temp.setDeletedState(0);
        }
        BeanUtil.copyProperties(company, temp,"companyCheckState","companyBlockState","deletedState");
        companyService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @RequiresAuthentication
    @PostMapping("/updateCompany")
    public Result updateCompany(@Validated @RequestBody Company company) {
        System.out.println(company.toString());
        Company temp = null;
        if(company.getCompanyId() != null) {
            temp = companyService.getById(company.getCompanyId());
            Assert.isTrue(temp.getCompanyId() == ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该企业信息不存在");
        }
        BeanUtil.copyProperties(company, temp, "companyId",  "updateTime", "companyState");
        companyService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @GetMapping("/getCompanyById")
    public Result getCompanyById(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = companyService.page(page, new QueryWrapper<Company>()
                .orderByAsc("update_time").eq("company_block_state", 0).eq("company_id",ShiroUtil.getProfile().getId()));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/checkCompany")
    public Result checkCompany(@Validated @RequestBody Company company) {
        System.out.println(company.toString());
        Company temp = null;
        if(company.getCompanyId() != null) {
            temp = companyService.getById(company.getCompanyId());
            temp.setCompanyCheckState(1);
//            Assert.isTrue(temp.getCompanyId() == ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            return Result.fail("该企业信息不存在");
        }
        BeanUtil.copyProperties(company, temp, "companyId",  "updateTime", "companyCheckState");
        companyService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

}

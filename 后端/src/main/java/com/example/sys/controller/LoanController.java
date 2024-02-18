package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.sys.entity.Company;
import com.example.sys.entity.Loan;
import com.example.sys.service.ICompanyService;
import com.example.sys.service.ILoanService;
import com.example.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-04-04
 */
@RestController
//@RequestMapping("/loan")
public class LoanController  {

    @Autowired
    ILoanService loanService;

    @Autowired
    ICompanyService companyService;

    @RequiresAuthentication
    @GetMapping("/getAllLoan")
    public Result getAllLoan(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = loanService.page(page, new QueryWrapper<Loan>()
                .orderByDesc("update_time").eq("institution_id",ShiroUtil.getProfile().getId()));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @GetMapping("/getAllLoanByCompanyId")
    public Result getAllLoanByCompanyId(@RequestParam(defaultValue = "1")Integer currentPage , @RequestParam Integer companyId)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = loanService.page(page, new QueryWrapper<Loan>()
                .orderByDesc("update_time").eq("company_id", companyId));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @GetMapping("/getCompanyLoanByCompanyId")
    public Result getCompanyLoanByCompanyId(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = loanService.page(page, new QueryWrapper<Loan>()
                .orderByDesc("update_time").eq("company_id", ShiroUtil.getProfile().getId()));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/editLoan")
    public Result editLoan(@Validated @RequestBody Loan loan)
    {
        System.out.println(loan.toString());
        Loan temp = null;
        if(loan.getLoanId() != null) {
            temp = loanService.getById(loan.getLoanId());
            Assert.isTrue(temp.getInstitutionId() == ShiroUtil.getProfile().getId(), "没有权限进行贷款信息编辑");
        } else {
            QueryWrapper<Company> wrapper = new QueryWrapper<>();
            temp = new Loan();
            System.out.println(ShiroUtil.getProfile().getId());
            temp.setInstitutionId(ShiroUtil.getProfile().getId());
            temp.setCompanyId(companyService.getOne(wrapper.eq("company_name",loan.getCompanyName())).getCompanyId());
            temp.setDeletedState(0);
            temp.setLoanState(0);
        }
        BeanUtil.copyProperties(loan, temp, "loanId","companyId", "institutionId", "updateTime", "loanState","deletedState");
        loanService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

}

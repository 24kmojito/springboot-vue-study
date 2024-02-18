package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.sys.entity.Applyloan;
import com.example.sys.service.IApplyloanService;
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
public class ApplyloanController {
    @Autowired
    IApplyloanService applyloanService;

    @RequiresAuthentication
    @GetMapping("/getAllApplyLoan")
    public Result getAllApplyLoan(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = applyloanService.page(page, new QueryWrapper<Applyloan>()
                .orderByDesc("create_time").eq("institution_id",ShiroUtil.getProfile().getId()));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/editApplyloan")
    public Result edit(@Validated @RequestBody Applyloan applyloan) {
        System.out.println(applyloan.toString());
        Applyloan temp = null;
        if(applyloan.getApplyloanId() != null) {
            temp = applyloanService.getById(applyloan.getApplyloanId());
            Assert.isTrue(temp.getApplyloanId() == ShiroUtil.getProfile().getId(), "没有权限进行贷款申请");
        } else {
            temp = new Applyloan();
            temp.setCompanyId(ShiroUtil.getProfile().getId());
            temp.setApplyloanState(0);
            temp.setDeletedState(0);
        }
        BeanUtil.copyProperties(applyloan, temp, "applyloanId", "companyId", "updateTime", "applyloanState","deletedState");
        applyloanService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }


}

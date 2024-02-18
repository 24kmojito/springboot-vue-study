package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.sys.entity.Complainmessage;
import com.example.sys.service.IComplainmessageService;
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
//@RequestMapping("/complainmessage")
public class ComplainmessageController {
    @Autowired
    IComplainmessageService complainmessageService;

    @RequiresAuthentication
    @GetMapping("/getAllComplainmessage")
    public Result getAllComplainmessage(@RequestParam(defaultValue = "1")Integer currentPage)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = complainmessageService.page(page, new QueryWrapper<Complainmessage>()
                .orderByDesc("create_time").eq("complain_state", 0));
        return Result.succ(pageData);
    }



    @RequiresAuthentication
    @PostMapping("/editComplainmessage")
    public Result editComplainmessage(@Validated @RequestBody Complainmessage complainmessage)
    {
        System.out.println(complainmessage.toString());
        Complainmessage temp = null;
        if(complainmessage.getComplainMessageId() != null) {
            temp = complainmessageService.getById(complainmessage.getComplainMessageId());
            Assert.isTrue(temp.getCompanyId() == ShiroUtil.getProfile().getId(), "没有权限进行申诉信息编辑");
        } else {
            temp = new Complainmessage();
            temp.setCompanyId(ShiroUtil.getProfile().getId());
            temp.setComplainState(0);
            temp.setDeletedState(0);
        }
        BeanUtil.copyProperties(complainmessage, temp, "complainMessageId", "companyId", "updateTime", "complainState","deletedState");
        complainmessageService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

    @RequiresAuthentication
    @PostMapping("/deleteComplainmessage")
    public Result deleteComplainmessage(@Validated @RequestBody Complainmessage complainmessage)
    {
        System.out.println(complainmessage.toString());
        Complainmessage temp = null;
        if(complainmessage.getComplainMessageId() != null) {
            temp = complainmessageService.getById(complainmessage.getComplainMessageId());
            temp.setComplainState(1);
       //     Assert.isTrue(temp.getCompanyId() == ShiroUtil.getProfile().getId(), "没有权限进行申诉信息编辑");
        } else {
            temp = new Complainmessage();
            temp.setCompanyId(ShiroUtil.getProfile().getId());
//            temp.setComplainState(1);
            temp.setDeletedState(0);
        }
        BeanUtil.copyProperties(complainmessage, temp, "complainMessageId", "companyId", "updateTime", "complainState","deletedState");
        complainmessageService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }

}

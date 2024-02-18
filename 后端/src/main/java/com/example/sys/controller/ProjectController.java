package com.example.sys.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.sys.entity.Project;
import com.example.sys.service.IProjectService;
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
//@RequestMapping("/project")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @RequiresAuthentication
    @GetMapping("/getAllProject")
    public Result getAllProject(@RequestParam(defaultValue = "1")Integer currentPage , @RequestParam Integer loanId)
    {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = projectService.page(page, new QueryWrapper<Project>()
                .orderByDesc("update_time").eq("loan_id", loanId));
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/editProject")
    public Result editProject(@Validated @RequestBody Project project,@RequestParam Integer loanId,@RequestParam Integer companyId)
    {
        System.out.println(project.toString());
        Project temp = null;
        if(project.getProjectId() != null) {
            temp = projectService.getById(project.getProjectId());
            Assert.isTrue(temp.getInstitutionId() == ShiroUtil.getProfile().getId(), "没有权限进行项目信息编辑");
        } else {
            temp = new Project();
            temp.setInstitutionId(ShiroUtil.getProfile().getId());
            temp.setProjectState(0);
            temp.setDeletedState(0);
            temp.setCompanyId(companyId);
            temp.setLoanId(loanId);
        }
        BeanUtil.copyProperties(project, temp, "projectId","companyId","loanId", "institutionId", "updateTime", "projectState","deletedState");
        projectService.saveOrUpdate(temp);
        return Result.succ("操作成功", null);
    }



}

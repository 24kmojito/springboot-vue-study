package com.example.sys.controller;

import com.example.common.dto.LoginDto;
import com.example.common.lang.Result;
import com.example.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    InstitutionController institutionController;
    @Autowired
    CompanyController companyController;
    @Autowired
    AdministratorController administratorController;
    
    
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        switch (loginDto.getUser_status())
        {
            case 1:
            {
                return institutionController.institutionLogin(loginDto,response);
            }
            case 2:
            {
                return companyController.companyLogin(loginDto,response);
            }
            case 3:
            {
                return administratorController.administratorLogin(loginDto,response);
            }
            default:
            {
                return Result.fail("用户身份错误");
            }
        }
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
    

}

package com.example.shiro.Realms;

import cn.hutool.core.bean.BeanUtil;
import com.example.shiro.AccountProfile;
import com.example.shiro.JwtToken;
import com.example.sys.entity.Company;
import com.example.sys.service.ICompanyService;
import com.example.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompanyRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ICompanyService companyService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        String companyId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        Company company = companyService.getById(Long.parseLong(companyId));
        if(company == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if(company.getCompanyBlockState() == 1) {
            throw new LockedAccountException("账户已被拉黑！");
        }
        AccountProfile profile = new AccountProfile();
        profile.setId(company.getCompanyId());
        profile.setUsername(company.getCompanyName());
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

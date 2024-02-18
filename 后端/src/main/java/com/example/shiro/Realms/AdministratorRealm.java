package com.example.shiro.Realms;

import com.example.shiro.AccountProfile;
import com.example.shiro.JwtToken;
import com.example.sys.entity.Administrator;
import com.example.sys.service.IAdministratorService;
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
public class AdministratorRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    IAdministratorService administratorService;
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
        String administratorId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        Administrator administrator = administratorService.getById(Long.parseLong(administratorId));
        if(administrator == null) {
            throw new UnknownAccountException("账户不存在！");
        }
//        if(administrator.getAdministratorBlockState() == 1) {
//            throw new LockedAccountException("账户已被拉黑！");
//        }
        AccountProfile profile = new AccountProfile();
        profile.setId(administrator.getAdministratorId());
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

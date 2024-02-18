package com.example.shiro.Realms;

import com.example.shiro.AccountProfile;
import com.example.shiro.JwtToken;
import com.example.sys.entity.Institution;
import com.example.sys.service.IInstitutionService;
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
public class InstitutionRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    IInstitutionService institutionService;
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
        String institutionId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        Institution institution = institutionService.getById(Long.parseLong(institutionId));
        if(institution == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if(institution.getInstitutionBlockState() == 1) {
            throw new LockedAccountException("账户已被拉黑！");
        }
        AccountProfile profile = new AccountProfile();
        profile.setId(institution.getInstitutionId());
        profile.setUsername(institution.getInstitutionName());
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

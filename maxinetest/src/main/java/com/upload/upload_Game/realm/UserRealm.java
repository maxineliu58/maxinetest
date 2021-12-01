package com.upload.upload_Game.realm;

import com.upload.upload_Game.mapper.Usermapper;
import com.upload.upload_Game.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 *
 * A user role class that validates the Shiro framework
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private Usermapper usermapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        //use the Optional to solve the problem of nullException usermapper. GetUserByUsername
        User user = Optional.ofNullable(usermapper.getUserByUsername(principal)).orElseThrow(UnknownAccountException::new);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", user);
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        //Permission information object Info, which is used to store all roles and permissions of the identified user.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // A collection of roles for the user
        Set<String> roles = new HashSet<>();
        String[] roleList={"admin","normal"};
        roles.add(roleList[Integer.parseInt(user.getRole())-1]);
        info.setRoles(roles);
        return info;
    }

}

package org.springboot.service;

import com.google.common.collect.Sets;
import org.springboot.dao.UserRepository;
import org.springboot.jdo.OperatorDetails;
import org.springboot.jdo.Role;
import org.springboot.jdo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by goldendba@gmail.com on 2017/2/4.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户 " + username + " 不存在.");
        }
        Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        OperatorDetails userDetails = new OperatorDetails(user.getLoginName(), user.getShaPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
        //加入登录时间信息和用户角色
        userDetails.setLoginTime(new Date());
        userDetails.setRoleList(user.getRoleList());

        return userDetails;
    }

    /**
     * 获得用户所有角色的权限.
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<GrantedAuthority> authSet = Sets.newHashSet();
        authSet.addAll(user.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));

        return authSet;
    }
}

package com.ssl.manager.security.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssl.manager.modules.usersphere.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = -1L;
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String[] roleIds;
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;

    public LoginUser(User user) {
        username = user.getUsername();
        id = user.getId();
        password = user.getPassword();
        this.roleIds = StrUtil.split(user.getRoles(),",");
    }

    public LoginUser(Integer id, String username,String roles) {
        this.id =id;
        this.username = username;
        this.roleIds =  StrUtil.split(roles,",");
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities == null){
            authorities = new ArrayList<>();
            for (String roleId : roleIds) {
                authorities.add(new SimpleGrantedAuthority(roleId));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> authoritiesToList(){
        List<String> list = new ArrayList<>( this.authorities.size());
        for (GrantedAuthority authority : this.authorities) {
            list.add(authority.getAuthority());
        }
        return list;
    }
}

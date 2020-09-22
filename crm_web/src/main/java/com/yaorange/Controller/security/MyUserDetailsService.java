package com.yaorange.Controller.security;

import com.yaorange.entity.Emp;
import com.yaorange.entity.Module;
import com.yaorange.entity.Role;
import com.yaorange.service.EmpService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Resource(name = "empService")
    private EmpService empService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名获取用户对象
        Emp emp = empService.getEmpByUsername(username);
        //读取当前用户拥有的所有权限
        List<Role> roleSet = emp.getRoleList();
        //准备用于存储当前用户可操作的所有模块集合
        Set<Module> modules = new HashSet<Module>();
        for (Role role : roleSet) {
            List<Module> moduleList = role.getModuleList();
            modules.addAll(moduleList);
        }

        //创建权限集合
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(roleSet.size());
        // 遍历集合
        System.out.println("当前登录用户权限模块URL集合：" + modules);
        for (Module m : modules) {
            authorities.add(new SimpleGrantedAuthority(m.getUrl()));
        }

        return new User(username, emp.getPassword(), authorities);
    }
}

package com.michael.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc
 *
 * @author wangce 2021-05-20
 * @since 1.0.0
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login‐success")
    public String loginSuccess() {
        //提示具体用户名
        String username = getUsername();

        return username + " ,登录成功";
    }

    /*** 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAuthority('p1')") //拥有p1权限才可以访问
    public String r1() {
        return " 访问资源1";
    }

    /**
     * 测试资源2
     *
     * @return
     */
    @GetMapping(value = "/r/r2")
    @PreAuthorize("hasAuthority('p2')") //拥有p2权限才可以访问
    public String r2() {
        return " 访问资源2";
    }

    /*** 获取当前登录用户名 * @return */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}

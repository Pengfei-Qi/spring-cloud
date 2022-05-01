// package com.fei.springcloud.controller;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.util.Collection;
//
// /**
//  * @description: BasicAuthController
//  * @author: qpf
//  * @date: 2022/4/26
//  * @version: 1.0
//  */
// @RestController
// @Slf4j
// public class BasicAuthController {
//     //获取登录用户
//     @RequestMapping("/loginName")
//     public String getLoginName(){
//         String username = "";	//用户名
//         String password = "";	//密码
//         String role = "";		//角色
//         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         if(principal instanceof UserDetails){
//             UserDetails userDetails = (UserDetails)principal;
//             Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//             for(GrantedAuthority grantedAuthority : authorities){
//                 username = userDetails.getUsername();
//                 password = userDetails.getPassword();
//                 role = grantedAuthority.getAuthority();
//                 log.info("用户名：{}\t密码：{}\t角色：{}", username, password, role);
//             }
//         }
//         return "<h1>当前用户</h1><br/>用户名："+ username +"\t密码："+ password +"\t角色：" + role;
//     }
// }

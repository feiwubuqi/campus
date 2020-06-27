package com.campus.domain.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campus.domain.TokenUtils;
import com.campus.domain.model.AccountInfo;
import com.campus.domain.service.AccountInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Login {
    @Autowired
    private AccountInfoService accountInfoService;
    @RequestMapping("/login")
    public AccountInfo login(@RequestBody AccountInfo accountInfo, HttpServletResponse response) {

        String accountSno = accountInfo.getAccountSno();
        String pwd = accountInfo.getAccountPwd();
        QueryWrapper<AccountInfo> wrapper = new QueryWrapper();
        wrapper.eq("ACCOUNT_SNO", accountSno);
        AccountInfo info = accountInfoService.getOne(wrapper);
        if (!StringUtils.isBlank(pwd)&&info!=null){
            if (info.getAccountPwd().equals(pwd)){
                System.out.println(info);
                String token=TokenUtils.sign(info.getAccountName(),info.getAccountSno());
                //System.out.println(token);
                response.setHeader("AuthenticationToken",token);
                info.setAccountPwd("");
                return info;
            }
        }
        return null;
    }

         @RequestMapping("/register")
        public String register(@RequestBody @Validated AccountInfo accountInfo){
            System.out.println(accountInfo);

            boolean status=accountInfoService.save(accountInfo);
        if (status){
            return "0000";
        }else {
            return "1111";
        }
    }

    @RequestMapping("/account")
    public AccountInfo getAccount(@RequestBody AccountInfo accountInfo){
        try{String accountSno = accountInfo.getAccountSno();
//        String pwd = accountInfo.getAccountPwd();
        QueryWrapper<AccountInfo> wrapper = new QueryWrapper();
        wrapper.eq("ACCOUNT_SNO", accountSno);
//        .eq("ACCOUNT_PWD",pwd);
        AccountInfo info = accountInfoService.getOne(wrapper);
        return info;
        }catch (Exception e){
            return null;
        }

    }
}

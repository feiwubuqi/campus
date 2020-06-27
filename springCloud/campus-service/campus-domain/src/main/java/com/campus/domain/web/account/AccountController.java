package com.campus.domain.web.account;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.campus.domain.dto.AdmainDto;
import com.campus.domain.model.AccountInfo;
import com.campus.domain.model.AdmainInfo;
import com.campus.domain.service.AccountInfoService;
import com.campus.domain.service.AdmainInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountInfoService accountInfoService;
    @Resource
    private AdmainInfoService admainInfoService;
@RequestMapping("/edit")
    public AccountInfo edit(@RequestBody AccountInfo info){
        String id=info.getAccountSno();
        String name=info.getAccountName();
        String acady=info.getAcademy();
        String mobile=info.getMobile();
        String nativeAdress=info.getNativePlace();
        LambdaUpdateWrapper<AccountInfo> wrapper=new LambdaUpdateWrapper<>();
            wrapper.eq(AccountInfo::getAccountSno,id)
                    .set(AccountInfo::getAcademy,acady)
                    .set(AccountInfo::getAccountName,name)
                    .set(AccountInfo::getMobile,mobile)
                    .set(AccountInfo::getNativePlace,nativeAdress);
        boolean status=accountInfoService.update(wrapper);
    if (status){
        LambdaQueryWrapper<AccountInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountInfo::getAccountSno,id);
        AccountInfo accountInfo=accountInfoService.getOne(queryWrapper);
        accountInfo.setAccountPwd("");
        return accountInfo;
    }
        return null;
    }
        @RequestMapping("/level")
    public AccountInfo updatelevel(@RequestBody AdmainDto info){
            String id=info.getAccountSno();
            String levelName=info.getLevel();
            String author=info.getAuthor();
            String password=info.getPassword();
            LambdaQueryWrapper<AdmainInfo> aWrapper=new LambdaQueryWrapper<>();
            aWrapper.eq(AdmainInfo::getAuthor,author).eq(AdmainInfo::getPassword,password);
             AdmainInfo admainInfo=admainInfoService.getOne(aWrapper);
            if (!StringUtils.isBlank(author)&&!StringUtils.isBlank(password)&&admainInfo!=null){
                if (author.equals(admainInfo.getAuthor())&&password.equals(admainInfo.getPassword())){

                    String level=null;
                    if ("普通用户".equals(levelName)){
                        level="0";
                    }else if("管理员".equals(levelName)){
                        level="1";
                    }else{
                        LambdaQueryWrapper<AccountInfo> queryWrapper=new LambdaQueryWrapper<>();
                        queryWrapper.eq(AccountInfo::getAccountSno,id);
                        AccountInfo accountInfo=accountInfoService.getOne(queryWrapper);
                        accountInfo.setAccountPwd("");
                        return accountInfo;
                    }
                    LambdaUpdateWrapper<AccountInfo> wrapper=new LambdaUpdateWrapper<>();
                    wrapper.set(AccountInfo::getLevel,level).eq(AccountInfo::getAccountSno,id);
                    boolean status=accountInfoService.update(wrapper);
                    if (status){
                        LambdaQueryWrapper<AccountInfo> queryWrapper=new LambdaQueryWrapper<>();
                        queryWrapper.eq(AccountInfo::getAccountSno,id);
                        AccountInfo accountInfo=accountInfoService.getOne(queryWrapper);
                        accountInfo.setAccountPwd("");
                        return accountInfo;}
                }else {  LambdaQueryWrapper<AccountInfo> queryWrapper=new LambdaQueryWrapper<>();
                    queryWrapper.eq(AccountInfo::getAccountSno,id);
                    AccountInfo accountInfo=accountInfoService.getOne(queryWrapper);
                    accountInfo.setAccountPwd("");
                    return accountInfo;
                }
            }
            LambdaQueryWrapper<AccountInfo> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(AccountInfo::getAccountSno,id);
            AccountInfo accountInfo=accountInfoService.getOne(queryWrapper);
            accountInfo.setAccountPwd("");
            return accountInfo;
        }
}

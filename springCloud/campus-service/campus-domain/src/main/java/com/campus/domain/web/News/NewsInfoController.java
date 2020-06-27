package com.campus.domain.web.News;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.domain.dto.NewsInfoDto;
import com.campus.domain.dto.NewsInfoOutDto;
import com.campus.domain.model.NewsInfo;
import com.campus.domain.repository.INewsInfoRepository;
import com.campus.domain.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("news")

public class NewsInfoController {
    @Autowired
    private NewsInfoService newsInfoService;
    @RequestMapping("/info")
    public NewsInfoOutDto queryNewSInfo(@RequestBody NewsInfoDto dto){
        List<NewsInfo> list=new ArrayList<>();
       // list.add(newsInfo);
        long page=dto.getPage();
        long pageSize=dto.getPageSize();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("SEQUENCE_ID");
        Page<NewsInfo> page1=new Page<>(page,pageSize);
        IPage<NewsInfo> iPage=newsInfoService.page(page1,wrapper);
//        iPage.getTotal()
        list=iPage.getRecords();
    for (NewsInfo newsInfo:list ){
    if (newsInfo.getContent().length()>=80)
    {
        String content=newsInfo.getContent().substring(0,80)+"...";
        //content.replaceAll("/t/n", "");
        newsInfo.setContent(content);
}
}
    NewsInfoOutDto outDto=new NewsInfoOutDto();
    outDto.setPage(iPage.getCurrent());
    outDto.setPageSize(iPage.getSize());
    if (iPage.getTotal()%iPage.getSize()==0){
    outDto.setTotalNum(iPage.getTotal()/iPage.getSize());
    }else{
    outDto.setTotalNum(iPage.getTotal()/iPage.getSize()+1);
    }
    outDto.setNewsInfoList(list);
    //System.out.println(iPage.getTotal());
    //System.out.println(iPage.getSize());
    //System.out.println(outDto.getTotalNum());
        return outDto;
    }
    @RequestMapping("/incr")
    public String insertNewsInfo(@RequestBody NewsInfo newsInfo){
        newsInfo.setTime(newsInfo.getTime().substring(0,10));
        StringBuffer buffer=new StringBuffer("../../static/");
        String img=newsInfo.getImgAddress();
        newsInfo.setImgAddress(buffer.append(img).toString());
        boolean status=newsInfoService.save(newsInfo);
        if (status){
            return "添加成功";
        }
        return "添加失败";
    }
    @RequestMapping("/delete")
    public String removeNewInfo(@RequestBody NewsInfo newsInfo){
        System.out.println(newsInfo);
        QueryWrapper<NewsInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.
                eq("SEQUENCE_ID",newsInfo.getSequenceId());
        boolean status=newsInfoService.remove(queryWrapper);
if (status){
    return "操作成功";
}
return "操作失败";
    }
    @RequestMapping("/update")
    public String modifyNewsInfo(@RequestBody NewsInfo newsInfo){
        System.out.println(newsInfo);
        StringBuffer buffer=new StringBuffer("../../static/");
        String img=newsInfo.getImgAddress();
        newsInfo.setImgAddress(buffer.append(img).toString());
        UpdateWrapper<NewsInfo> wrapper=new UpdateWrapper();
        wrapper.eq("SEQUENCE_ID",newsInfo.getSequenceId());
        boolean status=newsInfoService.update(newsInfo,wrapper);
        if (status){
            return "操作成功";
        }
        return "操作失败";
    }

    @RequestMapping("/detail")
    @Transactional
    public NewsInfo queryNewsDetail(@RequestBody NewsInfo newsInfo){
        System.out.println(newsInfo);

        QueryWrapper<NewsInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("SEQUENCE_ID",newsInfo.getSequenceId());
        NewsInfo newsInfo1=newsInfoService.getOne(queryWrapper);
        int num=Integer.valueOf(newsInfo1.getCountNum())+1;
        newsInfo1.setCountNum(num);
        boolean status=newsInfoService.update(newsInfo1,queryWrapper);

        return newsInfo1;
    }
}

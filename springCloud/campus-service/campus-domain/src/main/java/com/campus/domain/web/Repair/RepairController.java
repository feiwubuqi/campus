package com.campus.domain.web.Repair;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.domain.dto.RepairInfoDto;
import com.campus.domain.dto.RepairInfoOutDto;
import com.campus.domain.model.DormitoryFlow;
import com.campus.domain.model.RepairInfo;
import com.campus.domain.service.RepairInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("repair")
public class RepairController {
@Autowired
private RepairInfoService repairInfoService;
    @RequestMapping("/info")
    public RepairInfoOutDto queryRepairInfo(@RequestBody RepairInfoDto dto){
        List<RepairInfo> list=new ArrayList<>();
        long currentPage=dto.getPage();
        long pageSize=dto.getPageSize();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("REPORT_TIME");
        Page<RepairInfo> page=new Page<>(currentPage,pageSize);
        IPage<RepairInfo> iPage=repairInfoService.page(page,wrapper);
        list=iPage.getRecords();

        RepairInfoOutDto outDto=new RepairInfoOutDto();
        outDto.setPage(iPage.getCurrent());
        outDto.setPageSize(iPage.getSize());
        if (iPage.getTotal()%iPage.getSize()==0){
            outDto.setTotalNum(iPage.getTotal()/iPage.getSize());
        }else{
            outDto.setTotalNum(iPage.getTotal()/iPage.getSize()+1);
        }
        System.out.println(list.size());
        outDto.setRepairInfoList(list);

        return outDto;
    }
    @RequestMapping("/incr")
    public String insertRepairInfo(@RequestBody RepairInfo repairInfo){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        System.out.println(repairInfo);
        repairInfo.setRepairTime(repairInfo.getRepairTime().substring(0,10));
        repairInfo.setReportTime(format.format(new Date()));
        StringBuffer buffer=new StringBuffer("../../static/");
        String img=repairInfo.getImgAddress();
        repairInfo.setImgAddress(buffer.append(img).toString());
        repairInfo.setProcessStatus("00");
        boolean status=repairInfoService.save(repairInfo);
        if (status){
            return "添加成功";
        }
        return "添加失败";
    }
    @RequestMapping("/delete")
    public String removeRepairInfo(@RequestBody RepairInfo repairInfo){
        System.out.println(repairInfo);
        QueryWrapper<RepairInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.
                eq("TASK_ID",repairInfo.getTaskId());
        boolean status=repairInfoService.remove(queryWrapper);
        if (status){
            return "操作成功";
        }
        return "操作失败";
    }
    @RequestMapping("/update")
    public String modifyRepairInfo(@RequestBody RepairInfo repairInfo){
        UpdateWrapper<RepairInfo> wrapper=new UpdateWrapper();
        wrapper.eq("TASK_ID",repairInfo.getTaskId());

        boolean status=repairInfoService.update(repairInfo,wrapper);
        if (status){
            return "操作成功";
        }
        return "操作失败";


    }

}

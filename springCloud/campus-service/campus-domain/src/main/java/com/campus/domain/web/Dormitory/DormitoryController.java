package com.campus.domain.web.Dormitory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.domain.dto.DormitoryDto;
import com.campus.domain.dto.DormitoryOutDto;
import com.campus.domain.dto.RepairInfoOutDto;
import com.campus.domain.model.DormitoryFlow;
import com.campus.domain.model.NewsInfo;
import com.campus.domain.model.RepairInfo;
import com.campus.domain.service.DormitoryFlowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dormitory")
public class DormitoryController {


    @Autowired
    private DormitoryFlowService dormitoryFlowService;
    @RequestMapping("/info")
    public DormitoryOutDto queryNewSInfo(@RequestBody DormitoryDto dto){
        List<DormitoryFlow> list=new ArrayList<>();
        long currentPage=dto.getPage();
        long pageSize=dto.getPageSize();
        QueryWrapper wrapper=new QueryWrapper();

        if (dto.getDormitoryFlow()!=null){
            System.out.println(dto.getDormitoryFlow());
            String status=dto.getDormitoryFlow().getStatus();
            String housNo=dto.getDormitoryFlow().getHouseNo();
        if (!StringUtils.isBlank(status))
        if ("00".equals(status.trim())||"01".equals(status.trim())){
            System.out.println("xxxxxxxx");
            wrapper.eq("STATUS",dto.getDormitoryFlow().getStatus());
        }
        if (!StringUtils.isBlank(housNo)){

            wrapper.like("HOUSE_NO",housNo);
            System.out.println(housNo);
        }
        }
//        wrapper.orderByDesc("REPORT_TIME");
        Page<DormitoryFlow> page=new Page<>(currentPage,pageSize);
        IPage<DormitoryFlow> iPage=dormitoryFlowService.page(page,wrapper);
        list=iPage.getRecords();
        for (DormitoryFlow flow:list) {
            if ("00".equals(flow.getStatus())){
                flow.setStatus("未使用");
            }else if ("01".equals(flow.getStatus())){
                flow.setStatus("已使用");
            }
        }
        DormitoryOutDto outDto=new DormitoryOutDto();
        outDto.setPage(iPage.getCurrent());
        outDto.setPageSize(iPage.getSize());
        if (iPage.getSize()>iPage.getTotal()){
            outDto.setTotalNum(1);
            outDto.setDormitoryFlowList(list);
            return outDto;
        }
        if (iPage.getTotal()%iPage.getSize()==0){
            outDto.setTotalNum(iPage.getTotal()/iPage.getSize());
        }else{
            outDto.setTotalNum(iPage.getTotal()/iPage.getSize()+1);
        }
        System.out.println(list.size());
        outDto.setDormitoryFlowList(list);

        return outDto;
    }
    @RequestMapping("/incr")
    public String insertDormitoryFlow(@RequestBody DormitoryFlow dormitoryFlow){
        boolean status=dormitoryFlowService.save(dormitoryFlow);
        if (status){
            return "添加成功";
        }
        return "添加失败";
    }
    @RequestMapping("/delete")
    public String removeDormitoryFlow(@RequestBody DormitoryFlow dormitoryFlow){
        QueryWrapper<DormitoryFlow> queryWrapper=new QueryWrapper<>();
        queryWrapper.
                eq("id",dormitoryFlow.getId());
        boolean status=dormitoryFlowService.remove(queryWrapper);
        if (status){
            return "操作成功";
        }
        return "操作失败";
    }
    @RequestMapping("/update")
    public String modifyDormitoryFlow(@RequestBody DormitoryFlow dormitoryFlow){
        UpdateWrapper<DormitoryFlow> wrapper=new UpdateWrapper();
        wrapper.eq("id",dormitoryFlow.getId());

        boolean status=dormitoryFlowService.update(dormitoryFlow,wrapper);
        if (status){
            return "操作成功";
        }
        return "操作失败";


    }
}

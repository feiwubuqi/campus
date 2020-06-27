import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.DomainApplication;
import com.campus.domain.dto.NewsInfoDto;
import com.campus.domain.model.AccountInfo;
import com.campus.domain.model.DormitoryFlow;
import com.campus.domain.model.NewsInfo;
import com.campus.domain.model.RepairInfo;
import com.campus.domain.repository.IAccountInfoRepository;
import com.campus.domain.service.AccountInfoService;
import com.campus.domain.service.DormitoryFlowService;
import com.campus.domain.service.NewsInfoService;
import com.campus.domain.service.RepairInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DomainApplication.class)
public class TestImpl {
    @Resource
    private AccountInfoService accountInfoService;
    @Resource
    private IAccountInfoRepository infoRepository;
    @Resource
    private NewsInfoService newsInfoService;
    @Resource
    private RepairInfoService repairInfoService;
    @Resource
    private DormitoryFlowService dormitoryFlowService;

    @Test
    public void insertRepair(){
        RepairInfo repairInfo=new RepairInfo();
        repairInfo.setAccountSno("163010103");
        repairInfo.setHouseNo("7-508");
        repairInfo.setImgAddress("../../static/1.jpg");
        repairInfo.setMobile("18628561950");
        repairInfo.setProblemDesc("金木水火土");
        repairInfo.setProcessStatus("00");
        repairInfo.setRemark("fva门打开vnl");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(new Date());
        System.out.println(time);
        repairInfo.setReportTime(time);
        repairInfo.setRepairTime(format.format(new Date()));
        for (int i=0;i<20;i++){
            repairInfoService.save(repairInfo);
        }
    }

    @Test
    public void getMid(){
        int[] num={1,3,4,2,2};
        int left=1;
        int right=num.length-1;
        int result=0;
        while (left<=right){
            int middle=(left+right)/2;
            System.out.println("middle:"+middle);
            int count=0;
            for (int i=0;i<=num.length-1;i++){
                if (num[i]<=middle){
                    count++;
                }
            }
            if (count<=middle){
                left=middle+1;
                System.out.println("left:"+left);
            }if (count>middle){
                right=middle;
                System.out.println("right:"+right);
            }
            if (left==right){
                result=left;
                System.out.println(result);
            }
        }

    }


    @Test
    public void getAccount(){
       AccountInfo accountInfo= accountInfoService.getById("10010");
       System.out.println(accountInfo.toString());
       AccountInfo accountInfo1= infoRepository.selectById(10010);
       System.out.println(accountInfo1);
    }
    @Test
    public void getNewsInfo(){
        QueryWrapper<NewsInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("SEQUENCE_ID",1);
        NewsInfo newsInfo1=newsInfoService.getOne(queryWrapper);

        System.out.println(newsInfo1);
        NewsInfo newsInfo=newsInfoService.getById(1);
        System.out.println(newsInfo);
    }
    @Test
    public void incrNewsInfo(){
        NewsInfo newsInfo=new NewsInfo();
        newsInfo.setContent("你从今年年初你去哦你从前");
        newsInfo.setTime("2020-05-21");
        newsInfo.setAuthor("郑梦辉");
//        newsInfo.setSequenceId("5");
       boolean status= newsInfoService.save(newsInfo);
       System.out.println(status);
    }
    @Test
    public void getNewsInfoDto(){

        NewsInfoDto dto=new NewsInfoDto();
        dto.setPage(1);
        System.out.println(dto.getPage()+"..."+dto.getPageSize());
    }
    @Test
    public void getPagination(){
        Page<NewsInfo> page=new Page<>(3,3);
        IPage<NewsInfo> iPage=newsInfoService.page(page);
        List<NewsInfo> list=iPage.getRecords();
        System.out.println(iPage.getTotal());
        System.out.println(list.size());
        System.out.println(list);
    }
    @Test
    public void getComputed(){
        System.out.println(5/3);
        System.out.println(6%2);
    }
    @Test
    public void insertDormitory(){
        DormitoryFlow flow=new DormitoryFlow();
        flow.setAccountSno("1630103");
        flow.setBedNo("02");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        flow.setCreateTime(format.format(new Date()));
        flow.setHouseNo("7公寓508室");
        flow.setIntoTime("2016-09-01");
        flow.setOutTime("2020-06-30");
        flow.setStatus("01");
        System.out.println(flow);
        int count=0;
        for (int i=0;i<30;i++){

        dormitoryFlowService.save(flow);
        count++;
        System.out.println(count);
     }

    }




}

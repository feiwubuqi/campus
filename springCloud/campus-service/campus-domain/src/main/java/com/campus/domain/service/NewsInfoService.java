package com.campus.domain.service;

import com.campus.domain.model.NewsInfo;
import com.campus.domain.repository.INewsInfoRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengmh@belink.com
 * @since 2020-04-20
 */
@Service
public class NewsInfoService extends ServiceImpl<INewsInfoRepository, NewsInfo> {

}

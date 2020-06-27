package com.campus.domain.dto;

import com.campus.domain.base.CampusOutDto;
import com.campus.domain.model.NewsInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NewsInfoOutDto  extends CampusOutDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<NewsInfo> newsInfoList;

}

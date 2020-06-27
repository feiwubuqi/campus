package com.campus.domain.dto;

import com.campus.domain.base.CampusDto;
import com.campus.domain.model.NewsInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NewsInfoDto extends CampusDto implements Serializable {

    private static final long serialVersionUID = 1L;

   private  NewsInfo newsInfo;

}

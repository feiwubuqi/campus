package com.campus.domain.dto;

import com.campus.domain.base.CampusDto;
import com.campus.domain.model.DormitoryFlow;
import lombok.Data;

import java.io.Serializable;

@Data
public class DormitoryDto extends CampusDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private DormitoryFlow dormitoryFlow;
}

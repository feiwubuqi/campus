package com.campus.domain.dto;

import com.campus.domain.base.CampusOutDto;
import com.campus.domain.model.RepairInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class RepairInfoOutDto extends CampusOutDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<RepairInfo> repairInfoList;
}

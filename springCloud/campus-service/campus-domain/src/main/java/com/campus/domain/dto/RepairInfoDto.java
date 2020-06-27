package com.campus.domain.dto;

import com.campus.domain.base.CampusDto;
import com.campus.domain.model.RepairInfo;
import lombok.Data;

import java.io.Serializable;
@Data
public class RepairInfoDto extends CampusDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private RepairInfo repairInfo;

}

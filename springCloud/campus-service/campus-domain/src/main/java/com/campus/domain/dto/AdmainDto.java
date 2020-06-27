package com.campus.domain.dto;

import com.campus.domain.model.AccountInfo;
import lombok.Data;

@Data
public class AdmainDto extends AccountInfo {

    private String author;
    private String password;
}

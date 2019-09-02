package com.plag.project.plagdemo.dto;

import com.plag.project.plagdemo.Entity.FileInfo;
import lombok.Data;

@Data
public class ResultDto {
    private Long id;
    private Long stdId;
    private String studentName;
    private String filename;

    public ResultDto(FileInfo fileInfo) {
        this.id = fileInfo.getId();
        this.stdId = fileInfo.getStd_id();
        this.studentName = fileInfo.getStudent_Name();
        this.filename = fileInfo.getFilename();
    }
}

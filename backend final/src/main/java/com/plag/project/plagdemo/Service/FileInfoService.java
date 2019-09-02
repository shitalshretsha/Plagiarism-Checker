package com.plag.project.plagdemo.Service;

import com.plag.project.plagdemo.Entity.FileInfo;
import com.plag.project.plagdemo.dto.ResponseDto;
import com.plag.project.plagdemo.dto.ResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileInfoService {
    void delete(Long id);
    FileInfo getOne(Long id);
    List<ResultDto> getAll();
    String uploadFileOnly(MultipartFile file, String name, Long stdId);
    ResponseDto getLCS(Long id);
}

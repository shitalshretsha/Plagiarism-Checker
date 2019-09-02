package com.plag.project.plagdemo.Controller;

import com.plag.project.plagdemo.Entity.FileInfo;
import com.plag.project.plagdemo.Service.FileInfoService;

import com.plag.project.plagdemo.dto.ResponseDto;
import com.plag.project.plagdemo.dto.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin()
@RestController
public class FileServiceController {
    private FileInfoService fileInfoService;

    public FileServiceController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<?> insert(MultipartFile file, String name, Long stdId) throws IOException {
        try {
            String response = fileInfoService.uploadFileOnly(file,name,stdId);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        fileInfoService.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getLCS(@PathVariable Long id) {
        return new ResponseEntity<ResponseDto>(fileInfoService.getLCS(id), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<FileInfo> getOne(@PathVariable Long id) {
        FileInfo f1 = fileInfoService.getOne(id);
        return new ResponseEntity<FileInfo>(f1, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResultDto>> getAll() {
        List<ResultDto> fileInfoList = fileInfoService.getAll();
        System.out.println(fileInfoList);
        return new ResponseEntity<List<ResultDto>>(fileInfoList, HttpStatus.OK);
    }


}

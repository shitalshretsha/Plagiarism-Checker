package com.plag.project.plagdemo.Service.ServiceImpl;

import com.plag.project.plagdemo.Entity.FileInfo;
import com.plag.project.plagdemo.Repository.FileInfoRepository;
import com.plag.project.plagdemo.Service.FileInfoService;
import com.plag.project.plagdemo.dto.ResponseDto;
import com.plag.project.plagdemo.dto.ResultDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class FileInfoServiceImpl implements FileInfoService {
    private FileInfoRepository fileInfoRepository;

    public FileInfoServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    public ResponseDto lcs(String user_uploaded, String db_file) {
        ArrayList<Object> lcs_set = new ArrayList<>();

        char[] s = user_uploaded.toCharArray();
        char[] t = db_file.toCharArray();

        int m = user_uploaded.length();
        int n = db_file.length();
        int i, j, v = 0, k, longest = 0;
        int l[][] = new int[m + 1][n + 1];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (s[i] == t[j]) {
                    v = l[i][j] + 1;
                    l[i + 1][j + 1] = v;
                    if (v > longest) {
                        longest = v;
                    }
                    if (v == longest) {
                        lcs_set.clear();
                        for (k = i - v + 1; k < i + 1; k++) {
                            lcs_set.add(s[k]);
                        }
                    }

                }
            }
        }
        System.out.println("THE LONGEST COMMON SUBSTRING IS:\n " + lcs_set.size() + " " + lcs_set);
        System.out.println();
        String seperator = StringUtils.collectionToDelimitedString(lcs_set, "");
        System.out.println("THE COMMON WORDS ARE:  " + seperator);

       // double max = 0;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setFileContent(lcs_set);
        responseDto.setPercentage(percentage(lcs_set, m));
        responseDto.setPlag(seperator);
        return responseDto;

    }

    private ResponseDto algorithm(String content, Long id) {
        System.out.println("ASSIGNMENT TO BE CHECKED: \n" + content);
        String content1 = content.toLowerCase();
        String user_uploaded = content1;
        double max = 0.0;
        List<ResponseDto> count_list = new ArrayList<>();
        List<FileInfo> fileInfoList = fileInfoRepository.getAllFileInfoExceptThisId(id);
        ResponseDto final_per = new ResponseDto();
        for (FileInfo fileInfo : fileInfoList) {
            String databaseFile = null;
            try {
                databaseFile = fileInfo.getFileContent().getSubString(1, (int) (fileInfo.getFileContent().length() - 1));
                String db_file = databaseFile.toLowerCase();
                System.out.println("Assignment content from database list: \n" + db_file);
                System.out.println("\n");
                ResponseDto responseDto = lcs(user_uploaded, db_file);
                if (responseDto.getPercentage() >= max) {
                    max = responseDto.getPercentage();
                    final_per = responseDto;
                }
                System.out.println("The Highest plagiarized percentage is : " + max + " %");
                System.out.println("*-------------------------------------*-------------------------------------*-------------------------------------*");


                count_list.add(responseDto);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println("ASSIGNMENT CONTENT FROM DATABASE LIST IS:\n  " + fileInfoList);
        return final_per;
    }

    private Double percentage(ArrayList<Object> lcs_set, int m) {
        int a = lcs_set.size();
        System.out.println("Plagiarized Percentage is: " + Double.valueOf(((a * 100) / m)) + " %");
        return Double.valueOf(((a * 100) / m));
    }

    @Override
    public void delete(Long id) {
        fileInfoRepository.removeById(
                id);

    }

    @Override
    public FileInfo getOne(Long id) {
        return fileInfoRepository.findById(id).get();
    }

    @Override
    public List<ResultDto> getAll() {
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        List<ResultDto> resultDtos = new ArrayList<>();

        for (FileInfo fileInfo : fileInfoList) {
            ResultDto resultDto = new ResultDto(fileInfo);
            resultDtos.add(resultDto);
        }

        return resultDtos;
    }

    @Override
    public String uploadFileOnly(MultipartFile file, String name, Long stdId) {
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setStd_id(stdId);
            fileInfo.setStudent_Name(name);

            fileInfo.setFilename(file.getOriginalFilename());

            fileInfo.setFileContent(new javax.sql.rowset.serial.SerialClob(new String(file.getBytes()).toCharArray()));

            fileInfoRepository.save(fileInfo);

            return "saved";
        } catch (Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    @Override
    public ResponseDto getLCS(Long id) {
        FileInfo comparingFile = fileInfoRepository.findById(id).get();
        try {
            return algorithm(comparingFile.getFileContent().getSubString(1, (int) (comparingFile.getFileContent().length() - 1)), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

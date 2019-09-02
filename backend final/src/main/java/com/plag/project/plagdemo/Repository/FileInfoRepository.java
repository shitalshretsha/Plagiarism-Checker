package com.plag.project.plagdemo.Repository;

import com.plag.project.plagdemo.Entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    @Modifying
    @Query(value = "DELETE from file_info where id = ?1", nativeQuery = true)
    void removeById(Long Id);

    @Query(value = "SELECT * FROM file_info WHERE id <> ?1", nativeQuery = true)
    List<FileInfo> getAllFileInfoExceptThisId(Long id);


}
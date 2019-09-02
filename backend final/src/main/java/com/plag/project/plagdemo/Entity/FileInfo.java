package com.plag.project.plagdemo.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Clob;

@Data
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long Std_id;
    private String Student_Name;
    private String filename;
    Clob FileContent;
    public FileInfo() {
    }
}


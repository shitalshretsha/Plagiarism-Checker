package com.plag.project.plagdemo.dto;

import java.util.ArrayList;

public class ResponseDto {
    private ArrayList<Object> fileContent;
    private Double Percentage;
    private String plag;

    public ResponseDto() {
    }

    public ResponseDto(ArrayList<Object> fileContent, Double percentage, String plag) {
        this.fileContent = fileContent;
        Percentage = percentage;
        this.plag = plag;
    }

    public String getPlag() {
        return plag;
    }

    public void setPlag(String plag) {
        this.plag = plag;
    }

    public ArrayList<Object> getFileContent() {
        return fileContent;
    }

    public void setFileContent(ArrayList<Object> fileContent) {
        this.fileContent = fileContent;
    }

    public Double getPercentage() {
        return Percentage;
    }

    public void setPercentage(Double percentage) {
        Percentage = percentage;
    }
}

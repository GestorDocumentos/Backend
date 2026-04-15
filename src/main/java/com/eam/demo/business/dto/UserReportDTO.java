package com.eam.demo.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDTO {

    private Long idUser;
    private String name;
    private List<SubjectReportDTO> subjects;
}
package com.eam.demo.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String email;
    private List<String> roles;
}

package com.CCMe.Model.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogInRequest {
    @Email
    @NotNull
    private String email;
    private String password;
}

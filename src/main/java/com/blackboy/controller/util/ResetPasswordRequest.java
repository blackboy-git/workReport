package com.blackboy.controller.util;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String userId;
    private String oldPassword;
    private String newPassword;
}

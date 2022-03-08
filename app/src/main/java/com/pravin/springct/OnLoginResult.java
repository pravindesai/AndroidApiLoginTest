package com.pravin.springct;

public interface OnLoginResult {
    void onLoginSucess(UserResponse userResponse);
    void onLoginFailed(String message);
}

package com.ecom.novren.service;

import com.ecom.novren.model.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}

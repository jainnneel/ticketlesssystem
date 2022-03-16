package com.ticketless.service;

import com.ticketless.dto.UserDto;
import com.ticketless.model.Users;

public interface UsersService {

    boolean createUser(UserDto userdto);

    Users getUserByMobile(String mobile);
    
}

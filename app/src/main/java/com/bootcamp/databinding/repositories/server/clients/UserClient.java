package com.bootcamp.databinding.repositories.server.clients;

import com.bootcamp.databinding.repositories.server.services.UserService;
import com.bootcamp.databinding.utils.ClientUtil;

public class UserClient {
    public static UserService service() {
        return ClientUtil.service(UserService.class, "https://reqres.in/api/");
    }
}

package com.bootcamp.databinding.repositories.server.clients;

import com.bootcamp.databinding.repositories.server.services.PostService;
import com.bootcamp.databinding.utils.ClientUtil;

public class PostClient {
    public static PostService service() {
        return ClientUtil.service(PostService.class, "http://jsonplaceholder.typicode.com/");
    }
}

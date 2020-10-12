package com.itengine.instagram.service.impl;

import com.itengine.instagram.model.Authority;
import com.itengine.instagram.repository.AuthorityRepository;
import com.itengine.instagram.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findByName(String name) {
        return this.authorityRepository.findByName(name);
    }
}

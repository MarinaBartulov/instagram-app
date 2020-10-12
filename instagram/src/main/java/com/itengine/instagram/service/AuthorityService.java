package com.itengine.instagram.service;

import com.itengine.instagram.model.Authority;

public interface AuthorityService {

    Authority findByName(String name);
}

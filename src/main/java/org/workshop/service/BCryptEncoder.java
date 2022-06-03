package org.workshop.service;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BCryptEncoder implements IServiceEncoder {

    @Override
    public String encode(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

}
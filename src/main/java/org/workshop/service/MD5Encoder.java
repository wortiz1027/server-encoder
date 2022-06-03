package org.workshop.service;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Encoder implements IServiceEncoder {

    @Override
    public String encode(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();
    }

}
package com.ss.atmlocator.service;

/**
* This class contains with enums of error keys and codes for user credentials verification
*/
public final class ValidateUserCredCode {

    private ValidateUserCredCode(){}

    public static enum ValidationKey {
        EMAIL,
        LOGIN,
        PASSWORD,
        AVATAR,
        NOTHING;
    }

}

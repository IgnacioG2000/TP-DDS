package com.disenio.mimagrupo06.exception;

import java.util.NoSuchElementException;

public class NoSeEncuentraEnLaApi extends NullPointerException {
    public NoSeEncuentraEnLaApi(String msg) {
        super(msg);
    }
}

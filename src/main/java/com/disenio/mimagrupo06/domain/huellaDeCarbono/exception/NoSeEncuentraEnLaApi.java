package com.disenio.mimagrupo06.domain.huellaDeCarbono.exception;

import java.util.NoSuchElementException;

public class NoSeEncuentraEnLaApi extends NullPointerException {
    public NoSeEncuentraEnLaApi(String msg) {
        super(msg);
    }
}

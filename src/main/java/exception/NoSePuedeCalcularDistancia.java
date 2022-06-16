package exception;

import java.io.IOException;

public class NoSePuedeCalcularDistancia extends IOException {
    public NoSePuedeCalcularDistancia(String msg) {
        super(msg);
    }
}

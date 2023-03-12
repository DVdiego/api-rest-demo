package com.inditex.app.model;

import java.util.Arrays;

public enum RateName {

    UNO(1),
    DOS(2),
    TRES(3),
    CUATRO(4);

    final int value;

    RateName(int value) {
        this.value = value;
    }

    static RateName get(int id) {
        return Arrays.stream(RateName.values()).filter(r-> r.value == id).findFirst().orElseThrow();
    }
}

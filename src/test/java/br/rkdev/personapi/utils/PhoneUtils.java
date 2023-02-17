package br.rkdev.personapi.utils;

import br.rkdev.personapi.dto.request.PhoneDTO;
import br.rkdev.personapi.entity.Phone;
import br.rkdev.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "8199899-1234";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}

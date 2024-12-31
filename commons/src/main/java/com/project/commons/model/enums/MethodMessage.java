package com.project.commons.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Created by user on 2:35 25/07/2024, 2024
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MethodMessage {
    MSG_GET(StatusConstant.SUCCESS, "Berhasil menerima data", "Successfully get the data"),
    MSG_POST(StatusConstant.SUCCESS, "Pengumpulan data berhasil", "Successfully submit the data"),
    MSG_PUT(StatusConstant.SUCCESS, "Berhasil memperbarui data", "Successfully update the data"),
    MSG_DELETE(StatusConstant.SUCCESS, "Berhasil menghapus data", "Successfully delete the data"),
    MSG_PERMISSION_DENIED(StatusConstant.ERROR, "Izin ditolak", "Permission denied"),
    MSG_USER_UNAUTHORIZED(StatusConstant.ERROR, "User Unauthorized", "User Unauthorized"),
    MSG_USER_BAD_CREDENTIAL(StatusConstant.ERROR, "Salah username/password", "username/password is wrong"),
    MSG_USER_LOCKED(StatusConstant.ERROR, "User telah terkunci. Harap hubungi Administrator!", "User has been locked! Please call Administrator."),
    MSG_SOMETHING_WRONG(StatusConstant.ERROR, "Telah terjadi kesalahan", "Oops Something wrong!"),
    ;

    StatusConstant status;
    String in;
    String en;
    public static MethodMessage compare(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        for (MethodMessage message : values()) {
            if (message.name().equals(name)) {
                return message;
            }
        }
        return null;
    }

}

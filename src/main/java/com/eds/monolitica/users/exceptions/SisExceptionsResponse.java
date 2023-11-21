package com.eds.monolitica.users.exceptions;

import java.util.Date;

public class SisExceptionsResponse {
    private Date datestamp;
    private String message;

    public SisExceptionsResponse(Date datestamp, String message) {
        this.datestamp = datestamp;
        this.message = message;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public String getMessage() {
        return message;
    }
}

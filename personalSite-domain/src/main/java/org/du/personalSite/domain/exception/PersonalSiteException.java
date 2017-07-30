package org.du.personalSite.domain.exception;

/**
 * Created by 燃烧杯 on 2017/7/6.
 */
public class PersonalSiteException extends Exception {
    public PersonalSiteException() {
        super();
    }

    public PersonalSiteException(String message) {
        super(message);
    }

    public PersonalSiteException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalSiteException(Throwable cause) {
        super(cause);
    }

    protected PersonalSiteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

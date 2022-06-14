package com.am.appreview.enums;

public enum ApplicantStatus {

    New("New"),
    InProgress("InProgress"),
    Selected("Selected"),
    Rejected("Rejected");

    private final String value;

    ApplicantStatus(final String value) {
        this.value = value;
    }
}

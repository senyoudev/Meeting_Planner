package com.younesmeskafe.MeetingPlanner.domain;

public enum MeetingType {
    VC("Visioconference"),
    SPEC("Share and Case Study"),
    RS("Simple Meeting"),
    RC("Remote and On-site Coupled");

    private final String displayName;

    MeetingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
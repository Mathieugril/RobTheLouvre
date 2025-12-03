package com.robthelouvre.terminal;


public enum RoomType {
    OUTSIDE (Text.Descriptions.OUTSIDE, Text.Details.OUT_DET, 695, 15),
    LOBBY (Text.Descriptions.LOBBY, Text.Details.LOBBY_DET, 190, 200),
    VIP (Text.Descriptions.VIP, Text.Details.VIP_DET, 190, 370),
    MASTERS (Text.Descriptions.MASTERS, Text.Details.MASTERS_DET, 420, 220),
    REGALIA (Text.Descriptions.REGALIA, Text.Details.REGALIA_DET, 420, 420),
    CONTROL (Text.Descriptions.SECURITY, Text.Details.SECURITY_DET, 605, 335),
    BREAK(Text.Descriptions.GUARDS, Text.Details.GUARD_DET, 695, 335),
    JANITOR_CLOSET (Text.Descriptions.JANITOR, Text.Details.JANITOR_DET, 695, 275),
    DELIVERY_DOCK (Text.Descriptions.DELIVERY, Text.Details.DELIVERY_DET, 695, 100),
    BASEMENT (Text.Descriptions.BASEMENT, Text.Details.BASEMENT_DET, 695, 30),
    GARDEN (Text.Descriptions.GARDEN, Text.Details.GARDEN_DET, 420, 665),
    BALCONY (Text.Descriptions.BALCONY1, Text.Details.BALCONY_DET1, 420, 585),
    SECRET_PASSAGE (Text.Descriptions.PASSAGE, Text.Details.PASSAGE_DET1, 810, 500),
    SERVICE_TUNNEL (Text.Descriptions.SERVICE, Text.Details.SERVICE_DET, 695, 190),
    VAN (Text.Descriptions.VAN, Text.Details.VAN_DET ,695, 100);

    private String description;
    private String details;
    private final double iconX;
    private final double iconY;

    RoomType(String description, String details, double iconX, double iconY) {
        this.description = description;
        this.details = details;
        this.iconX = iconX;
        this.iconY = iconY;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public double getIconX() { return iconX; }
    public double getIconY() { return iconY; }
}


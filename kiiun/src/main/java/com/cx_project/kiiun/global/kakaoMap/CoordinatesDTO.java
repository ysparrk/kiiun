package com.cx_project.kiiun.global.kakaoMap;

public class CoordinatesDTO {
    private Double latitude;
    private Double longitude;

    public CoordinatesDTO(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}

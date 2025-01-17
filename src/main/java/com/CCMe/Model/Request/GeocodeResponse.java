package com.CCMe.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GeocodeResponse {

    private List<Result> results;
    private String status;

    // Getters and Setters
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Inner class for Result
    public static class Result {

        private Geometry geometry;

        // Getters and Setters
        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
    }

    // Inner class for Geometry
    public static class Geometry {
        private Location location;

        // Getters and Setters
        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    // Inner class for Location (latitude, longitude)
    public static class Location {

        private double lat;
        private double lng;

        // Getters and Setters
        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}


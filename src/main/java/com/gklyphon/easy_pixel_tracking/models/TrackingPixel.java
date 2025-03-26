package com.gklyphon.easy_pixel_tracking.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tracking_pixel")
public class TrackingPixel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String userAgent;
    private String referer;

    public TrackingPixel() {
    }

    public TrackingPixel(Long id, String ip, String userAgent, String referer) {
        this.id = id;
        this.ip = ip;
        this.userAgent = userAgent;
        this.referer = referer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public TrackingPixel(Builder builder) {
        this.id = builder.id;
        this.ip = builder.ip;
        this.userAgent = builder.userAgent;
        this.referer = builder.referer;
    }

    public static class Builder {
        private Long id;
        private String ip;
        private String userAgent;
        private String referer;

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder referer(String referer) {
            this.referer = referer;
            return this;
        }

        public TrackingPixel build() {
            return new TrackingPixel(this);
        }

    }

}

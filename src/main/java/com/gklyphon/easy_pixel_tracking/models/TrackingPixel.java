package com.gklyphon.easy_pixel_tracking.models;

import jakarta.persistence.*;

/**
 * Represents a tracking pixel entity that records user interactions.
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
@Entity
@Table(name = "tracking_pixel")
public class TrackingPixel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * IP address of the user triggering the tracking pixel.
     */
    private String ip;

    /**
     * User agent string of the browser or device accessing the tracking pixel.
     */
    private String userAgent;

    /**
     * Referrer URL from which the tracking pixel was accessed.
     */
    private String referer;

    /**
     * Default constructor.
     */
    public TrackingPixel() {
    }

    /**
     * Parameterized constructor to initialize a TrackingPixel instance.
     *
     * @param id        the unique identifier
     * @param ip        the IP address of the user
     * @param userAgent the user agent string
     * @param referer   the referrer URL
     */
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

    /**
     * Constructs a TrackingPixel instance using a builder pattern.
     *
     * @param builder the builder instance
     */
    public TrackingPixel(Builder builder) {
        this.id = builder.id;
        this.ip = builder.ip;
        this.userAgent = builder.userAgent;
        this.referer = builder.referer;
    }

    /**
     * Builder class for constructing TrackingPixel instances.
     */
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

        /**
         * Builds and returns a TrackingPixel instance.
         *
         * @return a new TrackingPixel instance
         */
        public TrackingPixel build() {
            return new TrackingPixel(this);
        }

    }

}

package com.gklyphon.easy_pixel_tracking.repository;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing TrackingPixel entities.
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
public interface ITrackingPixelRepository extends JpaRepository<TrackingPixel, Long> {

    /**
     * Retrieves a paginated list of all tracking pixels ordered by creation date.
     *
     * @param pageable pagination information
     * @return a paginated list of tracking pixels
     */
    Page<TrackingPixel> findAllByOrderByCreatedAt(Pageable pageable);

    /**
     * Retrieves a paginated list of tracking pixels filtered by IP address and ordered by creation date.
     *
     * @param ip the IP address to filter by
     * @param pageable pagination information
     * @return a paginated list of tracking pixels matching the given IP address
     */
    Page<TrackingPixel> findByIpOrderByCreatedAt(String ip, Pageable pageable);

    /**
     * Retrieves a paginated list of tracking pixels filtered by user agent and ordered by creation date.
     *
     * @param userAgent the user agent string to filter by
     * @param pageable pagination information
     * @return a paginated list of tracking pixels matching the given user agent
     */
    Page<TrackingPixel> findByUserAgentOrderByCreatedAt(String userAgent, Pageable pageable);

    /**
     * Retrieves a paginated list of tracking pixels filtered by referrer URL and ordered by creation date.
     *
     * @param referer the referrer URL to filter by
     * @param pageable pagination information
     * @return a paginated list of tracking pixels matching the given referrer URL
     */
    Page<TrackingPixel> findByRefererOrderByCreatedAt(String referer, Pageable pageable);
}

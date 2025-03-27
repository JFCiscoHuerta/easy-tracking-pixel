package com.gklyphon.easy_pixel_tracking.services;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing TrackingPixel entities.
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
public interface ITrackingPixelService {

    /**
     * Retrieves a paginated list of all tracking pixels.
     *
     * @param pageable pagination information
     * @return a paginated list of tracking pixels
     */
    Page<TrackingPixel> findAll(Pageable pageable);

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

    /**
     * Retrieves a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel
     * @return the tracking pixel entity
     */
    TrackingPixel findById(Long id);

    /**
     * Saves a new tracking pixel entity.
     *
     * @param trackingPixel the tracking pixel to save
     * @return the saved tracking pixel entity
     */
    TrackingPixel save(TrackingPixel trackingPixel);

    /**
     * Updates an existing tracking pixel entity.
     *
     * @param id the ID of the tracking pixel to update
     * @param trackingPixel the updated tracking pixel data
     * @return the updated tracking pixel entity
     */
    TrackingPixel update(Long id, TrackingPixel trackingPixel);

    /**
     * Deletes a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel to delete
     */
    void deleteById(Long id);
}

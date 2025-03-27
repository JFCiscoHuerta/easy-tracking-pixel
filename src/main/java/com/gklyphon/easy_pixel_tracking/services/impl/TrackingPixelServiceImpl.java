package com.gklyphon.easy_pixel_tracking.services.impl;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import com.gklyphon.easy_pixel_tracking.repository.ITrackingPixelRepository;
import com.gklyphon.easy_pixel_tracking.services.ITrackingPixelService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for managing TrackingPixel entities.
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
@Service
public class TrackingPixelServiceImpl implements ITrackingPixelService {

    private final ITrackingPixelRepository trackingPixelRepository;

    public TrackingPixelServiceImpl(ITrackingPixelRepository trackingPixelRepository) {
        this.trackingPixelRepository = trackingPixelRepository;
    }

    /**
     * Retrieves a paginated list of all tracking pixels.
     *
     * @param pageable pagination information
     * @return a paginated list of tracking pixels
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findAll(Pageable pageable) {
        return trackingPixelRepository.findAll(pageable);
    }

    /**
     * Retrieves a paginated list of all tracking pixels ordered by creation date.
     *
     * @param pageable pagination information
     * @return a paginated list of tracking pixels
     */
    @Override
    @Transactional
    public Page<TrackingPixel> findAllByOrderByCreatedAt(Pageable pageable) {
        return trackingPixelRepository.findAllByOrderByCreatedAt(pageable);
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by IP address and ordered by creation date.
     *
     * @param ip       the IP address to filter by
     * @param pageable pagination information
     * @return a paginated list of tracking pixels matching the given IP address
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByIpOrderByCreatedAt(String ip, Pageable pageable) {
        return trackingPixelRepository.findByIpOrderByCreatedAt(ip, pageable);
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by user agent and ordered by creation date.
     *
     * @param userAgent the user agent string to filter by
     * @param pageable  pagination information
     * @return a paginated list of tracking pixels matching the given user agent
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByUserAgentOrderByCreatedAt(String userAgent, Pageable pageable) {
        return trackingPixelRepository.findByUserAgentOrderByCreatedAt(userAgent, pageable);
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by referrer URL and ordered by creation date.
     *
     * @param referer  the referrer URL to filter by
     * @param pageable pagination information
     * @return a paginated list of tracking pixels matching the given referrer URL
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByRefererOrderByCreatedAt(String referer, Pageable pageable) {
        return trackingPixelRepository.findByRefererOrderByCreatedAt(referer, pageable);
    }

    /**
     * Retrieves a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel
     * @return the tracking pixel entity
     */
    @Override
    @Transactional(readOnly = true)
    public TrackingPixel findById(Long id) {
        return trackingPixelRepository.findById(id)
                .orElseThrow();
    }

    /**
     * Saves a new tracking pixel entity.
     *
     * @param trackingPixel the tracking pixel to save
     * @return the saved tracking pixel entity
     */
    @Override
    @Transactional
    public TrackingPixel save(TrackingPixel trackingPixel) {
        try {
            return trackingPixelRepository.save(trackingPixel);
        } catch (Exception ex) {
            throw new ServiceException("Unexpected error while saving.", ex);
        }
    }

    /**
     * Updates an existing tracking pixel entity.
     *
     * @param id            the ID of the tracking pixel to update
     * @param trackingPixel the updated tracking pixel data
     * @return the updated tracking pixel entity
     */
    @Override
    @Transactional
    public TrackingPixel update(Long id, TrackingPixel trackingPixel) {
        TrackingPixel originalTrackingPixel = findById(id);
        try {
            BeanUtils.copyProperties(trackingPixel, originalTrackingPixel, "id", "createdAt", "updatedAt");
            return trackingPixelRepository.save(originalTrackingPixel);
        } catch (Exception ex) {
            throw new ServiceException("Unexpected error while updating.", ex);
        }
    }

    /**
     * Deletes a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel to delete
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            trackingPixelRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException("Unexpected error while deleting.", ex);
        }
    }
}

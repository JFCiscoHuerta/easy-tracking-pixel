package com.gklyphon.easy_pixel_tracking.services;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrackingPixelService {
    Page<TrackingPixel> findAll(Pageable pageable);
    Page<TrackingPixel> findAllByOrderByCreatedAt(Pageable pageable);
    Page<TrackingPixel> findByIpOrderByCreatedAt(String ip, Pageable pageable);
    Page<TrackingPixel> findByUserAgentOrderByCreatedAt(String userAgent, Pageable pageable);
    Page<TrackingPixel> findByRefererOrderByCreatedAt(String referer, Pageable pageable);

    TrackingPixel findById(Long id);
    TrackingPixel save(TrackingPixel trackingPixel);
    TrackingPixel update(Long id, TrackingPixel trackingPixel);
    void deleteById(Long id);
}

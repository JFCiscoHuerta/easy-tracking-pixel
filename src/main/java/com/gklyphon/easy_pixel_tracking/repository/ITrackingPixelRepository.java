package com.gklyphon.easy_pixel_tracking.repository;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrackingPixelRepository extends JpaRepository<TrackingPixel, Long> {
    Page<TrackingPixel> findAllByOrderByCreatedAt(Pageable pageable);
    Page<TrackingPixel> findByIpOrderByCreatedAt(String ip, Pageable pageable);
    Page<TrackingPixel> findByUserAgentOrderByCreatedAt(String userAgent, Pageable pageable);
    Page<TrackingPixel> findByRefererOrderByCreatedAt(String referer, Pageable pageable);
}

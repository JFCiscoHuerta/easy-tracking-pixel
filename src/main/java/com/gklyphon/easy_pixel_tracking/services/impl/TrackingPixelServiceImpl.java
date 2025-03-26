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

@Service
public class TrackingPixelServiceImpl implements ITrackingPixelService {

    private final ITrackingPixelRepository trackingPixelRepository;

    public TrackingPixelServiceImpl(ITrackingPixelRepository trackingPixelRepository) {
        this.trackingPixelRepository = trackingPixelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findAll(Pageable pageable) {
        return trackingPixelRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Page<TrackingPixel> findAllByOrderByCreatedAt(Pageable pageable) {
        return trackingPixelRepository.findAllByOrderByCreatedAt(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByIpOrderByCreatedAt(String ip, Pageable pageable) {
        return trackingPixelRepository.findByIpOrderByCreatedAt(ip, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByUserAgentOrderByCreatedAt(String userAgent, Pageable pageable) {
        return trackingPixelRepository.findByUserAgentOrderByCreatedAt(userAgent, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrackingPixel> findByRefererOrderByCreatedAt(String referer, Pageable pageable) {
        return trackingPixelRepository.findByRefererOrderByCreatedAt(referer, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TrackingPixel findById(Long id) {
        return trackingPixelRepository.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional
    public TrackingPixel save(TrackingPixel trackingPixel) {
        try {
            return trackingPixelRepository.save(trackingPixel);
        } catch (Exception ex) {
            throw new ServiceException("Unexpected error while saving.", ex);
        }
    }

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

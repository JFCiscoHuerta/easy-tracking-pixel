package com.gklyphon.easy_pixel_tracking.controllers;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import com.gklyphon.easy_pixel_tracking.services.ITrackingPixelService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Tracking operations.
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final ITrackingPixelService trackingPixelService;
    private final PagedResourcesAssembler<TrackingPixel> pagedResourcesAssembler;

    public TrackingController(ITrackingPixelService trackingPixelService, PagedResourcesAssembler<TrackingPixel> pagedResourcesAssembler) {
        this.trackingPixelService = trackingPixelService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Retrieves a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel
     * @return the tracking pixel entity or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(trackingPixelService.findById(id));
    }

    /**
     * Retrieves a paginated list of all tracking pixels.
     *
     * @param page page number (default: 0)
     * @param size page size (default: 10)
     * @return paginated tracking pixels
     */
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findAll(pageable)));
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by IP.
     *
     * @param ip   the IP address to filter by
     * @param page page number (default: 0)
     * @param size page size (default: 10)
     * @return paginated tracking pixels
     */
    @GetMapping("/by-ip")
    public ResponseEntity<?> getAllByIp(
            @RequestParam("ip") String ip,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByIpOrderByCreatedAt(ip, pageable)));
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by user-agent.
     *
     * @param userAgent user agent string to filter by
     * @param page      page number (default: 0)
     * @param size      page size (default: 10)
     * @return paginated tracking pixels
     */
    @GetMapping("/by-user-agent")
    public ResponseEntity<?> getAllByUserAgent(
            @RequestParam("user-agent") String userAgent,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByUserAgentOrderByCreatedAt(userAgent, pageable)));
    }

    /**
     * Retrieves a paginated list of tracking pixels filtered by referrer URL.
     *
     * @param referer referrer URL to filter by
     * @param page    page number (default: 0)
     * @param size    page size (default: 10)
     * @return paginated tracking pixels
     */
    @GetMapping("/by-referer")
    public ResponseEntity<?> getAllByReferer(
            @RequestParam("referer") String referer,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByRefererOrderByCreatedAt(referer, pageable)));
    }

    /**
     * Creates a new tracking pixel.
     *
     * @param trackingPixel the tracking pixel to save
     * @return the saved tracking pixel
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TrackingPixel trackingPixel) {
        TrackingPixel trackingPixelToSave = trackingPixelService.save(trackingPixel);
        return ResponseEntity.status(HttpStatus.CREATED).body(trackingPixelToSave);
    }

    /**
     * Updates an existing tracking pixel.
     *
     * @param id            the ID of the tracking pixel to update
     * @param trackingPixel the updated tracking pixel data
     * @return the updated tracking pixel entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TrackingPixel trackingPixel) {
        return ResponseEntity.ok(trackingPixelService.update(id, trackingPixel));
    }

    /**
     * Deletes a tracking pixel by its unique identifier.
     *
     * @param id the ID of the tracking pixel to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        trackingPixelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Converts a paginated list of TrackingPixel entities into a HATEOAS-compliant PagedModel.
     *
     * @param page the paginated tracking pixel data
     * @return a PagedModel containing tracking pixels with pagination metadata
     */
    private PagedModel<EntityModel<TrackingPixel>> buildPagedModel(Page<TrackingPixel> page) {
        return pagedResourcesAssembler.toModel(page);
    }

}

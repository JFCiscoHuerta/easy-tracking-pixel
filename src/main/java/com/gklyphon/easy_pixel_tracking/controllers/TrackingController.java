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

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final ITrackingPixelService trackingPixelService;
    private final PagedResourcesAssembler<TrackingPixel> pagedResourcesAssembler;

    public TrackingController(ITrackingPixelService trackingPixelService, PagedResourcesAssembler<TrackingPixel> pagedResourcesAssembler) {
        this.trackingPixelService = trackingPixelService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(trackingPixelService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findAll(pageable)));
    }

    @GetMapping("/by-ip")
    public ResponseEntity<?> getAllByIp(
            @RequestParam("ip") String ip,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByIpOrderByCreatedAt(ip, pageable)));
    }

    @GetMapping("/by-user-agent")
    public ResponseEntity<?> getAllByUserAgent(
            @RequestParam("user-agent") String userAgent,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByUserAgentOrderByCreatedAt(userAgent, pageable)));
    }

    @GetMapping("/by-referer")
    public ResponseEntity<?> getAllByReferer(
            @RequestParam("referer") String referer,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(
                buildPagedModel(trackingPixelService.findByRefererOrderByCreatedAt(referer, pageable)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TrackingPixel trackingPixel) {
        TrackingPixel trackingPixelToSave = trackingPixelService.save(trackingPixel);
        return ResponseEntity.status(HttpStatus.CREATED).body(trackingPixelToSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TrackingPixel trackingPixel) {
        return ResponseEntity.ok(trackingPixelService.update(id, trackingPixel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        trackingPixelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<TrackingPixel>> buildPagedModel(Page<TrackingPixel> page) {
        return pagedResourcesAssembler.toModel(page);
    }

}

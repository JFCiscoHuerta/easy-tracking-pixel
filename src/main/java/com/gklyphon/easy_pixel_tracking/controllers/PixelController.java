package com.gklyphon.easy_pixel_tracking.controllers;

import com.gklyphon.easy_pixel_tracking.models.TrackingPixel;
import com.gklyphon.easy_pixel_tracking.services.ITrackingPixelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Controller for handling tracking pixel requests.
 *
 * <p>The pixel image is served as a PNG file from the classpath resources.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/pixel")
public class PixelController {

    private final ITrackingPixelService pixelTrackingService;

    public PixelController(ITrackingPixelService pixelTrackingService) {
        this.pixelTrackingService = pixelTrackingService;
    }

    /**
     * Handles requests to the tracking pixel endpoint.
     * Logs request details and serves a transparent 1x1 PNG image.
     *
     * @param request HttpServletRequest containing client request details
     * @return ResponseEntity containing the tracking pixel image as byte array
     */
    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPixel(HttpServletRequest request) throws IOException {
        TrackingPixel trackingPixel = new TrackingPixel.Builder()
                .ip(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .referer(request.getHeader("Referer"))
                .build();

        pixelTrackingService.save(trackingPixel);

        ClassPathResource resourceFile = new ClassPathResource("static/pixel.png");
        return Files.readAllBytes(resourceFile.getFile().toPath());
    }
}

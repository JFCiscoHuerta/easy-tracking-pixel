# Easy Tracking Pixel

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-green)
![License](https://img.shields.io/badge/License-Apache2-yellow)
![Build](https://img.shields.io/badge/Build-Maven-red)

A simple tracking pixel service built with Spring Boot. This service records visitor information such as IP address, user agent, and referer when the tracking pixel is accessed.


## Endpoints
|         | Description                          | Endpoint   |
|---------------|----------------------------------|------|
| **Track a Visitor**           | Returns a 1x1 PNG image and logs the visitor details. | `GET /tracking` |
| **Retrieve All Tracked Visits** | Returns paginated tracking data. | `GET /tracking?page=0&size=10` |
| **Retrieve Tracking by ID**     | Retrieves tracking data by its unique ID. | `GET /tracking/{id}` |
| **Filter by IP Address**        | Retrieves tracking data filtered by the IP address. | `GET /tracking/by-ip?ip=192.168.1.1&page=0&size=10` |
| **Filter by User-Agent**        | Retrieves tracking data filtered by User-Agent. | `GET /tracking/by-user-agent?user-agent=Mozilla/5.0&page=0&size=10` |
| **Filter by Referer**           | Retrieves tracking data filtered by Referer. | `GET /tracking/by-referer?referer=https://example.com&page=0&size=10` |
| **Create Tracking Entry**       | Creates a new tracking entry. | `POST /tracking` |
| **Update Tracking Entry**       | Updates an existing tracking entry by ID. | `PUT /tracking/{id}` |
| **Delete Tracking Entry**       | Deletes a tracking entry by ID. | `DELETE /tracking/{id}` |

---

### How to Add the Tracking Pixel Image

You can add the tracking pixel image to any HTML page using the following code:

```html
<img src="http://localhost:8081/pixel" alt="Tracking Pixel" />
```

## License

This project is licensed under the  Apache 2.0 license - see the LICENSE file for detail

package dev.sandboxapp.totp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

  @GetMapping("/heartbeat")
  Map<String, String> heartbeat() {
    var response = new HashMap<String, String>();
    response.put("status", "ok");
    return response;
  }
}

package dev.sandboxapp.totp.dto;

import java.util.List;
import java.util.UUID;

public record AccountOneTimePasscodes(
  UUID id,
  Integer period,
  List<OneTimePasscode> passcodes
) {
}

import FingerprintJS from '@fingerprintjs/fingerprintjs';

export async function loadConfig() {
  const fp = await FingerprintJS.load();
  const fingerprint = await fp.get();
  window.fingerprintVistorId = fingerprint.visitorId;
}

import FingerprintJS from '@fingerprintjs/fingerprintjs';

export async function loadConfig() {
  const fp = await FingerprintJS.load();
  window.fingerprintjs = await fp.get();
  return null;
}

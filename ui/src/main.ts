import { bootstrapApplication } from '@angular/platform-browser';
import FingerprintJS from '@fingerprintjs/fingerprintjs';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

declare global {
  interface Window {
    fingerprintVistorId: string;
  }
}

async function loadApplication() {
  const fp = await FingerprintJS.load();
  const fingerprint = await fp.get();
  window.fingerprintVistorId = fingerprint.visitorId;
  await bootstrapApplication(AppComponent, appConfig);
}

loadApplication().catch((err) => console.error(err))

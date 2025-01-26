import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { IntlProvider } from 'react-intl';
import messages from './messages.json';
import { ThemeProvider } from '@/components/ThemeProvider.jsx';
import { BrowserRouter } from 'react-router-dom';
import AuthProvider from '@/components/AuthProvider.jsx';
import FingerprintJS from '@fingerprintjs/fingerprintjs';

(async () => {
  const fp = await FingerprintJS.load();
  window.fingerprintjs = await fp.get();
  createRoot(document.getElementById('root')).render(
    <StrictMode>
      <IntlProvider locale="en" messages={ messages }>
        <ThemeProvider>
          <BrowserRouter>
            <AuthProvider>
              <App />
            </AuthProvider>
          </BrowserRouter>
        </ThemeProvider>
      </IntlProvider>
    </StrictMode>
  )
})();

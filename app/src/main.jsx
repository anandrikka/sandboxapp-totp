import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { IntlProvider } from 'react-intl';
import messages from './messages.json';
import { ThemeProvider } from '@/components/providers/ThemeProvider.jsx';
import { BrowserRouter } from 'react-router-dom';
import { UserProvider } from '@/components/providers/UserProvider.jsx';
import { loadConfig } from '@/lib/config.js';

loadConfig().then(() => {
  createRoot(document.getElementById('root')).render(
    <UserProvider>
      <IntlProvider locale="en" messages={ messages }>
        <BrowserRouter>
          <ThemeProvider>
            <StrictMode>
              <App />
            </StrictMode>
          </ThemeProvider>
        </BrowserRouter>
      </IntlProvider>
    </UserProvider>
  )
});

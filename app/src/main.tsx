import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from "./App.tsx";
import { ThemeProvider } from '@/components/ThemeProvider.tsx';
import messages from './messages.json';
import { IntlProvider } from 'react-intl';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ThemeProvider>
      <IntlProvider locale="en" messages={ messages }>
        <App />
      </IntlProvider>
    </ThemeProvider>
  </StrictMode>,
)

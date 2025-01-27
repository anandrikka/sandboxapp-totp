import { createContext } from 'react';

export const UserContext = createContext(null);
export const LogoutContext = createContext(null);
export const ThemeProviderContext = createContext({
  theme: 'system',
  setTheme: () => null,
})


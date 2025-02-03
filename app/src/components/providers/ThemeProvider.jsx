import React, { useEffect, useMemo, useState } from 'react'
import { ThemeProviderContext } from '@/lib/contexts.js';

export function ThemeProvider({
  children,
  defaultTheme = 'system',
  storageKey = 'ui-theme'
}) {
  const [theme, setTheme] = useState(() => localStorage.getItem(storageKey) || defaultTheme)

  useEffect(() => {
    const root = window.document.documentElement
    root.classList.remove('light', 'dark')
    if (theme === 'system') {
      const systemTheme = window.matchMedia('(prefers-color-scheme: dark)')
        .matches
        ? 'dark'
        : 'light'

      root.classList.add(systemTheme)
      return
    }
    root.classList.add(theme)
  }, [theme]);

  const value = useMemo(() => {
    return {
      theme,
      setTheme: (theme) => {
        localStorage.setItem(storageKey, theme)
        setTheme(theme)
      },
    }
  }, [theme, setTheme])

  return (
    <ThemeProviderContext.Provider value={ value }>
      {children}
    </ThemeProviderContext.Provider>
  )
}

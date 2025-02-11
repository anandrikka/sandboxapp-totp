import { DarkModeIcon, LightModeIcon } from '@/components/ui/icons';
import { useTheme } from '@/components/providers/ThemeProvider.jsx';

export default function ThemeToggler() {
  const { theme, setTheme } = useTheme();
  const finalTheme = theme === 'system' ? window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light' : theme;
  const Icon = finalTheme === 'dark' ? DarkModeIcon : LightModeIcon;

  function onThemeChange() {
    setTheme(finalTheme === 'dark' ? 'light' : 'dark')
  }

  return (
    <button
      type="button"
      className="text-foreground hover:bg-accent focus:outline-none rounded-lg text-sm p-2.5"
    >
      <Icon onClick={ onThemeChange } />
    </button>
  )
}

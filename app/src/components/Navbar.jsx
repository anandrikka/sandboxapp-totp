import { FormattedMessage } from 'react-intl';
import Profile from '@/components/Profile.jsx';

/**
 * Renders a fixed navigation bar with a title and a profile section.
 *
 * @param {Object} props - The properties object.
 * @param {ReactNode} props.showProfile - The profile section to be rendered inside the navigation bar.
 * @return {JSX.Element} The rendered navigation bar component.
 */
export default function Navbar({ showProfile }) {
  return (
    <nav className="fixed w-full shadow-sm z-50 bg-background">
      <div className="max-w-(--breakpoint-xl) flex flex-wrap items-center justify-between mx-auto p-4">
        <span className="self-center text-xl font-semibold whitespace-nowrap">
          <FormattedMessage id="app.title" />
        </span>
        { showProfile && <Profile /> }
      </div>
    </nav>
  )
}

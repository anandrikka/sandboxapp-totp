import { FormattedMessage } from 'react-intl';

/**
 * Renders a fixed navigation bar with a title and a profile section.
 *
 * @param {Object} props - The properties object.
 * @param {ReactNode} props.profile - The profile section to be rendered inside the navigation bar.
 * @return {JSX.Element} The rendered navigation bar component.
 */
export default function Navbar({ profile }) {
  return (
    <nav className="fixed w-full shadow-sm z-50 bg-background">
      <div className="max-w-(--breakpoint-xl) flex flex-wrap items-center justify-between mx-auto p-3">
        <span className="self-center text-xl font-semibold whitespace-nowrap">
          <FormattedMessage id="app.title" />
        </span>
        { profile }
      </div>
    </nav>
  )
}

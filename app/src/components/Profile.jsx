import { Link } from 'react-router-dom';
import { useUser } from '@/components/providers/UserProvider.jsx';

export default function Profile() {
  const { user } = useUser();
  return (
    <div className="relative">
      <button type="button"
              className="flex bg-foreground text-sm rounded-full focus:ring-4">
        <span className="w-8 h-8 rounded-full self-center content-center font-semibold text-md text-background">AR</span>
      </button>
      <div
        className="z-51 absolute top-8 right-0 my-4 text-base bg-secondary divide-y divide-secondary-foreground rounded-lg shadow-xs hidden">
        <div className="px-4 py-3">
          <span className="block text-sm">{`${user.firstName} ${user.lastName}`}</span>
          <span className="block text-sm">${user.email}</span>
        </div>
        <ul className="py-2">
          <li>
            <Link to="/profile" className="block px-4 py-2 hover:bg-background">Profile</Link>
          </li>
          <li>
            <Link to="/devices" className="block px-4 py-2 hover:bg-background">Devices</Link>
          </li>
          <li>
            <Link to="/login" className="block px-4 py-2 hover:bg-background">Logout</Link>
          </li>
        </ul>
      </div>
    </div>
  )
}

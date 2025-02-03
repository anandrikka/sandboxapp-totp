import { Navigate, Outlet } from 'react-router-dom';
import { useUser } from '@/components/providers/UserProvider.jsx';

export default function Authenticated() {
  const { user } = useUser();
  if (!user) {
    return <Navigate to="login" />
  }
  return (
    <Outlet />
  )
}

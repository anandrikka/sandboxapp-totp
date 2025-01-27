import { Navigate, Outlet } from 'react-router-dom';
import { useUser } from '@/hooks/use-user.js';

export default function Authenticated() {
  const { user } = useUser();
  if (!user) {
    return <Navigate to="login" />
  }
  return (
    <Outlet />
  )
}

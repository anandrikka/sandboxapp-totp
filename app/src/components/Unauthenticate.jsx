import { Outlet } from 'react-router-dom';
import { useEffect } from 'react';
import { inValidateCookie } from '@/lib/auth.js';

export default function Unauthenticate() {
  useEffect(() => {
    inValidateCookie().then(() => {})
  }, []);
  return (
    <Outlet />
  )
}

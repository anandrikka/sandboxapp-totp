import { useContext } from 'react';
import { LogoutContext, UserContext } from '@/lib/contexts.js';

export function useUser() {
  return useContext(UserContext);
}

export function useLogout() {
  return useContext(LogoutContext);
}

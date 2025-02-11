import {
  useCallback, useContext,
  useEffect,
  useMemo,
  useState,
  createContext
} from 'react';
import { fetchUser } from '@/lib/auth.js';

const UserContext = createContext(null);
const LogoutContext = createContext(null);

export function UserProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loaded, setLoaded] = useState(false);

  const logout = useCallback(() => {
    setUser(() => null)
  }, []);

  useEffect(() => {
    fetchUser().then((response) => {
      setUser(response.data)
    }).finally(() => setLoaded(true))
  }, []);

  return (
    <UserContext.Provider value={ user }>
      <LogoutContext.Provider value = { logout }>
        { loaded && children }
      </LogoutContext.Provider>
    </UserContext.Provider>
  )
}

export function useUser() {
  return useContext(UserContext);
}

export function useLogout() {
  return useContext(LogoutContext);
}

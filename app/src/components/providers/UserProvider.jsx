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
  const [userChecked, setUserChecked] = useState(false);

  const logout = useCallback(() => {
    setUser(() => null)
  }, [])

  useEffect(() => {
    fetchUser().then((response) => {
      setUser(response.data)
    }).finally(() => setUserChecked(true))
  }, []);

  const value = useMemo(() => {
    return { user, userChecked }
  }, [user, userChecked]);

  console.log('user provider..')

  return (
    <UserContext.Provider value={ value }>
      <LogoutContext.Provider value = { logout }>
        { children }
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

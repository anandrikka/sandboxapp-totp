import {
  useCallback,
  useEffect,
  useMemo,
  useState
} from 'react';
import { fetchUser } from '@/lib/auth.js';
import { LogoutContext, UserContext } from '@/lib/contexts.js';

export default function UserProvider({ children }) {
  const [user, setUser] = useState(null);
  const [checkCompleted, setCheckCompleted] = useState(false);

  const logout = useCallback(() => {
    setUser(() => null)
  }, [])

  const userContextValue = useMemo(() => {
    return { user, checkCompleted, setUser }
  }, [user, checkCompleted, setUser])

  useEffect(() => {
    fetchUser().then((response) => {
      setUser(response.data)
    }).finally(() => setCheckCompleted(true))
  }, []);

  console.log('her...')

  return (
    <UserContext.Provider value={ userContextValue }>
      <LogoutContext.Provider value = { logout }>
        { children }
      </LogoutContext.Provider>
    </UserContext.Provider>
  )
}

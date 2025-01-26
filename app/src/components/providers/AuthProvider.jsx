import Navbar from '@/components/Navbar.jsx';
import { createContext, useContext, useEffect, useState } from 'react';

const UserContext = createContext(null);
const LogoutContext = createContext(null);

async function fetchUser() {
  let response = await fetch('/api/v1/users/self')
  return {
    status: response.status,
    data: await response.json()
  }
}

export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    setLoading(true);
    fetchUser().then(({ status, data }) => {
      if (status === 401) {

      } else {
        setUser(data)
      }
      setLoading(false);
    }).finally(() => setLoading(false))
  }, []);
  return (
    <UserContext.Provider value={ user }>
      <LogoutContext.Provider value = { setUser }>
        <div>
          <Navbar />
          { loading ? null : children }
        </div>
      </LogoutContext.Provider>
    </UserContext.Provider>
  )
}

export function useUser() {
  return useContext(UserContext);
}

export function useLogout() {
  const setUser =  useContext(LogoutContext);
  return () => setUser(null);
}


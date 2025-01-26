import Navbar from '@/components/Navbar.jsx';
import { createContext, useContext, useEffect, useState } from 'react';
import fetcher from '@/lib/fetcher.js';

const AuthContext = createContext(null);

export default function AuthProvider({ children }) {
  const [ user, setUser ] = useState(null);
  useEffect(() => {
    fetcher.get('/api/v1/users/self')
      .then((data) => console.log(data))
  }, []);
  return (
    <AuthContext.Provider value={ user }>
      <div>
        <Navbar />
        { children }
      </div>
    </AuthContext.Provider>
  )
}

export function useUser() {
  return useContext(AuthContext);
}


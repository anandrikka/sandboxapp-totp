import Navbar from '@/components/Navbar.jsx';
import { Route, Routes } from 'react-router-dom';
import Accounts from '@/pages/Accounts.jsx';
import Devices from '@/pages/Devices.jsx';
import Profile from '@/pages/Profile.jsx';
import Authenticated from '@/components/Authenticated.jsx';
import Login from '@/pages/Login.jsx';
import Signup from '@/pages/Signup.jsx';
import Unauthenticate from '@/components/Unauthenticate.jsx';
import { useUser } from '@/hooks/use-user.js';

function App() {
  const { checkCompleted, user } = useUser();
  return (
    <div className="w-full h-full">
      <Navbar showProfile={ checkCompleted && !!user } />
      <div className="max-w-(--breakpoint-xl) mx-auto px-4 pt-20">
        {
          checkCompleted && (
            <Routes>
              <Route element={ <Authenticated /> }>
                <Route index element={ <Accounts /> } />
                <Route path="devices" element={ <Devices /> } />
                <Route path="profile" element={ <Profile /> } />
              </Route>
              <Route element={ <Unauthenticate /> }>
                <Route path="login" Component={ Login } />
                <Route path="signup" Component={ Signup } />
              </Route>
            </Routes>
          )
        }
      </div>
    </div>
  )
}

export default App

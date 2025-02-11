import Navbar from '@/components/Navbar.jsx';
import { Route, Routes } from 'react-router-dom';
import Accounts from '@/pages/Accounts.jsx';
import Devices from '@/pages/Devices.jsx';
import Profile from '@/pages/Profile.jsx';
import Authenticated from '@/components/Authenticated.jsx';
import Login from '@/pages/Login.jsx';
import Signup from '@/pages/Signup.jsx';
import { useUser } from '@/components/providers/UserProvider.jsx';

function App() {
  const user = useUser();
  return (
    <div className="w-full h-full">
      <Navbar showProfile={ user } />
      <div className="max-w-(--breakpoint-xl) mx-auto px-4 pt-20">
        <Routes>
          <Route element={ <Authenticated /> }>
            <Route index element={ <Accounts /> } />
            <Route path="devices" element={ <Devices /> } />
            <Route path="profile" element={ <Profile /> } />
          </Route>
          <Route path="login" Component={ Login } />
          <Route path="signup" Component={ Signup } />
        </Routes>
      </div>
    </div>
  )
}

export default App

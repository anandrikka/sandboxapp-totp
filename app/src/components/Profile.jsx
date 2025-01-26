export default function Profile() {
  return (
    <div className="relative">
      <button type="button"
              className="flex bg-foreground text-sm rounded-full focus:ring-4">
        <span className="w-8 h-8 rounded-full self-center content-center font-semibold text-md text-background">AR</span>
      </button>
      <div
        className="z-51 absolute top-8 right-0 my-4 text-base bg-secondary divide-y divide-secondary-foreground rounded-lg shadow-xs hidden">
        <div className="px-4 py-3">
          <span className="block text-sm">Bonnie Green</span>
          <span className="block text-sm">name@flowbite.com</span>
        </div>
        <ul className="py-2">
          <li>
            <a href="#"
               className="block px-4 py-2 hover:bg-background">Dashboard</a>
          </li>
          <li>
            <a href="#"
               className="block px-4 py-2 hover:bg-background">Settings</a>
          </li>
          <li>
            <a href="#"
               className="block px-4 py-2 hover:bg-background">Earnings</a>
          </li>
          <li>
            <a href="#"
               className="block px-4 py-2 hover:bg-background">Signout</a>
          </li>
        </ul>
      </div>
    </div>
  )
}
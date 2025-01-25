export default function Navbar() {
  return (
    <nav className="fixed w-full shadow-sm z-50">
      <div className="max-w-(--breakpoint-xl) flex flex-wrap items-center justify-between mx-auto p-3">
        <span className="self-center text-xl font-semibold whitespace-nowrap">
          TOTP Generator
        </span>
        <div className="relative">
          <button type="button"
                  className="flex bg-foreground text-sm rounded-full focus:ring-4">
            <span className="w-8 h-8 rounded-full self-center content-center font-semibold text-md text-background">AR</span>
          </button>
          <div
            className="hidden z-51 absolute top-8 right-0 my-4 text-base bg-secondary divide-y divide-secondary-foreground rounded-lg shadow-xs">
            <div className="px-4 py-3">
              <span className="block text-sm ">Bonnie Green</span>
              <span className="block text-sm  ">name@flowbite.com</span>
            </div>
            <ul className="py-2">
              <li>
                <a href="#"
                   className="block px-4 py-2 hover:bg-popover">Dashboard</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 hover:bg-popover">Settings</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 hover:bg-popover">Earnings</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 hover:bg-popover">Sign
                  out</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  )
}
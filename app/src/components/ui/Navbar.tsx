export default function Navbar() {
  return (
    <nav className="fixed w-full bg-background shadow-sm z-50">
      <div className="max-w-(--breakpoint-xl) flex flex-wrap items-center justify-between mx-auto p-3">
        <span className="self-center text-foreground text-xl font-semibold whitespace-nowrap">
          TOTP Generator
        </span>
        <div className="relative">
          <button type="button"
                  className="flex bg-foreground text-sm rounded-full focus:ring-4"
                  id="user-menu-button" data-dropdown-toggle="user-dropdown">
            <span className="w-8 h-8 rounded-full self-center content-center font-semibold text-md text-background">AR</span>
          </button>
          <div
            className="z-51 absolute top-8 right-0 my-4 text-base list-none bg-secondary divide-y divide-secondary-foreground rounded-lg shadow-xs "
            id="user-dropdown">
            <div className="px-4 py-3">
              <span className="block text-sm text-gray-900 dark:text-white">Bonnie Green</span>
              <span className="block text-sm  text-gray-500 truncate dark:text-gray-400">name@flowbite.com</span>
            </div>
            <ul className="py-2" aria-labelledby="user-menu-button">
              <li>
                <a href="#"
                   className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Dashboard</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Settings</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Earnings</a>
              </li>
              <li>
                <a href="#"
                   className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Sign
                  out</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  )
}
import { useState } from "react";
import clsx from "clsx";

const Navbar = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const handleLogout = () => {
    console.log("Logout clicked!"); // Replace this with your logout functionality
  };

  return (
    <>
      {/* Navbar Header */}
      <nav
        className={clsx(
          "fixed w-full bg-background border-b border-border z-50"
        )}
      >
        <div className="max-w-screen-xl mx-auto px-4 lg:px-6">
          <div className="flex justify-between items-center py-3">
            {/* Left Section: Mobile Menu Icon + Brand */}
            <div className="flex items-center space-x-4">
              {/* Mobile Menu Button */}
              <button
                className="md:hidden inline-flex items-center p-2 rounded-md focus:outline-none focus:ring-2 focus:ring-ring"
                aria-label="Toggle Navigation"
                onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)} // Toggle the menu
              >
                {/* Dynamic Icon: Hamburger or Close */}
                {isMobileMenuOpen ? (
                  <svg
                    className="w-6 h-6 text-muted-foreground"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M6 18L18 6M6 6l12 12"
                    />
                  </svg>
                ) : (
                  <svg
                    className="w-6 h-6 text-muted-foreground"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M4 6h16M4 12h16m-7 6h7"
                    />
                  </svg>
                )}
              </button>

              {/* Brand */}
              <span className="text-xl font-semibold text-foreground">
                Brand
              </span>
            </div>

            {/* Links for non-mobile mode */}
            <div className="hidden md:flex space-x-6">
              <a
                href="/"
                className="text-sm font-medium text-muted-foreground hover:text-foreground"
              >
                Home
              </a>
              <a
                href="/about"
                className="text-sm font-medium text-muted-foreground hover:text-foreground"
              >
                About
              </a>
              <a
                href="/services"
                className="text-sm font-medium text-muted-foreground hover:text-foreground"
              >
                Services
              </a>
              <a
                href="/contact"
                className="text-sm font-medium text-muted-foreground hover:text-foreground"
              >
                Contact
              </a>
            </div>

            {/* Right Section: Mode Toggle + Logout */}
            <div className="flex items-center space-x-4">
              {/* Light/Dark Mode Toggle Button */}
              <button
                onClick={() => console.log("Toggle light/dark mode!")} // Replace with toggle logic
                className="p-2 rounded-md bg-card text-card-foreground hover:bg-muted"
              >
                Toggle Mode
              </button>

              {/* Logout Button */}
              <button
                onClick={handleLogout}
                className="p-2 rounded-md bg-card text-card-foreground hover:bg-muted"
              >
                Logout
              </button>
            </div>
          </div>
        </div>
      </nav>

      {/* Mobile Menu Drawer */}
      <div
        className={clsx(
          "fixed top-0 left-0 h-full w-64 bg-background border-r border-border shadow-md transform transition-transform ease-in-out duration-300 z-40",
          isMobileMenuOpen ? "translate-x-0" : "-translate-x-full"
        )}
      >
        <div className="flex flex-col p-4 space-y-6">
          <a
            href="/"
            className="text-sm font-medium text-muted-foreground hover:text-foreground"
          >
            Home
          </a>
          <a
            href="/about"
            className="text-sm font-medium text-muted-foreground hover:text-foreground"
          >
            About
          </a>
          <a
            href="/services"
            className="text-sm font-medium text-muted-foreground hover:text-foreground"
          >
            Services
          </a>
          <a
            href="/contact"
            className="text-sm font-medium text-muted-foreground hover:text-foreground"
          >
            Contact
          </a>
        </div>
      </div>

      {/* Overlay: Visible when the mobile menu is open */}
      {isMobileMenuOpen && (
        <div
          className="fixed inset-0 bg-black bg-opacity-50 transition-opacity duration-300 z-30"
          onClick={() => setIsMobileMenuOpen(false)} // Close menu when clicking the overlay
        ></div>
      )}
    </>
  );
};

export default Navbar;

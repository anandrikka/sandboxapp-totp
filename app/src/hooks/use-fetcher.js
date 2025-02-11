import { useLogout } from '@/components/providers/UserProvider.jsx';

export default function useFetcher() {
  const logout = useLogout();
  async function request(url, options) {
    const defaultHeaders = {
      'Content-Type': 'application/json',
      'X-USER-IDENTIFIER': window.fingerprintVisitorId
    };
    const {
      headers = {},
      ...rest
    } = options;

    const config = {
      ...rest,
      headers: {
        ...defaultHeaders,
        ...headers,
      },
    };
    try {
      const response = await fetch(url, config);
      const responseData = await response.json();
      if (response.status === 401) {
        logout();
      }
      return {
        data: responseData,
        status: response.status,
        success: response.ok
      }
    } catch (e) {
      return {
        success: false,
        status: 0,
        data: { message: e.message || 'Network error occurred' },
      };
    }
  }

  return {
    get: (url, options = {}) => request(url, { ...options, method: 'GET' }),
    post: (url, body, options = {}) =>
      request(url, { ...options, method: 'POST', body: JSON.stringify(body) }),
    put: (url, body, options = {}) =>
      request(url, { ...options, method: 'PUT', body: JSON.stringify(body) }),
    delete: (url, options = {}) => request(url, { ...options, method: 'DELETE' })
  }
}

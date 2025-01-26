const fetchWrapper = {
  get: (url, options = {}) => request(url, { ...options, method: 'GET' }),
  post: (url, body, options = {}) =>
    request(url, { ...options, method: 'POST', body: JSON.stringify(body) }),
  put: (url, body, options = {}) =>
    request(url, { ...options, method: 'PUT', body: JSON.stringify(body) }),
  delete: (url, options = {}) => request(url, { ...options, method: 'DELETE' }),
};

// Core `request` function
async function request(url, options) {
  const defaultHeaders = {
    'Content-Type': 'application/json',
    'X-VISITOR-ID': window.fingerprintjs.visitorId
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

export default fetchWrapper;

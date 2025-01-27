export async function fetchUser() {
  let response = await fetch('/api/v1/users/self')
  if (!response.ok) {
    const error = new Error('Unable to fetch user');
    error.status = response.status
    error.data = await response.json()
    throw error;
  }
  return {
    status: response.status,
    data: await response.json()
  }
}

export async function inValidateCookie() {
  await fetch('/api/v1/auth/invalidate_token')
}


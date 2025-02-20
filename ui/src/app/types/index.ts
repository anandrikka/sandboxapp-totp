export interface Account {
  id: string
  issuer?: string
  name: string
  period: number
}

export interface SignupRequest {
  firstName: string
  lastName: string
  email: string
}

export type LoadingState<T = unknown> = { state: 'loading' } | { state: 'loaded', data: T } | { state: 'error', error: Error }

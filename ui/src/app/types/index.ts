export interface Account {
  id: string
  issuer?: string
  name: string
  period: number
}

export interface UserForm {
  firstName: string
  lastName: string
  email: string
}

export interface User extends UserForm {
  id: string
}

export interface IError {
  code: string
  message: string
  title: string
  status: number
  meta?: Record<string, string>
}

export type LoadingState = { status: 'loading' | 'initiate' } | { status: 'loaded'; data: any } | { status: 'error'; error: IError | Partial<IError> }

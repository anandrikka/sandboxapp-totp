export type LoadingState<T = unknown> = { state: 'loading' } | { state: 'loaded', data: T } | { state: 'error', error: Error }

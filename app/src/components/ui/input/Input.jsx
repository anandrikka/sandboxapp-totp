import * as styles from './Input.module.css';

export default function Input(props) {
  return (
    <input className={ styles.root } { ...props } />
  )
}

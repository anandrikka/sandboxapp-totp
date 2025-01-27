import * as styles from './Button.module.css'
import cx from 'clsx';

/**
 * Renders a customizable button component.
 *
 * @param {Object} props - The properties passed to the button component.
 * @param {string} props.label - The text to be displayed on the button.
 * @param {string} [props.kind="primary"] - The type of button (e.g., "primary", "secondary").
 * @param {Function} props.onClick - Callback function to handle button click events.
 *
 * @returns {JSX.Element} A JSX button element.
 */
export default function Button(props) {
  const {label, kind = 'primary', onClick, className} = props;
  return (
    <button
      className={cx(styles.btn, styles[kind], className)}
      onClick={onClick}
    >
      { label }
    </button>
  )
}

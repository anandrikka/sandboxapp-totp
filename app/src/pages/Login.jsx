import Button from '@/components/ui/button/Button.jsx';
import Input from '@/components/ui/input/Input.jsx';
import cn from 'clsx';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card/Card.jsx';
import { useNavigate } from 'react-router-dom';

export default function Login() {
  const navigate = useNavigate();
  return (
    <div className="flex w-full items-center justify-center p-6 md:p-10">
      <div className="w-full max-w-sm">
        <div className={ cn("flex flex-col gap-6") }>
          <Card>
            <CardHeader>
              <CardTitle className="text-2xl">Login</CardTitle>
            </CardHeader>
            <CardContent>
              <form>
                <div className="flex flex-col gap-6">
                  <div className="grid gap-2">
                    <label htmlFor="email">Email</label>
                    <Input
                      id="email"
                      type="email"
                      placeholder="abc@example.com"
                      required
                    />
                  </div>
                  <Button type="submit" className="w-full" label="Send OTP" />
                </div>
                <div className="mt-4 text-center text-sm">
                  Don&apos;t have an account?{" "}
                  <span onClick={ () => navigate('/signup') } className="underline underline-offset-4">
                    Sign up
                  </span>
                </div>
              </form>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  )
}

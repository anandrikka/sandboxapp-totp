import cn from 'clsx';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card/Card.jsx';
import Input from '@/components/ui/input/Input.jsx';
import Button from '@/components/ui/button/Button.jsx';

export default function Signup(props) {
  const { className, ...rest } = props;
  return (
    <div className="flex w-full items-center justify-center p-6 md:p-10">
      <div className="w-full max-w-sm">
        <div className={ cn("flex flex-col gap-6", className) } {...rest }>
          <Card>
            <CardHeader>
              <CardTitle className="text-2xl">Signup</CardTitle>
            </CardHeader>
            <CardContent>
              <form>
                <div className="flex flex-col gap-6">
                  <div className="flex flex-col gap-6 md:flex-row md:gap-2">
                    <div className="grid gap-2">
                      <label htmlFor="firstName">First Name</label>
                      <Input
                        id="firstName"
                        type="text"
                        placeholder="John"
                        required
                      />
                    </div>
                    <div className="grid gap-2">
                      <label htmlFor="lastName">Last Name</label>
                      <Input
                        id="lastName"
                        type="text"
                        placeholder="Doe"
                        required
                      />
                    </div>
                  </div>
                  <div className="grid gap-2">
                    <label htmlFor="email">Email</label>
                    <Input
                      id="email"
                      type="email"
                      placeholder="abc@abc.com"
                      required
                    />
                  </div>
                  <Button type="submit" className="w-full" label="Sign up" />
                </div>
              </form>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  )
}

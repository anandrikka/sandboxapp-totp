import { Component } from '@angular/core';
import { GearIconComponent } from '../../lib/components/icons/gear-icon.component';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [
    GearIconComponent
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(protected authService: AuthService) {
  }
}

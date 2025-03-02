import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from '../../../../core/services/auth.service';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons/faEnvelope';
import { faChevronRight } from '@fortawesome/free-solid-svg-icons/faChevronRight';
import { SettingsHeaderComponent } from '../../../settings/settings-header/settings-header.component';

@Component({
  selector: 'app-profile-details',
  imports: [
    FormsModule,
    FontAwesomeModule,
    SettingsHeaderComponent
  ],
  templateUrl: './profile-details.component.html'
})
export class ProfileDetailsComponent {
  @Output() changeState = new EventEmitter()

  constructor(protected authService: AuthService) {}

  protected readonly faEnvelope = faEnvelope;
  protected readonly faChevronRight = faChevronRight;
}

import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-settings-sub-header',
  imports: [],
  template: `
    <div class="grid grid-cols-3 py-4 gap-1">
      <p (click)="cancel.emit()">Cancel</p>
      <p class="text-center">{{ title }}</p>
    </div>
  `,
  styles: ``
})
export class SettingsSubHeaderComponent {
  @Input() title: string = '';
  @Output() cancel= new EventEmitter();
}

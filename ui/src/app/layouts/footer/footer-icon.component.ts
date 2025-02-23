import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-footer-icon',
  imports: [],
  template: `
    <button
      type="button"
      class="flex flex-col items-center justify-between cursor-pointer"
      (click)="iconClick.emit()">
      <span class="fill-foreground w-5 h-5" [class.fill-accent]="activated">
        <ng-content></ng-content>
      </span>
    </button>
  `,
  styles: ``
})
export class FooterIconComponent {
  @Input() activated: boolean = false;
  @Output() iconClick = new EventEmitter();
}

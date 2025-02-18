import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-base',
  imports: [
    RouterOutlet
  ],
  templateUrl: './base.component.html',
  styleUrl: './base.component.css'
})
export class BaseComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.self();
  }
}

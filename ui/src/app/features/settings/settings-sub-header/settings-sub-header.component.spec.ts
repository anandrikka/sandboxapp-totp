import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsSubHeaderComponent } from './settings-sub-header.component';

describe('SettingsSubHeaderComponent', () => {
  let component: SettingsSubHeaderComponent;
  let fixture: ComponentFixture<SettingsSubHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SettingsSubHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SettingsSubHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

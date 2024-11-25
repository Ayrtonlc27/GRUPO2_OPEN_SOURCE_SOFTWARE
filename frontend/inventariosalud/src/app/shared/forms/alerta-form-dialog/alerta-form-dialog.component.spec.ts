import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertaFormDialogComponent } from './alerta-form-dialog.component';

describe('AlertaFormDialogComponent', () => {
  let component: AlertaFormDialogComponent;
  let fixture: ComponentFixture<AlertaFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlertaFormDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlertaFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

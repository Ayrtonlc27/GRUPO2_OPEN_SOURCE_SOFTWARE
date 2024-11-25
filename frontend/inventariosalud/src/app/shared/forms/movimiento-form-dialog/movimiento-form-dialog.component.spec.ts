import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoFormDialogComponent } from './movimiento-form-dialog.component';

describe('MovimientoFormDialogComponent', () => {
  let component: MovimientoFormDialogComponent;
  let fixture: ComponentFixture<MovimientoFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovimientoFormDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MovimientoFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

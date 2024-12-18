import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaMedicamentosComponent } from './lista-medicamentos.component';

describe('ListaMedicamentosComponent', () => {
  let component: ListaMedicamentosComponent;
  let fixture: ComponentFixture<ListaMedicamentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaMedicamentosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListaMedicamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

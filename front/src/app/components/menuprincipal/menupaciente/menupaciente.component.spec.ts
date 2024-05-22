import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenupacienteComponent } from './menupaciente.component';

describe('MenupacienteComponent', () => {
  let component: MenupacienteComponent;
  let fixture: ComponentFixture<MenupacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenupacienteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenupacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

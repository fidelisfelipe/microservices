import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentConversionFormComponent } from './conversion-form.component';

describe('ComponentConversionFormComponent', () => {
  let component: ComponentConversionFormComponent;
  let fixture: ComponentFixture<ComponentConversionFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComponentConversionFormComponent]
    });
    fixture = TestBed.createComponent(ComponentConversionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

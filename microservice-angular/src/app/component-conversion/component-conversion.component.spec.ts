import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentConversionComponent } from './component-conversion.component';

describe('ComponentConversionComponent', () => {
  let component: ComponentConversionComponent;
  let fixture: ComponentFixture<ComponentConversionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComponentConversionComponent]
    });
    fixture = TestBed.createComponent(ComponentConversionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

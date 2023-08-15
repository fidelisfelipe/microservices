import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentConversionAlertComponent } from './component-conversion-alert.component';

describe('ComponentConversionAlertComponent', () => {
  let component: ComponentConversionAlertComponent;
  let fixture: ComponentFixture<ComponentConversionAlertComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComponentConversionAlertComponent]
    });
    fixture = TestBed.createComponent(ComponentConversionAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

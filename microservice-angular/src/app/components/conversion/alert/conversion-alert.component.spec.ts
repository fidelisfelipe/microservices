import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionAlertComponent } from './conversion-alert.component';

describe('ComponentConversionAlertComponent', () => {
  let component: ConversionAlertComponent;
  let fixture: ComponentFixture<ConversionAlertComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionAlertComponent]
    });
    fixture = TestBed.createComponent(ConversionAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

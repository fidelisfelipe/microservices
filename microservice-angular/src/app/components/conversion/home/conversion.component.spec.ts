import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionComponent } from './conversion.component';

describe('ComponentConversionComponent', () => {
  let component: ConversionComponent;
  let fixture: ComponentFixture<ConversionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionComponent]
    });
    fixture = TestBed.createComponent(ConversionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

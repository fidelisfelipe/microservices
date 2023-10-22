import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionAddComponent } from './conversion-add.component';

describe('ConversionAddComponent', () => {
  let component: ConversionAddComponent;
  let fixture: ComponentFixture<ConversionAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionAddComponent]
    });
    fixture = TestBed.createComponent(ConversionAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

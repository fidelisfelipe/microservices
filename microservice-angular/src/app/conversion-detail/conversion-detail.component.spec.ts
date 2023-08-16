import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionDetailComponent } from './conversion-detail.component';

describe('ConversionDetailComponent', () => {
  let component: ConversionDetailComponent;
  let fixture: ComponentFixture<ConversionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionDetailComponent]
    });
    fixture = TestBed.createComponent(ConversionDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

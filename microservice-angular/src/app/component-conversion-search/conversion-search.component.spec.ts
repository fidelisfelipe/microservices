import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionSearchComponent } from './conversion-search.component';

describe('ConversionSearchComponent', () => {
  let component: ConversionSearchComponent;
  let fixture: ComponentFixture<ConversionSearchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionSearchComponent]
    });
    fixture = TestBed.createComponent(ConversionSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

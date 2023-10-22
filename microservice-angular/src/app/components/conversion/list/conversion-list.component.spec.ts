import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionListComponent } from './conversion-list.component';

describe('ConversionListComponent', () => {
  let component: ConversionListComponent;
  let fixture: ComponentFixture<ConversionListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionListComponent]
    });
    fixture = TestBed.createComponent(ConversionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

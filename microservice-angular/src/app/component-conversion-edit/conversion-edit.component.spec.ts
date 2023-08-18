import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionEditComponent } from './conversion-edit.component';

describe('ConversionDetailComponent', () => {
  let component: ConversionEditComponent;
  let fixture: ComponentFixture<ConversionEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionEditComponent]
    });
    fixture = TestBed.createComponent(ConversionEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

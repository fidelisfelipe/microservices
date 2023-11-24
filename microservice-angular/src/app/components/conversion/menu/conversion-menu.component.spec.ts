import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionMenuComponent } from './conversion-menu.component';

describe('MenuComponent', () => {
  let component: ConversionMenuComponent;
  let fixture: ComponentFixture<ConversionMenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionMenuComponent]
    });
    fixture = TestBed.createComponent(ConversionMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

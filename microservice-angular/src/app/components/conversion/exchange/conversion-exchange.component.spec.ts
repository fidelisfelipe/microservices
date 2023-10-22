import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionExchangeComponent } from './conversion-exchange.component';

describe('ExchangeComponent', () => {
  let component: ConversionExchangeComponent;
  let fixture: ComponentFixture<ConversionExchangeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConversionExchangeComponent]
    });
    fixture = TestBed.createComponent(ConversionExchangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

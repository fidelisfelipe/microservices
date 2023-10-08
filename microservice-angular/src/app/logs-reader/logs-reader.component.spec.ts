import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogsReaderComponent } from './logs-reader.component';

describe('LogsReaderComponent', () => {
  let component: LogsReaderComponent;
  let fixture: ComponentFixture<LogsReaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LogsReaderComponent]
    });
    fixture = TestBed.createComponent(LogsReaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

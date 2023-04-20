import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventManagerComponent } from './event-manager.component';

describe('EventManagerComponent', () => {
  let component: EventManagerComponent;
  let fixture: ComponentFixture<EventManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventManagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

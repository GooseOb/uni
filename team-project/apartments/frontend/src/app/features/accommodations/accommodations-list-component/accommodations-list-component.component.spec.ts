import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationsListComponentComponent } from './accommodations-list-component.component';

describe('AccommodationsListComponentComponent', () => {
  let component: AccommodationsListComponentComponent;
  let fixture: ComponentFixture<AccommodationsListComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccommodationsListComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccommodationsListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

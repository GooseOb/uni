import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationShowComponent } from './accommodation-show.component';

describe('AccommodationShowComponent', () => {
  let component: AccommodationShowComponent;
  let fixture: ComponentFixture<AccommodationShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccommodationShowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccommodationShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

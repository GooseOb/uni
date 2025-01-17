import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHostAccommodationsComponent } from './list-host-accommodations.component';

describe('ListHostAccommodationsComponent', () => {
  let component: ListHostAccommodationsComponent;
  let fixture: ComponentFixture<ListHostAccommodationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListHostAccommodationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListHostAccommodationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

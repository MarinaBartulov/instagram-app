import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPostDetailsComponent } from './user-post-details.component';

describe('UserPostDetailsComponent', () => {
  let component: UserPostDetailsComponent;
  let fixture: ComponentFixture<UserPostDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPostDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPostDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

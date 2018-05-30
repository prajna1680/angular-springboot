import { Restaurant } from './restaurant';
import { TestBed, inject } from '@angular/core/testing';
import { RestaurantService } from './restaurant.service';

describe('Restaurant', () => {

  beforeEach(() => {
    console.log("inside the setup of testbed");
    TestBed.configureTestingModule({
      providers: [RestaurantService]
    });
  });

  it('should accept values in the constructor', () => {
    console.log("inside original test");
    let rest = new Restaurant(34, 'as', 'bangalore', 'indian', 4);
    expect(rest.id).toBe(34);
    expect(rest.name).toBe('as');
    expect(rest.location).toBe('bangalore');
    expect(rest.cuisine).toBe('indian');
    expect(rest.rating).toBe(4);
  });

});

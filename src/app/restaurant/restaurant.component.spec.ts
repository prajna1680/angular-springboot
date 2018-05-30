import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { RestaurantService } from '../restaurant.service';
import { RestaurantComponent } from './restaurant.component';
import { Router } from '@angular/router';
import { MatButtonModule, MatFormFieldModule,  MatInputModule } from '@angular/material';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Restaurant } from '../restaurant';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { By } from '@angular/platform-browser';
class RestaurantServiceMock {
  public getRestaurants(): Observable<Restaurant[]> {
    const rests: Restaurant[] = new Array<Restaurant>(
      new Restaurant(1,"c","c","c",1),
      new Restaurant(2,"c","c","c",1)
    );
    return Observable.of(rests);
  }
}
describe('RestaurantComponent', () => {
  let component: RestaurantComponent;
  let fixture: ComponentFixture<RestaurantComponent>;
  let http:HttpClient;
  let router: Router;
  let service = new RestaurantService(http);
  let restaurantComponent = new RestaurantComponent(service,router);
  let restaurantServiceStub: Partial<RestaurantService>;
let routerSpy: Partial<Router>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantComponent ],
      imports:[HttpClientModule, 
               BrowserAnimationsModule, 
              MatButtonModule,
              MatFormFieldModule,
              MatInputModule,
              MatExpansionModule,
              MatTableModule,
              MatCardModule,
              MatGridListModule,
              MatToolbarModule],
      providers:[{provide: RestaurantService, useValue: restaurantServiceStub },
                 {provide: Router,  useValue: routerSpy}],
      schemas: [CUSTOM_ELEMENTS_SCHEMA,NO_ERRORS_SCHEMA]
      
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  
  it('should create', () => {
    expect(component).toBeTruthy();
  });

 it('should add restaurant', async() => {
  const restElements = fixture.debugElement.query(By.css("#id")); //dom ele
  const id=restElements.nativeElement; //tag ele
fixture.whenStable().then(()=>{
  id.value=1;
  console.log("rest added");
  expect(restElements).toBe(1);
});
  //  let data = new Restaurant(1,'s','s','s',1);
  //  let responseadd = service.addRestaurant(data);
  //  expect(responseadd).toBe({"id":1,"name":'s',"location":'s',"cuisine":'s',"rating":1});

    // restaurantServiceStub = TestBed.get(RestaurantService);
    // expect(restaurantServiceStub).toBeTruthy;
   });

});

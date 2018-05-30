import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { Headers, Response, RequestOptions } from '@angular/http'
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { Restaurant } from './restaurant';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class RestaurantService {

  data: any;

  private apiURL = "http://localhost:8080/api";
  private headers = new Headers({ 'Content-Type': 'application/json' });
  // queryUrl: string = '?search=';
  private searchurl = "http://localhost:8080/api/search/";

  // private options = new RequestOptions({headers:this.headers});


  private restaurant: Restaurant;

  constructor(private http: HttpClient) { }

  // searchHeroes(term: string): Observable<Restaurant[]> {
  //   if (!term.trim()) {
  //     // if not search term, return empty hero array.
  //     return null;
  //   }
  //   return this.http.get<Restaurant[]>(`${this.apiURL}/?name=${term}`).pipe(
  //     tap(`found heroes matching "${term}"`)
  //     // catchError(this.handleError<Restaurant[]>('searchHeroes', []))
  //   );
  // }

  searchHeroes(term: string){
    return this.http.get(this.searchurl + term)
    .map((response: Response) => {
      console.log(response);
      return response;
      //.json;
      });
    }
      // return this.http.get(this.searchurl + term).map((res:Response) =>
      //   res.json)
      // );
   
    // return this.http.get(`${this.apiURL}/search/${term$}`).map((response: Response) => response.json);
    // return this.http
    //   .get(`${this.apiURL}/search/${term}`)
    //   .map((response: Response) => {
    //     this.data = response.json;
    //     console.log("data is ",response.json);
      
    //   });
  

  getRestaurants() {
    return this.http.get(`${this.apiURL}/restaurants`);
  }

  addRestaurant(restaurant: Restaurant) {
    return this.http.post(`${this.apiURL}/restaurant`, restaurant).map((response: Response) => {response.json;
    console.log(response.json);
    });
  }

  // getRestaurantId(id: Number){
  //   return this.http.get(`${this.apiURL}/restaurant/`+id).map((response:Response)=>response.json);
  // }

  deleteRestaurant(id: Number) {
    return this.http.delete(`${this.apiURL}/restaurant/` + id);//.map((response:Response)=>response.json);
  }

  updateRestaurant(restaurant: Restaurant) {
    let id = restaurant.id;
    return this.http.put(`${this.apiURL}/restaurant/${id}`, restaurant).map((response: Response) => {response.json;
    console.log(response);
    });
  }

  setter(restaurant: Restaurant) {
    this.restaurant = restaurant;
  }

  getter() {
    return this.restaurant;
  }

}


//working
// return this.http.get<Observable<Restaurant>>(this.searchurl + term).map((response: Response) => {response.json;
//console.log(response);
//});

// this.searchTerm.
// valueChanges
// .debounceTime(400)
// // .distinctUntilChanged()
// .map(data => this.restService.searchHeroes(data))
// .subscribe(response => console.log(response));

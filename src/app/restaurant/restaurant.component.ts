import { Component, OnInit, ViewChild } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { Restaurant } from '../restaurant';
import { Router, ExtraOptions } from '@angular/router';
import { DataSource } from '@angular/cdk/table';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap, merge, filter } from 'rxjs/operators';
import { map } from 'rxjs/operators';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { FormControl } from '@angular/forms';
import {NgControl} from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import { serializePath } from '@angular/router/src/url_tree';


@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {
  restaurants: any;
  id: number;
  name: string = '';
  location: string = '';
  cuisine: string = '';
  rating: number;
  results:any = [];
  private searchTerms = new Subject<string>();
  values: any;
  searchTerm: FormControl = new FormControl();

  searchResult: any;

  constructor(private restService: RestaurantService, private router: Router) { 
    this.searchTerm.
    valueChanges
    .debounceTime(400)
    .subscribe(data => {
      this.restService.searchHeroes(data).subscribe(data=>{
        this.searchResult=data;
      sessionStorage.setItem("results",this.searchResult);      
     console.log(this.searchResult);
      })
     
    });

  }

//  this.searchTerm.
// valueChanges
// .debounceTime(400)
// // .distinctUntilChanged()
// .map(data => this.restService.searchHeroes(data))
// .subscribe(response => console.log(response));

  //   this.searchdata.valueChanges.distinctUntilChanged().
  //   debounceTime(500)
  //    .subscribe(responsedata =>{this.restService.searchHeroes(responsedata).subscribe(response=>{
  //      this.results = response
  //    });
  //    });
  //  console.log(this.results);
  // }


   
 

   ngOnInit(): void {
    this.restService.getRestaurants().subscribe(responseRestaurants => this.restaurants = responseRestaurants);
    // this.results = this.searchTerms.pipe(
    //   debounceTime(500),
    //   distinctUntilChanged(),
    //   switchMap((term: string) => this.restService.searchHeroes(term)),);
    // console.log(this.searchTerms);
  }

  addRestaurant() {
    let restaurant = new Restaurant(this.id, this.name, this.location, this.cuisine, this.rating);
    this.restService.addRestaurant(restaurant).subscribe(res => { this.restaurants = res; this.ngOnInit() });
  }

  deleteRestaurant(id) {
    this.restService.deleteRestaurant(id).subscribe(
      next => this.ngOnInit());
  }

  updateRestaurant(restaurant) {
    this.restService.setter(restaurant);
    this.router.navigate(['/op']);
    //this.restService.updateRestaurant(this.restaurants.id).subscribe(res=>this.restaurants=res);
  }

  addFavt(restaurant) {
    let list: Array<Restaurant>;
    list.push(restaurant);
    console.log(list.pop.toString);
  }
}

// for tables
    // export class RestaurantDataSource extends DataSource<any> {
    //   constructor(private restService:RestaurantService){
    //     super();
    //   }
    //   connect(): Observable<any> {
    //     return this.restService.getRestaurants();
    //   }
    //   disconnect(){}
    // }
// addRestaurant(){
    //   let restaurant = new Restaurant();
    //   this.restService.setter(restaurant);
    //   this.router.navigate(['/op']);
    //   //this.restService.updateRestaurant(this.restaurants.id).subscribe(res=>this.restaurants=res);
    // }

    //   search = (text$: Observable<string>) =>
  //   text$.pipe(
  //     debounceTime(400),
  //     distinctUntilChanged(),
	// //  merge(this.focus$),
	// //  merge(this.click$.pipe(filter(() => !this.instance.isPopupOpen()))),
  // //      map(term => (term = this.restService.searchHeroes(term).subscribe(response=>this.restaurants=response)));
  //  map(term =>  this.restService.searchHeroes(term).subscribe(response=>this.restaurants=response)));


  // search(term: string): void {
  //   this.searchTerms.next(term);
  //   //console.log(term);
  // }

  // onSearchChange(searchValue : string ) { 
  //   this.restaurants = this.searchTerms.pipe( 
  //   debounceTime(500),distinctUntilChanged(),
  //   switchMap((term: string) => this.restService.searchHeroes(term)));
  //   this.restService.searchHeroes(searchValue).subscribe(responsedata =>this.results=responsedata);
  //  console.log(this.results);
  // }
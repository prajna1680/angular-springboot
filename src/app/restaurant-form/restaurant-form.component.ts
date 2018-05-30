import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';
import { Router } from '@angular/router';
import { Location} from '@angular/common';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {
 restaurant: Restaurant;

  constructor(private restService: RestaurantService, private router: Router, private location: Location) { }

  ngOnInit() {
    this.restaurant= this.restService.getter();
  }

  processForm(){
    // if(this.restaurant.id=undefined){
    //   this.restService.addRestaurant(this.restaurant).subscribe((restaurant)=>this.router.navigate(['/']));  
    // }
    // else{
      this.restService.updateRestaurant(this.restaurant).subscribe((rest)=>this.router.navigate(['/home']));
  }

  back(){
    this.location.back(); 
  }


}

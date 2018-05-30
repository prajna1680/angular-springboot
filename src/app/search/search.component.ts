import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private route: ActivatedRoute,private restService: RestaurantService) { }

  ngOnInit() {
 let name = this.route.snapshot.paramMap.get('s.name');
 console.log(name);
 
  let displayResult = sessionStorage.getItem("results");
  console.log(displayResult);
  let list: string[] = [];

 let  result=  this.restService.searchHeroes(name).subscribe();
  }

  

}

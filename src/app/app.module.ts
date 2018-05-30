import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, ExtraOptions } from '@angular/router';

import { AppComponent } from './app.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { RestaurantService } from './restaurant.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RestaurantFormComponent } from './restaurant-form/restaurant-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTabsModule, MatTab, MatTabGroup } from '@angular/material/tabs';
import { FlexLayoutModule } from "@angular/flex-layout";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { SearchComponent } from './search/search.component';
import {NgbTypeahead, NgbTypeaheadModule, NgbTypeaheadSelectItemEvent } from '@ng-bootstrap/ng-bootstrap/';
import {NgControl} from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';


const appRoutes: Routes = [
  { path: 'home', component: RestaurantComponent },
  { path: 'op', component: RestaurantFormComponent },
  { path: 'search/:s.name', component: SearchComponent }

];
@NgModule({
  declarations: [
    AppComponent,
    RestaurantComponent,
    RestaurantFormComponent,
    SearchComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, { onSameUrlNavigation: 'reload' }),
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatExpansionModule,
    MatTableModule,
    MatCardModule,
    MatGridListModule,
    MatToolbarModule,
    MatTableModule,
    FlexLayoutModule,
    NgbTypeaheadModule,
    NgbModule.forRoot(),
    ReactiveFormsModule,
    MatAutocompleteModule

  ],
  providers: [RestaurantService],
  bootstrap: [AppComponent]
})
export class AppModule { }

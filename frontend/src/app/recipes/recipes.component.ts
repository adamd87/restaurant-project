import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html'
})
export class RecipesComponent implements OnInit {



  ngOnInit(): void {
  }
  constructor(private httpClient: HttpClient, private router: Router) {
  }


}

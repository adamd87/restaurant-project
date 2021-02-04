import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent {

  constructor(private httpClient: HttpClient, private router: Router) {
  }

}

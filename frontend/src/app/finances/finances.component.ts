import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-finances',
  templateUrl: './finances.component.html'
})
export class FinancesComponent implements OnInit {


  ngOnInit(): void {
  }
  constructor(private httpClient: HttpClient, private router: Router) {
  }


}

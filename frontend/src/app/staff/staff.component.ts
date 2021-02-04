import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html'
})
export class StaffComponent implements OnInit {



  ngOnInit(): void {
  }
  constructor(private httpClient: HttpClient, private router: Router) {
  }

}

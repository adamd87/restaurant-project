import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Menu} from './menu';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  private url = 'http://localhost:8080/menu';
  menuItems: Menu [] = [];

  newMenuItem: Menu = {
    name: '',
    price: null
  };

  constructor(private httpClient: HttpClient, private router: Router) { }

  loadMenu(): void{
    this.httpClient.get<Menu[]>(this.url + '/get-all')
      .subscribe(menuItems => this.menuItems = menuItems);
  }

  ngOnInit(): void {
    this.loadMenu();
  }

}

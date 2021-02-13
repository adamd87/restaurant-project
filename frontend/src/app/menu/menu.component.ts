import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Menu} from './menu';
import {Product} from '../stockroom/product';
import {ProductValidationErrors} from '../stockroom/product-validation-errors';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  private menuUrl = 'http://localhost:8080/menu';
  private orderUrl = 'http://localhost8080/order';
  menuItems: Menu [] = [];

  newMenuItem: Menu = {
    name: '',
    price: null
  };
  // validationErrors: ProductValidationErrors = {};
  // submitted = false;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  loadMenu(): void {
    this.httpClient.get<Menu[]>(this.menuUrl + '/get-all')
      .subscribe(menuItems => this.menuItems = menuItems);
    this.newMenuItem.name = '';
    this.newMenuItem.price = null;
  }

  ngOnInit(): void {
    this.loadMenu();
  }

  getMenuItem(name: string): void {
    this.httpClient.get<Menu[]>(this.menuUrl + '/by-name/' + name)
      .subscribe(menuItems => this.menuItems = menuItems);
  }
}

import {Component, OnInit} from '@angular/core';
import {Product} from './product';
import {ProductValidationErrors} from './product-validation-errors';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-stockroom',
  templateUrl: './stockroom.component.html'
})
export class StockroomComponent implements OnInit {

  private url = 'http://localhost:8080/products';
  products: Product [] = [];

  newProduct: Product = {
    name: '',
    weight: null,
    cost: null
  };
  validationErrors: ProductValidationErrors = {};
  submitted = false;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  loadProducts(): void {
    this.httpClient.get<Product[]>(this.url + '/get-all')
      .subscribe(products => this.products = products);
    this.newProduct.name = '';
    this.newProduct.weight = null;
    this.newProduct.cost = null;
    this.submitted = false;
  }

  loadOneProduct(name: string): void {
    this.httpClient.get<Product[]>(this.url + '/by-name/' + name)
      .subscribe(products => this.products = products);
    this.submitted = false;
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  createProduct(): void {
    this.httpClient.post(this.url, this.newProduct)
      .subscribe(() => this.loadProducts(),
        errorResponse => {
          this.submitted = true;
          this.validationErrors = errorResponse.error;
        });
  }

  delete(id?: number): void {
    this.httpClient.delete(this.url + '/' + id)
      .subscribe(() => {
          alert('The product has been removed!');
          this.loadProducts();
        }
      );
  }

  updateProduct(name: string): void {
    this.httpClient.patch(this.url + '/' + name, this.newProduct)
      .subscribe(() => this.loadOneProduct(this.newProduct.name));
    this.submitted = false;
  }


}

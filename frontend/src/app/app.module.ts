import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {StockroomComponent} from './stockroom/stockroom.component';
import {Router, RouterModule, Routes} from '@angular/router';
import {AuthorizationInterceptor} from './authorization.interceptor';
import {WelcomeComponent} from './welcome/welcome.component';
import {MainComponent} from './main/main.component';
import {RecipesComponent} from './recipes/recipes.component';
import {StaffComponent} from './staff/staff.component';
import {FinancesComponent} from './finances/finances.component';
import { MenuComponent } from './menu/menu.component';

const routes: Routes = [{
  path: '',
  component: WelcomeComponent
}, {
  path: 'main',
  component: MainComponent
}, {
  path: 'main/stockroom',
  component: StockroomComponent
}, {
  path: 'main/recipes',
  component: RecipesComponent
}, {
  path: 'main/menu',
  component: MenuComponent
}, {
  path: 'main/finances',
  component: FinancesComponent
}, {
  path: 'main/staff',
  component: StaffComponent
}];

@NgModule({
  declarations: [
    AppComponent,
    StockroomComponent,
    WelcomeComponent,
    MainComponent,
    RecipesComponent,
    StaffComponent,
    FinancesComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],

  providers: [{
    useClass: AuthorizationInterceptor,
    multi: true,
    provide: HTTP_INTERCEPTORS
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
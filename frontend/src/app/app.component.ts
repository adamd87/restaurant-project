import {Component, HostListener, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoggedUser} from './logged-user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  username: string;
  password: string;
  loggedUser: LoggedUser = null;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.login();
  }


  onLoginFormSubmit(): void {
    this.storeAuthorizationHeaderInLocalStorage();
    this.login(() => {
      alert('Invalid username or password!');
      this.username = '';
      this.password = '';
    });
  }

  private storeAuthorizationHeaderInLocalStorage(): void {
    const authorizationHeader = 'Basic ' + btoa(this.username + ':' + this.password);
    localStorage.setItem('authorizationHeader', authorizationHeader);
  }

  private login(errorCallback?: () => void): void {
    this.httpClient.post<LoggedUser>('http://localhost:8080/login', {})
      .subscribe(loggedUser => {
          this.router.navigate(['']);
          this.loggedUser = loggedUser;
        },
        () => {
          if (errorCallback) {
            errorCallback();
          }
        });
  }

  // @HostListener('window:beforeunload')
  // onWindowUnload(): void {
  //   localStorage.removeItem('authorizationHeader');
  // }

  logout(): void {
    localStorage.removeItem('authorizationHeader');
    this.router.navigate([''])
      .then(() => location.reload());
  }
}

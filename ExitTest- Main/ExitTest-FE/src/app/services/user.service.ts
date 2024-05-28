import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { HostListener, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, switchMap, throwError } from 'rxjs';
import Swal from 'sweetalert2';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  [x: string]: any;

  API = "http://localhost:8080";
  requestHeader = new HttpHeaders({ "No-Auth": "True" });

  constructor(private http: HttpClient, private router: Router) { }

  public login(loginData: any) {
    return this.http.post(this.API + '/auth/login', loginData, { headers: this.requestHeader }).pipe(
      switchMap((response: any) => {
        const jwt = response.jwt;
        const loggedInUserUrl = this.API + '/auth/LoggedInUser';
        const loggedInUserHeaders = new HttpHeaders({
          'Authorization': `Bearer ${jwt}`
        });

        const loggedInUserRequest = this.http.get(loggedInUserUrl, { headers: loggedInUserHeaders });
        return loggedInUserRequest.pipe(
          map((loggedInUserResponse: any) => {
            const userData = {
              jwt: jwt,
              message: response.message,
              usersStructure: loggedInUserResponse
            };
            // Save user data to localStorage
            localStorage.setItem('userData', JSON.stringify(userData));
            if (response.message === 'Login Success') {
              this.router.navigate(['/']);
            }
            return userData;
          })
        );
      }),
      catchError((error: any) => {
        if (error instanceof HttpErrorResponse && error.status === 400) {
          // Bad Request: wrong credentials
          Swal.fire("Error", "Either Username Or Password is Incorrect")
        }
        return throwError(error);
      })
    );
  }

  public isLoggedIn(): boolean {
    // Check if user data is present in localStorage
    return !!localStorage.getItem('userData');
  }

  public logout():any{
    // Clear user data from localStorage
    localStorage.removeItem('userData');
    // Redirect to home
    this.router.navigate(['/']);
  }

  @HostListener('window:beforeunload')
  clearLocalStorage() {
    this.logout();
  }

  register(userData: any){
    return this.http.post(this.API + '/auth/register', userData, { headers: this.requestHeader });
  }

  
}

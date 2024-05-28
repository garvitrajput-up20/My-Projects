import { Component, HostListener, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  protected isLoggedIn = false;
  loggedInUserName: string = '';

  constructor(private router: Router, private userService: UserService, private authService: AuthService) { }


  ngOnInit(): void {
    this.checkLoginStatus();
    const userData = localStorage.getItem('userData');
    const parsedData = userData ? JSON.parse(userData) : null;
    this.loggedInUserName = parsedData && parsedData.usersStructure ? parsedData.usersStructure.name : '';
  }

  checkLoginStatus() {
    this.isLoggedIn = this.userService.isLoggedIn();
    this.authService.setLoggedInStatus(this.isLoggedIn);

  }

  logout() {
    this.userService.logout();
    this.isLoggedIn = false;
    this.authService.setLoggedInStatus(false);
    this.router.navigate(['/']);
  }

  @HostListener('window:beforeunload')
  clearLocalStorage() {
    this.logout();
  }

}

import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-userregister',
  templateUrl: './userregister.component.html',
  styleUrls: ['./userregister.component.scss']
})
export class UserregisterComponent {
  

  constructor(private userService: UserService, private router:Router) { }


  register(registerForm:NgForm) {
    // Call the register function from the UserService
    this.userService.register(registerForm.value).subscribe(
      (response) => {
        // Handle successful registration

        console.log('Registration successful');
        console.log(registerForm.value);
        alert("Registration successful");
        this.router.navigate(['/login']);
        // Redirect to the login page or perform other actions
      },
      (error) => {
        // Handle registration error
        console.error('Registration failed:', error);
        // Display an error message or perform other actions
        alert("Registration Failed");
      }
    );
  }
}
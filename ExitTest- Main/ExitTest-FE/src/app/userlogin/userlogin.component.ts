import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-userlogin',
  templateUrl: './userlogin.component.html',
  styleUrls: ['./userlogin.component.scss']
})
export class UserloginComponent implements OnInit{

  constructor(private userService: UserService) { }

  ngOnInit(): void {
      
  }
  
  login(loginForm:NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response)=>{
        console.log(response)
      },
      (error)=>{
        console.log(error)
      }
    );
  }

}

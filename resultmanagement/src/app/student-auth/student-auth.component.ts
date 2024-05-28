import { Component, OnInit } from '@angular/core';
import { StudentService } from '../services/student.service';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-student-auth',
  templateUrl: './student-auth.component.html',
  styleUrls: ['./student-auth.component.css']
})
export class StudentAuthComponent implements OnInit{

constructor(private studentService : StudentService, private router:Router){}

ngOnInit(): void {
    
}


  

login(data:any):void{
  console.warn(data);
  this.studentService.login(data);
}


}

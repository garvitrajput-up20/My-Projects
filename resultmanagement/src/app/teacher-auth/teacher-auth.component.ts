import { Component, OnInit } from '@angular/core';
import { TeacherService } from '../services/teacher.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teacher-auth',
  templateUrl: './teacher-auth.component.html',
  styleUrls: ['./teacher-auth.component.css']
})
export class TeacherAuthComponent implements OnInit {
  constructor(private teacher:TeacherService,  private router:Router){}

  ngOnInit(): void {
      
  }

  login(data:any):void{
    console.warn(data);
    this.teacher.login(data);
  }

}

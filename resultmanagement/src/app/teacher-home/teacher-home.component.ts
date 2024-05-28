import { Component, OnInit } from '@angular/core';
import { TeacherService } from '../services/teacher.service';
import { Router } from '@angular/router';
import { student } from 'data-type';

@Component({
  selector: 'app-teacher-home',
  templateUrl: './teacher-home.component.html',
  styleUrls: ['./teacher-home.component.css']
})
export class TeacherHomeComponent implements OnInit {
  fetchStudent:undefined | student[]
  constructor(private teacher:TeacherService,  private router:Router){}

  viewStudent = false;

  ngOnInit(): void {
    this.list();
  }

  addStudent(data:any):void{
    console.warn(data);
    this.teacher.addStudent(data);
    this.list();
  }

  ViewStudents(){
    this.viewStudent=true;
  }

  AddForm(){
    this.viewStudent = false;
  }

  logout(){
    localStorage.removeItem('teacher');
    this.router.navigate(['']);
  }

  delete(id:number){
    this.teacher.deleteStudent(id);
    this.list();
  }

  list(){
    this.teacher.fetchStudent().subscribe((result)=>{
      console.log(result);
      this.fetchStudent = result;
    })
  }



}

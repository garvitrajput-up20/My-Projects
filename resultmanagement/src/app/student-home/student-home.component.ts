import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { student } from 'data-type';

@Component({
  selector: 'app-student-home',
  templateUrl: './student-home.component.html',
  styleUrls: ['./student-home.component.css']
})
export class StudentHomeComponent implements OnInit {
  studentData!: student;
  constructor(private route:ActivatedRoute) { }
  ngOnInit():void {
    this.route.params.subscribe((params:Params)=>{
     
        if(localStorage.getItem('student')){
          let studentStore = localStorage.getItem('student');
          let studentData = studentStore && JSON.parse(studentStore);
          this.studentData = studentData[0];
          console.warn(this.studentData);
        }
      });
    };
  
}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TeacherService } from '../services/teacher.service';
import { student } from 'data-type';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})
export class EditStudentComponent implements OnInit {

  studentData!: student;

  constructor(private route: ActivatedRoute, private teacher: TeacherService) { 
    this.studentData = {
      "rollNo": "",
      "name": "",
      "dob": "",
      "score": "",
      "id": 0
    }
  }
  ngOnInit(): void {
    let studentId = this.route.snapshot.paramMap.get('id');
    studentId && this.teacher.getStudent(studentId).subscribe((data) => {
      console.log(data);
      this.studentData = data;
    })
  }

  editStudent() {
    console.log(this.studentData);
    if(this.studentData)
    this.teacher.updateStudent(this.studentData);
  }

}

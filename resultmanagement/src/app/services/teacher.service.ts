import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { student } from 'data-type';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {


  constructor(private http: HttpClient, private router: Router) { }
  isTeacherLoggedIn = new BehaviorSubject<boolean>(false);
  registerTeacher(data: any) {
    this.http.post('http://localhost:3000/teacher', data, { observe: 'response' }).subscribe((result) => {
      this.isTeacherLoggedIn.next(true);
      this.router.navigate(['teacher-home']);
      localStorage.setItem('teacher', JSON.stringify(result.body))
      console.log('result', result);
    });
    return false;
  }

  reloadTeacher() {
    if (localStorage.getItem('teacher')) {
      this.isTeacherLoggedIn.next(true);
      this.router.navigate(['teacher-home']);
    }
  }


  login(data: any) {
    console.warn(data)
    this.http.get(`http://localhost:3000/teacher?username=${data.username}&password=${data.password}`,
      { observe: 'response' })
      .subscribe((result: any) => {
        console.warn(result);
        if (result && result.body && result.body.length) {
          console.warn("Teacher Logged In")
          this.isTeacherLoggedIn.next(true);
          this.router.navigate(['teacher-home']);
          localStorage.setItem('teacher', JSON.stringify(result.body))
        } else {
          console.warn("Teacher Login Failed");
          alert("Invalid Credentials")
        }
      });
    return false;
  }

  addStudent(data: any) {
    this.http.post('http://localhost:3000/student', data, { observe: 'response' }).subscribe((result) => {
      console.log('result', result);
      if (result) {
        alert('Student Added Successfully')
      } else {
        alert('Student Adding Failed');
      }
    });
  }

  fetchStudent() {
    return this.http.get<student[]>('http://localhost:3000/student')
  }

  deleteStudent(id: number) {
    this.http.delete(`http://localhost:3000/student/${id}`).subscribe((result) => {
      if (result) {
        alert('Student Deleted Successfully');
      } else {
        alert('Student Deleting Failed');
      }
    })
  }

  getStudent(id: string) {
    return this.http.get<student>(`http://localhost:3000/student/${id}`)
  }


  updateStudent(studentData:student){
    return this.http.put<student>(`http://localhost:3000/student/${studentData.id}`, studentData).subscribe((result)=>{
      if(result){
        alert('Student Updated Successfully');
      }else{
        alert('Student Updating Failed');
      }
  })
  }
}

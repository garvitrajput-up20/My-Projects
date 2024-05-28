import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  isStudentLoggedIn = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient, private router: Router)  { }


  reloadStudent() {
    if (localStorage.getItem('student')) {
      this.isStudentLoggedIn.next(true);
      this.router.navigate(['student-home']);
    }
  }

  login(data: any) {
    console.warn(data)
    this.http.get(`http://localhost:3000/student?rollNo=${data.rollNo}&dob=${data.dob}`,
      { observe: 'response' })
      .subscribe((result: any) => {
        console.warn(result);
        if (result && result.body && result.body.length) {
          console.warn("Student Logged In")
          this.isStudentLoggedIn.next(true);
          this.router.navigate(['student-home']);
          localStorage.setItem('student', JSON.stringify(result.body))
        } else {
          console.warn("Student Login Failed");
          alert("Invalid Credentials")
        }
      });
    return false;
  }
}

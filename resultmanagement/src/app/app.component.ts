import { Component } from '@angular/core';
import { TeacherService } from './services/teacher.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'resultmanagement';
  constructor(private teacher:TeacherService){
    
  }
  ngOnInit():void{
    this.teacher.reloadTeacher();
  }
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TeacherAuthComponent } from './teacher-auth/teacher-auth.component';
import { HomeComponent } from './home/home.component';
import { StudentAuthComponent } from './student-auth/student-auth.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { StudentHomeComponent } from './student-home/student-home.component';

@NgModule({
  declarations: [
    AppComponent,
    TeacherAuthComponent,
    HomeComponent,
    StudentAuthComponent,
    TeacherHomeComponent,
    EditStudentComponent,
    StudentHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TeacherAuthComponent } from './teacher-auth/teacher-auth.component';
import { StudentAuthComponent } from './student-auth/student-auth.component';
import { TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { AuthGuard } from './auth.guard';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { StudentHomeComponent } from './student-home/student-home.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'teacher-auth',
    component:TeacherAuthComponent
  },
  {
    path:'student-auth',
    component:StudentAuthComponent
  },
  {
    path:'teacher-home',
    component:TeacherHomeComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'edit-student/:id',
    component:EditStudentComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'student-home',
    component:StudentHomeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

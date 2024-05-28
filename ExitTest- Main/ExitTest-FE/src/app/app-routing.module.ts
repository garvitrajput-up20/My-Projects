import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserloginComponent } from './userlogin/userlogin.component';
import { UserregisterComponent } from './userregister/userregister.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth.guard';
import { SearchResultsComponent } from './search-results/search-results.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: UserloginComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'register',
    component: UserregisterComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'search-results',
    component: SearchResultsComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

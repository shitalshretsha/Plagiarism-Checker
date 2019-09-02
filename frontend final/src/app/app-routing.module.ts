import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { TeamComponent } from './team/team.component';
import { ContactsComponent } from './contacts/contacts.component';
import {LoginComponent} from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StudentComponent } from './student/student.component';

const routes: Routes = [
  {path: '',redirectTo: '/home',pathMatch:'full'},
  {path:'home',component: HomeComponent},
  {path:'about',component: AboutComponent},
  {path:'team',component: TeamComponent},
  {path:'contacts',component: ContactsComponent},
  {path:'login',component: LoginComponent},
  {path:'dashboard',component: DashboardComponent},
  {path:'student', component:StudentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent=[HomeComponent,AboutComponent,ContactsComponent,LoginComponent,TeamComponent,DashboardComponent]
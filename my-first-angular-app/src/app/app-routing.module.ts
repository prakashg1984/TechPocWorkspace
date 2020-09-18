import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { WelcomeComponent } from './welcome/welcome.component';
import { HelloComponent } from './hello/hello.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent },
  { path: 'hello', component: HelloComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/welcome', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

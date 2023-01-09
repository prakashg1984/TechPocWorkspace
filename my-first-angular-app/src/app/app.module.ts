
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { LogService } from './log.service';
import { RegisterService } from './register/register.service';

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    WelcomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, AppRoutingModule
  ],
  providers: [LogService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }

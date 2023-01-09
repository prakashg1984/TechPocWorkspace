import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hello',
  templateUrl: './hello.component.html',
  styleUrls: ['./hello.component.css']
})
export class HelloComponent implements OnInit {

  name: string = "Default";

  constructor(private router: Router) {
    this.name = sessionStorage.getItem('loggedInUser');
  }

  ngOnInit(): void {
  }

  logOut() {
    sessionStorage.removeItem('loggedInUser');
    this.router.navigate(['/welcome']);
  }
}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginService } from './login.service';
import { FormsModule, ReactiveFormsModule }         from '@angular/forms';
import { Router, NavigationExtras }  from '@angular/router';
import { Login } from './Login';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService:LoginService;
  let router: Router;

  class LoginServiceStub {
    checkUser(x:Login) :boolean {
      return true;
    }; 
  };

  class RouterStub {
    navigate(commands: any[], extras?: NavigationExtras) { };
  };

  let loginServiceStub:LoginServiceStub = new LoginServiceStub();
  let routerStub:RouterStub = new RouterStub();


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports:[ ReactiveFormsModule, FormsModule ],
      providers: [{provide: LoginService, useValue:loginServiceStub}, 
                  {provide: Router, useValue:routerStub}
                  ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    loginService = TestBed.get(LoginService);
  router = TestBed.get(Router);

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    
    //component.ngOnInit();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

// Checking form is invalid if it is empty
it('should have invalid form when it is empty', () => {
	  
  expect(component.loginForm.valid).toBeFalsy();
});

describe('should have username field', () => {
  let errors = {};
  let userName;
  beforeEach( ()=>{
    userName= component.loginForm.controls['userName'];
    errors = userName.errors || {};
  });
  // Checking userName is invalid if it is empty
  it('which is invalid when it is empty', () => {
    expect(userName.valid).toBeFalsy();

  });

  // Checking required error is present if username is invalid
  it('which contains required error when it is empty', () => {
    expect(errors['required']).toBeTruthy();

  });
});

describe('should have username field', () => {
  let errors = {};
  let userName;
  beforeEach( ()=>{
    userName= component.loginForm.controls['userName'];
    userName.setValue("Kalpana");
    errors = userName.errors || {};
    
  });
  // Checking userName is valid if it is filled
  it('which is valid when it is filled', () => {
    expect(userName.valid).toBeTruthy();

  });

  // Checking required error is not present if username is valid
  it('which should not contains required error when it is filled', () => {
    expect(errors['required']).toBeFalsy();

  });
});

describe('should have password field', () => {
  let errors = {};
  let password;
  beforeEach( ()=>{
    password= component.loginForm.controls['password'];
    errors = password.errors || {};
  });
  // Checking password is invalid if it is empty
  it('which is invalid when it is empty', () => {
    expect(password.valid).toBeFalsy();

  });

  // Checking required error is present if password is invalid
  it('which contains required error when it is empty', () => {
    expect(errors['required']).toBeTruthy();

  });
});

describe('should have password field', () => {
  let errors = {};
  let password;
  beforeEach( ()=>{
    password= component.loginForm.controls['password'];
    password.setValue("Kalpana");
    errors = password.errors || {};
    
  });
  // Checking password is valid if it is filled
  it('which is valid when it is filled', () => {
    expect(password.valid).toBeTruthy();

  });

  // Checking required error is not present if password is valid
  it('which should not contains required error when it is filled', () => {
    expect(errors['required']).toBeFalsy();

  });
});

// Checking form is valid if all fields are filled properly
it('should have valid from when all fields are correct', () => {
  component.loginForm.controls['userName'].setValue("Kalpana");
  component.loginForm.controls['password'].setValue("Kalpana");
  expect(component.loginForm.valid).toBeTruthy();
});


describe('should have noOfAttempts', () => {
  let spy;
  beforeEach( ()=>{
    spy = spyOn(loginService, 'checkUser')
      .and.returnValue(false);
    component.loginForm.controls['userName'].setValue("Kalpana");
    component.loginForm.controls['password'].setValue("Kalpana");
    component.userLogin();
    
  });
  // Checking noOfAttempts for first time with wrong values
  it('which should be increased by 1 on giving wrong credentials', () => {
    expect(component.noOfAttempts).toBe(1);

  });

  // Checking noOfAttempts for second time with wrong values
  it('which should be increased by 2 on giving wrong credentials for second time', () => {
    component.userLogin();
    expect(component.noOfAttempts).toBe(2);

  });

  // Checking noOfAttempts for third time with wrong values
  it('which should be increased by 2 on giving wrong credentials for second time', () => {
    component.userLogin();
    component.userLogin();
    expect(component.noOfAttempts).toBe(3);

  });
});

describe('should have error', () => {
  let spy;
  beforeEach( ()=>{
    spy = spyOn(loginService, 'checkUser')
      .and.returnValue(false);
    component.loginForm.controls['userName'].setValue("Kalpana");
    component.loginForm.controls['password'].setValue("Kalpana");
    component.userLogin();
    
  });
  // Checking error for first time with wrong values
  it('which should show proper error message for first time invalid entry', () => {
    expect(component.error).toBe('Username or Password mismatch. Only two more attempts you have....');

  });

  // Checking error for second time with wrong values
  it('which should show proper error message for second time invalid entry', () => {
    component.userLogin();
    expect(component.error).toBe('Again Username or Password mismatch. Last attempt. Please enter correctly');

  });

  // Checking error for third time with wrong values
  it('which should show proper error message for third time invalid entry', () => {
    component.userLogin();
    component.userLogin();
    expect(component.error).toBe('Sorry you have exceeded all the attempts...Come again later...');

  });
});

describe('should', () => {
  let spy;
  beforeEach( ()=>{
    spyOn(loginService, 'checkUser').and.returnValue(true);
    spy = spyOn(router,'navigate');
    component.loginForm.controls['userName'].setValue("Kalpana");
    component.loginForm.controls['password'].setValue("Kalpana");
    fixture.detectChanges();
    component.userLogin();
    
  });
  // Checking password is valid if it is filled
  it('call the router on giving the correct credentials', () => {
    expect(spy.calls.any()).toBe(true);

  });

  // Checking required error is not present if password is valid
  it('have noOfAttempts which is 0 on giving correct credentials', () => {
    expect(component.noOfAttempts).toBe(0);

  });
});
});

import './login.component.scss';
import {Component, Inject, forwardRef, OnInit} from '@angular/core';
import { AppService } from '../common/app.service';
import { User } from '../model/User';
import {Router} from "@angular/router";
import {AppComponent} from "../base/app.component";
import AppConstants from "../base/app.constants";
import {Account} from "../model/Account";
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit{
    private user;
    private error = '';
    private isLoading = false;

    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router,
        @Inject(forwardRef(()=> AppComponent)) private parent: AppComponent) {
        this.user = new User();
        this.user.account = new Account();
    }

    ngOnInit():void {
    }

    public login(){
        let vm = this;
        vm.error = '';
        vm.isLoading = true;
        this.appService.login(this.user).subscribe(
            res => {
                let user = res.json() as User;
                vm.parent.setUser(user);
                vm.isLoading = false;
                vm.router.navigate(['/'+AppConstants.ROUTER_USERS])
            },
            err => {
                vm.error = "Account or password is wrong!"
                vm.isLoading = false;
            }
        )
    }
}

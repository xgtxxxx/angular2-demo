import './users.component.scss';
import { Component, forwardRef, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../common/app.service'
import { AppComponent } from "../base/app.component";
import { User } from '../model/User'
import { Account } from "../model/Account";
import { TimeSheetTask } from "../model/TimeSheetTask";
import AppConstants from "../base/app.constants"
@Component({
    selector: 'app-users',
    templateUrl: './users.component.html'
})
export class UsersComponent {

    private isLoading = false;
    private error = "";
    private users;

    constructor(@Inject(AppService) private appService : AppService,
                @Inject(Router) private router : Router,
                @Inject(forwardRef(()=> AppComponent)) private parent: AppComponent) {
        this.getUsers();
    }

    public getUsers() {
        let vm = this;
        vm.error = '';
        vm.isLoading = true;
        this.appService.getUsers().subscribe(
            res => {
                vm.users = res.json().sort((u1, u2)=>u1.name.localeCompare(u2.name));
                vm.isLoading = false;
            },
            err => {
                vm.error = err.message;
                vm.isLoading = false;
            }
        )
    }

    public newUser(){
        this.router.navigate(['/'+AppConstants.ROUTER_NEW_USER]);
    }

    public editUser(user: User){
        this.router.navigate(['/'+AppConstants.ROUTER_EDIT_USER, user.id])
    }
}